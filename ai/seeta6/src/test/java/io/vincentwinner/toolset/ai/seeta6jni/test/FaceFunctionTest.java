package io.vincentwinner.toolset.ai.seeta6jni.test;

import io.vincentwinner.toolset.ai.seeta6jni.*;
import io.vincentwinner.toolset.ai.seeta6jni.frame.ImageFrame;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;
import io.vincentwinner.toolset.ai.seeta6jni.util.SeetaImageUtil;
import io.vincentwinner.toolset.os.Computer;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FaceFunctionTest {

    static {
        //输出 CPU 和 内存信息
        System.out.println(Computer.getCPU());
        System.out.println(Computer.getMemory());
    }

    //多人脸图片
    private final File manyPeoplePic = new File("C:\\Users\\84681\\Pictures\\Camera Roll\\一寸照片\\test\\0b.jpg");
    //单人脸图片
    private final File onePeoplePic = new File("C:\\Users\\84681\\Pictures\\Camera Roll\\一寸照片\\test\\002.jpg");
    //人脸位置检测测试次数
    private final int detectTimes = 100;
    //人脸关键点检测测试次数
    private final int landmarkTimes = 100;
    //人脸特征提取测试次数
    private final int extractFaceFeatureTimes = 100;
    //人脸特征对比测试次数
    private final int compareFaceFeatureTimes = 100;

    /**
     * 测试人脸检测功能并显示检测结果2秒
     */
    @Test
    public void _01_testFaceDetector() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        SeetaFaceInfo[] detect = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(manyPeoplePic)));
        new ImageFrame(new FileInputStream(manyPeoplePic),detect);
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 测试人脸关键点检测（5点）功能并显示检测结果2秒
     */
    @Test
    public void _02_testFaceLandmark() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark landmark = Seeta6JNI.getFaceLandmark();
        SeetaFaceInfo[] detectResult = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)));
        SeetaPointF[] markResult = landmark.mark(SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)
        ), new SeetaRect(detectResult[0]));
        new ImageFrame(new FileInputStream(onePeoplePic),markResult);
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 测试人脸关键点检测（68）点功能并显示检测结果2秒
     */
    @Test
    public void _03_testFaceLandmark68() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark68 landmark = Seeta6JNI.getFaceLandmark68();
        SeetaFaceInfo[] detectResult = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)));
        SeetaPointF[] markResult = landmark.mark(SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)
        ), new SeetaRect(detectResult[0]));
        new ImageFrame(new FileInputStream(onePeoplePic),markResult);
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 测试人脸特征提取功能
     */
    @Test
    public void _04_testFaceRecognizerExtractFaceFeature() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark landmark = Seeta6JNI.getFaceLandmark();
        FaceRecognizer recognizer = Seeta6JNI.getFaceRecognizer();
        SeetaFaceInfo[] detectResult = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)));
        SeetaPointF[] markResult = landmark.mark(SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)
        ), new SeetaRect(detectResult[0]));
        float[] features = recognizer.extractFaceFeature(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)),
                markResult
        );
        Assert.assertTrue(features.length > 0);
    }

    /**
     * 测试人脸对比功能
     */
    @Test
    public void _05_testFaceRecognizerFaceCompare() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark landmark = Seeta6JNI.getFaceLandmark();
        FaceRecognizer recognizer = Seeta6JNI.getFaceRecognizer();
        SeetaFaceInfo[] detectResult = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)));
        SeetaPointF[] markResult = landmark.mark(SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)
        ), new SeetaRect(detectResult[0]));
        float[] features = recognizer.extractFaceFeature(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)),
                markResult
        );
        float similar = recognizer.compare(features,features);
        Assert.assertTrue(similar > 0.99f);
    }

    /**
     * 测试人脸检测 {@link #detectTimes} 次
     */
    @Test
    public void _101_testFaceDetectorN() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        SeetaImageData imgData = SeetaImageUtil.toSeetaImageData(ImageIO.read(manyPeoplePic));
        long start = System.currentTimeMillis();
        for (int i = 0; i < detectTimes; i++) {
            faceDetector.detect(imgData);
        }
        long stop = System.currentTimeMillis();
        System.out.println("测试 FaceDetector " + detectTimes + " 次: ");
        System.out.println("  全部用时: " + (stop - start) / 1000d + " s");
        System.out.println("  单次用时: " + (stop - start) / 1000d / detectTimes + " s");
    }

    /**
     * 测试人脸关键点检测（5点）功能 {@link #landmarkTimes} 次
     */
    @Test
    public void _102_testFaceLandmarkN() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark faceLandmark = Seeta6JNI.getFaceLandmark();
        SeetaImageData imageData = SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic));
        long start = System.currentTimeMillis();
        for (int i = 0; i < landmarkTimes; i++) {
            SeetaFaceInfo[] detectResult = faceDetector.detect(imageData);
            faceLandmark.mark(imageData, new SeetaRect(detectResult[0]));
        }
        long stop = System.currentTimeMillis();
        System.out.println("测试 FaceLandmark（5点） " + landmarkTimes + " 次: ");
        System.out.println("  全部用时: " + (stop - start) / 1000d + " s");
        System.out.println("  单次用时: " + (stop - start) / 1000d / landmarkTimes + " s");
    }

    /**
     * 测试人脸关键点检测（68点）功能 {@link #landmarkTimes} 次
     */
    @Test
    public void _103_testFaceLandmark68N() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark68 faceLandmark = Seeta6JNI.getFaceLandmark68();
        SeetaImageData imageData = SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic));
        long start = System.currentTimeMillis();
        for (int i = 0; i < landmarkTimes; i++) {
            SeetaFaceInfo[] detectResult = faceDetector.detect(imageData);
            faceLandmark.mark(imageData, new SeetaRect(detectResult[0]));
        }
        long stop = System.currentTimeMillis();
        System.out.println("测试 FaceLandmark（68点） " + landmarkTimes + " 次: ");
        System.out.println("  全部用时: " + (stop - start) / 1000d + " s");
        System.out.println("  单次用时: " + (stop - start) / 1000d / landmarkTimes + " s");
    }

    /**
     * 测试人脸特征提取功能 {@link #extractFaceFeatureTimes} 次
     */
    @Test
    public void _104_testFaceRecognizerExtractFaceFeatureN() throws Exception{
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark landmark = Seeta6JNI.getFaceLandmark();
        FaceRecognizer recognizer = Seeta6JNI.getFaceRecognizer();
        SeetaFaceInfo[] detectResult = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)));
        SeetaPointF[] markResult = landmark.mark(SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)
        ), new SeetaRect(detectResult[0]));
        SeetaImageData imageData = SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic));
        long start = System.currentTimeMillis();
        for (int i = 0; i < extractFaceFeatureTimes; i++) {
            recognizer.extractFaceFeature(imageData, markResult);
        }
        long stop = System.currentTimeMillis();
        System.out.println("测试 FaceRecognizer 特征提取 " + extractFaceFeatureTimes + " 次: ");
        System.out.println("  全部用时: " + (stop - start) / 1000d + " s");
        System.out.println("  单次用时: " + (stop - start) / 1000d / extractFaceFeatureTimes + " s");
    }

    /**
     * 测试人脸特征对比功能 {@link #compareFaceFeatureTimes} 次
     */
    @Test
    public void _105_testFaceRecognizerFaceCompare() throws Exception {
        FaceDetector faceDetector = Seeta6JNI.getFaceDetector();
        FaceLandmark landmark = Seeta6JNI.getFaceLandmark();
        FaceRecognizer recognizer = Seeta6JNI.getFaceRecognizer();
        SeetaFaceInfo[] detectResult = faceDetector.detect(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)));
        SeetaPointF[] markResult = landmark.mark(SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)
        ), new SeetaRect(detectResult[0]));
        float[] features = recognizer.extractFaceFeature(
                SeetaImageUtil.toSeetaImageData(ImageIO.read(onePeoplePic)),
                markResult
        );
        long start = System.nanoTime();
        for (int i = 0; i < compareFaceFeatureTimes; i++) {
            recognizer.compare(features,features);
        }
        long stop = System.nanoTime();
        System.out.println("测试 FaceRecognizer 特征对比 " + compareFaceFeatureTimes + " 次: ");
        System.out.println("  全部用时: " + (stop - start) / 1000d + " μs");
        System.out.println("  单次用时: " + (stop - start) / 1000d / compareFaceFeatureTimes + " μs");
    }

}
