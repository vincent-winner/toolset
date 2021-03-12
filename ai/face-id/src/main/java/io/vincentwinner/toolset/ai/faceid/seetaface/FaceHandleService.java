package io.vincentwinner.toolset.ai.faceid.seetaface;

import com.seetaface2.model.SeetaImageData;
import com.seetaface2.model.SeetaPointF;
import com.seetaface2.model.SeetaRect;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用本类方法对图片进行 人脸识别 和 人脸比较
 */
public class FaceHandleService {

    private ExecutorService executorService;
    private static final FaceFeatureModel model = FaceFeatureModel.getInstance();
    private static final SeetaProperty property = SeetaProperty.getInstance();;

    private FaceHandleService(){
        executorService = Executors.newFixedThreadPool(property.getThreadMax());
    }

    private static final class Instance {
        private static final FaceHandleService instance = new FaceHandleService();
    }
    public static FaceHandleService getInstance() {
        return Instance.instance;
    }

    /**
     * 人脸比较任务
     */
    private static final class FaceCompareTask implements Callable<FaceCompareResult>{

        private final InputStream pic1;
        private final InputStream pic2;

        FaceCompareTask(InputStream pic1, InputStream pic2){
            this.pic1 = pic1;
            this.pic2 = pic2;
        }

        @Override
        public FaceCompareResult call() throws Exception {
            SeetaImageData pic1 = toImageData(this.pic1);
            SeetaImageData pic2 = toImageData(this.pic2);
            float rate = compare(pic1,pic2);
            return new FaceCompareResult(rate / property.getFactor().floatValue(),rate >= property.getSimilarRate());
        }

    }

    /**
     * 人脸识别任务
     */
    private static final class FaceDetectTask implements Callable<SeetaRect[]> {

        private final InputStream pic;

        FaceDetectTask(InputStream pic){
            this.pic = pic;
        }

        @Override
        public SeetaRect[] call() throws Exception {
            SeetaImageData pic = toImageData(this.pic);
            return detect(pic);
        }
    }

    /**
     * 人脸关键点检测任务
     */
    private static final class FaceDetectPointTask implements Callable<SeetaPointF[]>{

        private final InputStream pic;

        FaceDetectPointTask(InputStream pic){
            this.pic = pic;
        }

        @Override
        public SeetaPointF[] call() throws Exception {
            SeetaImageData pic = toImageData(this.pic);
            return detectPoints(pic);
        }
    }

    /**
     * 提交人脸比较任务
     * @param pic1 图片1
     * @param pic2 图片2
     * @return 人脸比较结果 {@link FaceCompareResult}
     */
    public Future<FaceCompareResult> submitCompare(InputStream pic1, InputStream pic2){
        return executorService.submit(new FaceCompareTask(pic1,pic2));
    }

    /**
     * 提交人脸检测任务
     * @param pic 图片
     * @return 人脸位置（矩形区域）
     */
    public Future<SeetaRect[]> submitDetect(InputStream pic) {
        return executorService.submit(new FaceDetectTask(pic));
    }

    /**
     * 检测人脸五个关键点
     * 双眼 （2）
     * 鼻子 （1）
     * 嘴角 （2）
     * 下边的图是人脸
     * ╭------------╮   ╭------------╮
     * │   ○    ○  │   │   1    2   ┃
     * │      |     │   │     3      ┃
     * │   ▁▁▁▁▁▁   │   │   4    5   ┃
     * ╰------------╯   ╰------------╯
     * @param pic 图片输入流
     * @return 关键点 x5
     */
    public Future<SeetaPointF[]> submitDetectPoint(InputStream pic){
        return executorService.submit(new FaceDetectPointTask(pic));
    }

    /**
     * 等待线程池中的任务进行完毕后关闭线程池
     */
    public void shutdown(){
        executorService.shutdown();
    }

    /**
     * 比较两张人脸的相似度
     * @param pic1 图片1
     * @param pic2 图片2
     * @return 相似度（负数为未检测到人脸）
     */
    private static strictfp synchronized float compare(SeetaImageData pic1, SeetaImageData pic2){
        return model.compare(pic1,pic2);
    }

    /**
     * 检测图片中的人脸位置
     * @return 关键点集合
     */
    private static synchronized SeetaRect[] detect(SeetaImageData pic){
        return model.detect(pic);
    }

    /**
     * 检测人脸五个关键点
     * @return 关键点集合
     */
    private static synchronized SeetaPointF[] detectPoints(SeetaImageData pic){
        return model.detectPoints(pic);
    }

    /**
     * 释放线程池
     */
    @Override
    public void finalize() {
        shutdown();
    }

    /**
     * 按照 Blue Green Red 的顺序生成 BGR 矩阵
     * 在 OpenCV 中为了兼容老机型而采用了 BGR 模式
     * @param image 被处理的图片
     * @return BGR 矩阵
     */
    private static byte[] getMatrixBGR(BufferedImage image) {
        byte[] matrixBGR;
        if (isBGR3Byte(image)) {
            matrixBGR = (byte[]) image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        } else {
            // ARGB格式图像数据
            int[] intrgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            matrixBGR = new byte[image.getWidth() * image.getHeight() * 3];
            // ARGB转BGR格式
            for (int i = 0, j = 0; i < intrgb.length; ++i, j += 3) {
                matrixBGR[j] = (byte) (intrgb[i] & 0xff);
                matrixBGR[j + 1] = (byte) ((intrgb[i] >> 8) & 0xff);
                matrixBGR[j + 2] = (byte) ((intrgb[i] >> 16) & 0xff);
            }
        }
        return matrixBGR;
    }

    /**
     * 是否为 BGR 三通道图像
     * @param image 图像
     * @return 是否为 BGR 三通道图像
     */
    private static boolean isBGR3Byte(BufferedImage image) {
        return equalBandOffsetWith3Byte(image, new int[]{0, 1, 2});
    }

    /**
     * 是否为 BGR 三通道图像
     */
    private static boolean equalBandOffsetWith3Byte(BufferedImage image, int[] bandOffset) {
        if (image.getType() == BufferedImage.TYPE_3BYTE_BGR) {
            if (image.getData().getSampleModel() instanceof ComponentSampleModel) {
                ComponentSampleModel sampleModel = (ComponentSampleModel) image.getData().getSampleModel();
                return Arrays.equals(sampleModel.getBandOffsets(), bandOffset);
            }
        }
        return false;
    }

    /**
     * 将图片转换为目标图像
     * @param inputStream 图片输入流
     * @return 目标图像
     */
    private static SeetaImageData toImageData(InputStream inputStream){
        try {
            BufferedImage imageBuf = ImageIO.read(inputStream);
            SeetaImageData image = new SeetaImageData(imageBuf.getWidth(),imageBuf.getHeight());
            image.data = getMatrixBGR(imageBuf);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
