package io.vincentwinner.toolset.ai.seeta6jni.interfac;

import io.vincentwinner.toolset.ai.seeta6jni.*;
import io.vincentwinner.toolset.ai.seeta6jni.config.Seeta6Config;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;
import io.vincentwinner.toolset.ai.seeta6jni.util.SeetaImageUtil;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 人脸处理
 * 采用多线程转换图像数据
 * 人脸识别方法 C++ 源代码部分多线程状态仍然同步执行，故只采用多线程转换图像数据
 */
@SuppressWarnings("all")
public class FaceHandleService {

    private static ExecutorService service;

    private static FaceDetector faceDetector;
    private static FaceLandmark faceLandmark;
    private static FaceLandmark68 faceLandmark68;
    private static FaceRecognizer faceRecognizer;

    private static class FaceHandleServiceInstance {
        private static FaceHandleService INSTANCE = new FaceHandleService();
    }

    private FaceHandleService(){
        Seeta6Config config = Seeta6Config.getInstance();
        service = new ThreadPoolExecutor(
                config.getCorePoolSize(),
                config.getMaxPoolSize(),
                config.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(config.getBlockQueueSize()),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        List<String> loadFunctions = config.getLoadFunctions();
        loadFunctions.forEach(f -> {
            if("faceDetector".equals(f)){
                faceDetector = Seeta6JNI.getFaceDetector();
            }else if("faceLandmark".equals(f)){
                faceLandmark = Seeta6JNI.getFaceLandmark();
            }else if("faceLandmark68".equals(f)){
                faceLandmark68 = Seeta6JNI.getFaceLandmark68();
            }else if("faceRecognizer".equals(f)){
                faceRecognizer = Seeta6JNI.getFaceRecognizer();
            }
        });
    }

    /**
     * 提交人脸处理任务
     * @param faceTask 人脸处理任务
     * @param <T> 任务处理结果类型
     * @return 可以获取多线程人脸处理结果的 Future 对象
     */
    public <T> Future<T> submitTask(FaceHandleTask<T> faceTask) {
        return service.submit(faceTask);
    }

    /**
     * 获取人脸处理服务实例
     * @return 人脸处理服务实例
     */
    public static FaceHandleService getInstance() {
        return FaceHandleServiceInstance.INSTANCE;
    }


    /**
     * 在程序关闭时手动调用此方法关闭线程池，否则程序不会自行退出
     */
    public void shutdown(){
        service.shutdown();
    }

    /**
     * 自定义人脸处理任务
     * @param <ResultType> 处理结果类型
     */
    public static abstract class FaceHandleTask<ResultType> implements Callable<ResultType> {

        /**
         * 将 java 中的 BufferedImage 对象转换为 C++ 中的 SeetaImageData 对象
         * @param image BufferedImage 对象
         * @return SeetaImageData 对象
         */
        protected SeetaImageData convertImage(BufferedImage image){
            if (image == null) return null;
            return SeetaImageUtil.toSeetaImageData(image);
        }

        /**
         * 获取图片所有人脸信息中最可能的人脸
         * @param faceInfos 图片中所有人脸信息以及他们的评分
         * @return 图片中最大人脸的信息以及评分
         */
        protected SeetaFaceInfo getMaxFaceInfo(SeetaFaceInfo[] faceInfos){
            if(faceInfos == null || faceInfos.length == 0) return null;
            return Arrays.stream(faceInfos).max(
                    (f1,f2) -> Float.compare(f1.getScore(),f2.getScore()))
                    .orElse(null);
        }
    }

    /**
     * 人脸检测任务
     */
    public static class FaceDetectTask extends FaceHandleTask<SeetaFaceInfo[]> {

        //待处理图像
        private final BufferedImage image;

        public FaceDetectTask(BufferedImage image){
            this.image = image;
        }

        @Override
        public SeetaFaceInfo[] call() {
            SeetaImageData imageData = convertImage(image);
            if(imageData == null) return null;
            return faceDetector.detect(imageData);
        }
    }

    /**
     * 人脸关键点检测任务
     * 双眼 （2）
     * 鼻子 （1）
     * 嘴角 （2）
     * 下边的图是人脸
     * ╭------------╮   ╭------------╮
     * │   ○    ○  │   │   1    2   ┃
     * │      |     │   │     3      ┃
     * │   ▁▁▁▁▁▁   │   │   4    5   ┃
     * ╰------------╯   ╰------------╯
     */
    public static class FaceLandmarkTask extends FaceHandleTask<SeetaPointF[]> {

        //待检测图像
        private BufferedImage image;
        //已经检测完毕的人脸位置及评分信息
        private SeetaFaceInfo[] faceInfos;
        //是否使用68关键点人脸识别模型
        private final boolean use68PointModel;

        public FaceLandmarkTask(BufferedImage image,boolean use68PointModel){
            this.image = image;
            this.use68PointModel = use68PointModel;
        }

        public FaceLandmarkTask(SeetaFaceInfo[] faceInfos,boolean use68PointModel) {
            this.faceInfos = faceInfos;
            this.use68PointModel = use68PointModel;
        }

        public FaceLandmarkTask(BufferedImage image){
            this(image,false);
        }

        public FaceLandmarkTask(SeetaFaceInfo[] faceInfos){
            this(faceInfos,false);
        }

        @Override
        public SeetaPointF[] call() throws Exception {
            SeetaImageData imageData = convertImage(image);
            if(imageData == null) return null;
            SeetaFaceInfo[] detectResult = this.faceInfos;
            if(detectResult == null) {
                detectResult = faceDetector.detect(imageData);
                if (detectResult == null || detectResult.length == 0) return null;
            }
            SeetaFaceInfo maxFaceInfo = getMaxFaceInfo(detectResult);
            if(use68PointModel){
                return faceLandmark68.mark(imageData,new SeetaRect(maxFaceInfo));
            }
            return faceLandmark.mark(imageData,new SeetaRect(maxFaceInfo));
        }
    }

    /**
     * 人脸特征提取
     * 注意：人脸关键点一定要为 5 点关键点特征提取结果才会准确
     *      左眼   右眼
     *          鼻
     *      左嘴角 右嘴角
     */
    public static class FaceFeatureExtractTask extends FaceHandleTask<float[]> {

        private BufferedImage image;
        private SeetaFaceInfo[] faceInfos;
        private SeetaFaceInfo maxFaceInfo;
        private SeetaPointF[] faceLandmarks;

        public FaceFeatureExtractTask(BufferedImage image){
            this.image = image;
        }

        public FaceFeatureExtractTask(SeetaFaceInfo[] faceInfos){
            this.faceInfos = faceInfos;
        }

        public FaceFeatureExtractTask(SeetaFaceInfo maxFaceInfo){
            this.maxFaceInfo = maxFaceInfo;
        }

        public FaceFeatureExtractTask(SeetaPointF[] faceLandmarks){
            this.faceLandmarks = faceLandmarks;
        }

        @Override
        public float[] call() throws Exception {
            SeetaImageData imageData = convertImage(image);
            if(imageData == null) return null;
            SeetaFaceInfo[] detectResult = this.faceInfos;
            SeetaFaceInfo maxFaceInfo = this.maxFaceInfo;
            SeetaPointF[] markResult = this.faceLandmarks;
            if(detectResult == null && maxFaceInfo == null && markResult == null) {
                detectResult = faceDetector.detect(imageData);
            }
            if(detectResult == null || detectResult.length == 0) return null;
            if(maxFaceInfo == null && markResult == null) {
                maxFaceInfo = getMaxFaceInfo(detectResult);
            }
            if(markResult == null) {
                markResult = faceLandmark.mark(imageData, new SeetaRect(maxFaceInfo));
            }
            if(markResult == null || markResult.length == 0) return null;
            return faceRecognizer.extractFaceFeature(imageData,markResult);
        }
    }

    /**
     * 比较人脸特征任务
     */
    public static class FaceCompareTask extends FaceHandleTask<Float> {

        private float[] feature1;
        private float[] feature2;

        private BufferedImage img1;
        private BufferedImage img2;

        public FaceCompareTask(float[] feature1, float[] feature2) {
            this.feature1 = feature1;
            this.feature2 = feature2;
        }

        public FaceCompareTask(BufferedImage img1, BufferedImage img2) {
            this.img1 = img1;
            this.img2 = img2;
        }

        @Override
        public Float call() throws Exception {
            if(feature1 == null && feature2 == null && (img1 == null || img2 == null)) return null;
            if(feature1 == null){
                feature1 = SingleThreadFaceHelper.extractFaceFeature(img1);
            }
            if(feature2 == null){
                feature2 = SingleThreadFaceHelper.extractFaceFeature(img2);
            }
            return faceRecognizer.compare(feature1,feature2);
        }

    }

}
