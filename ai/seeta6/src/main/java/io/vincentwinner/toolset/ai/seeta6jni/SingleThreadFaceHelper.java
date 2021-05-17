package io.vincentwinner.toolset.ai.seeta6jni;

import io.vincentwinner.toolset.ai.seeta6jni.config.Seeta6Config;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;
import io.vincentwinner.toolset.ai.seeta6jni.util.SeetaImageUtil;

import java.awt.image.BufferedImage;
import java.util.List;

@SuppressWarnings("all")
public class SingleThreadFaceHelper {

    static {
        List<String> loadFunctions = Seeta6Config.getInstance().getLoadFunctions();
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

    private static FaceDetector faceDetector;
    private static FaceLandmark faceLandmark;
    private static FaceLandmark68 faceLandmark68;
    private static FaceRecognizer faceRecognizer;

    /**
     * 检测图片中所有人脸的位置并给出每个人脸的评分
     * 没有检测到人脸则返回 null
     * @param img 含有人脸的图片
     * @return 人脸位置信息和评分信息
     */
    public static SeetaFaceInfo[] detectFace(BufferedImage img){
        SeetaImageData seetaImageData = SeetaImageUtil.toSeetaImageData(img);
        SeetaFaceInfo[] faceInfo = faceDetector.detect(seetaImageData);
        if (faceInfo == null || faceInfo.length == 0) return null;
        return faceInfo;
    }

    /**
     * 检测图片中最大人脸的关键点（5点）
     * 双眼 （2）
     * 鼻子 （1）
     * 嘴角 （2）
     * 下边的图是人脸
     * ╭------------╮   ╭------------╮
     * │   ○    ○  │   │   1    2   ┃
     * │      |     │   │     3      ┃
     * │   ▁▁▁▁▁▁   │   │   4    5   ┃
     * ╰------------╯   ╰------------╯
     * @param img 含有人脸的图片
     * @return 图片中最大人脸的关键点（5点）
     */
    public static SeetaPointF[] landmarkFace(BufferedImage img){
        SeetaImageData seetaImageData = SeetaImageUtil.toSeetaImageData(img);
        SeetaFaceInfo[] faceInfo = faceDetector.detect(seetaImageData);
        if (faceInfo == null || faceInfo.length == 0) return null;
        SeetaFaceInfo maxFaceInfo = faceInfo[0];
        for (SeetaFaceInfo seetaFaceInfo : faceInfo) {
            if(seetaFaceInfo.getScore() > maxFaceInfo.getScore()){
                maxFaceInfo = seetaFaceInfo;
            }
        }
        return faceLandmark.mark(seetaImageData,new SeetaRect(maxFaceInfo));
    }

    /**
     * 检测图片中最大人脸的关键点（68点）
     * @param img 含有人脸的图片
     * @return 图片中最大人脸的关键点（68点）
     */
    public static SeetaPointF[] landmarkFace68(BufferedImage img){
        SeetaImageData seetaImageData = SeetaImageUtil.toSeetaImageData(img);
        SeetaFaceInfo[] faceInfo = faceDetector.detect(seetaImageData);
        if (faceInfo == null || faceInfo.length == 0) return null;
        SeetaFaceInfo maxFaceInfo = faceInfo[0];
        for (SeetaFaceInfo seetaFaceInfo : faceInfo) {
            if(seetaFaceInfo.getScore() > maxFaceInfo.getScore()){
                maxFaceInfo = seetaFaceInfo;
            }
        }
        return faceLandmark68.mark(seetaImageData,new SeetaRect(maxFaceInfo));
    }

    /**
     * 提取人脸特征值
     * @param img 包含人脸的图片
     * @return 人脸特征值
     */
    public static float[] extractFaceFeature(BufferedImage img){
        SeetaImageData seetaImageData = SeetaImageUtil.toSeetaImageData(img);
        SeetaFaceInfo[] faceInfo = faceDetector.detect(seetaImageData);
        if (faceInfo == null || faceInfo.length == 0) return null;
        SeetaFaceInfo maxFaceInfo = faceInfo[0];
        for (SeetaFaceInfo seetaFaceInfo : faceInfo) {
            if(seetaFaceInfo.getScore() > maxFaceInfo.getScore()){
                maxFaceInfo = seetaFaceInfo;
            }
        }
        SeetaPointF[] points = faceLandmark.mark(seetaImageData, new SeetaRect(maxFaceInfo));
        return faceRecognizer.extractFaceFeature(seetaImageData, points);
    }

    /**
     * 比较两组人脸特征值
     * @param feature1 人脸特征1
     * @param feature2 人脸特征2
     * @return 特征相似度
     */
    public static float compareFaceFeature(float[] feature1,float[] feature2){
        return faceRecognizer.compare(feature1,feature2);
    }

}
