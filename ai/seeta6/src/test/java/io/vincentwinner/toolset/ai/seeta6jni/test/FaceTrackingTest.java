package io.vincentwinner.toolset.ai.seeta6jni.test;

import io.vincentwinner.toolset.ai.seeta6jni.interfac.SingleThreadFaceHelper;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaAngle;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;
import io.vincentwinner.toolset.ai.seeta6jni.util.SeetaImageUtil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.global.opencv_highgui;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * ****************************
 * ***** 按 ESC 键退出程序 ******
 * ****************************
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FaceTrackingTest {

    /**
     * 测试人脸识别追踪
     */
    @Test
    @SuppressWarnings("all")
    public void _01_testTrackingDetectFace() {
        trackingByCamera("Pose Estimation", (imgMatrix,imgBuf) -> {
            SeetaFaceInfo[] faceInfos = SingleThreadFaceHelper.detectFace(imgBuf);
            SeetaRect maxFaceRect = SeetaImageUtil.getMaxFaceRect(faceInfos);
            opencv_imgproc.rectangle(imgMatrix, new Rect(
                            maxFaceRect.getX(),
                            maxFaceRect.getY(),
                            maxFaceRect.getWidth(),
                            maxFaceRect.getHeight()),
                    new Scalar(0,255,0,0)
            );
        });
    }

    /**
     * 测试人脸关键点检测（5点）追踪
     */
    @Test
    @SuppressWarnings("all")
    public void _02_testTrackingLandmarkFace5(){
        trackingByCamera("Face Landmark 5 Points",(imgMatrix,imgBuf) -> {
            SeetaPointF[] points = SingleThreadFaceHelper.landmarkFace(imgBuf);
            for (SeetaPointF point : points) {
                opencv_imgproc.circle(imgMatrix,
                        new Point(Double.valueOf(point.getX()).intValue(),
                                Double.valueOf(point.getY()).intValue()),
                        2, new Scalar(255, 0, 0, 0), -1, 8, 0
                );
            }
        });
    }

    /**
     * 测试人脸关键点检测（68点）追踪
     */
    @Test
    @SuppressWarnings("all")
    public void _02_testTrackingLandmarkFace68(){
        trackingByCamera("Face Landmark 68 Points",(imgMatrix,imgBuf) -> {
            SeetaPointF[] points = SingleThreadFaceHelper.landmarkFace68(imgBuf);
            for (SeetaPointF point : points) {
                opencv_imgproc.circle(imgMatrix,
                        new Point(Double.valueOf(point.getX()).intValue(),
                                Double.valueOf(point.getY()).intValue()),
                        2, new Scalar(255, 0, 0, 0), -1, 8, 0
                );
            }
        });
    }

    /**
     * 测试人脸姿态估计追踪
     *
     */
    @Test
    @SuppressWarnings("all")
    public void _04_testTrackingEstimatePose(){
        trackingByCamera("Pose Estimation", (imgMatrix,imgBuf) -> {
            SeetaAngle angle = SingleThreadFaceHelper.estimationPose(imgBuf);
            drawAxis(imgMatrix,angle);
        });
    }

    @FunctionalInterface
    public interface PaintingFunction {
        void painting(Mat imgMatrix,BufferedImage imgBuf);
    }

    /**
     * 跟踪相机并绘制跟踪方法
     * @param windowName 窗口名称
     * @param function 绘制方法
     */
    public void trackingByCamera(String windowName, PaintingFunction function){
        VideoCapture camera = new VideoCapture();
        Mat mat = new Mat();
        camera.open(0);
        if (!camera.isOpened()) {
            System.out.println("无法打开摄像头");
            System.exit(-1);
        }
        while (true) {
            try {
                camera.read(mat);
                BufferedImage imgBuf = mat2BufferedImage(mat);
                function.painting(mat,imgBuf);
                opencv_highgui.imshow(windowName, mat);
            }catch (Exception e){continue; }
            if (27 == opencv_highgui.waitKey(5)) {
                camera.release();
                opencv_highgui.destroyAllWindows();
                Assert.assertTrue(true);
                break;
            }
        }
    }

    /**
     * 将图像矩阵转换为 BufferedImage 对象
     * @param mat 图像矩阵
     * @return BufferedImage 对象
     */
    public BufferedImage mat2BufferedImage(Mat mat){
        BytePointer buf = new BytePointer();
        opencv_imgcodecs.imencode(".png", mat, buf);
        byte[] byteArray = buf.getStringBytes();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufImage;
    }

    /**
     * 利用三维坐标轴绘制欧拉角
     * @param imageMatrix 图像矩阵
     * @param angle 人脸姿态角（欧拉角 yaw, pitch, roll）
     */
    private void drawAxis(Mat imageMatrix, SeetaAngle angle){
        final float PI = 3.14159265f;
        final int tdx = 70;
        final int tdy = 70;
        final int size = 55;
        int x1, y1,
                x2, y2,
                x3, y3;

        angle.setPitch(angle.getPitch() * PI / 180);
        angle.setYaw(angle.getYaw() * PI / 180);
        angle.setRoll(angle.getRoll() * PI / 180);

        x1 = Double.valueOf(
                size * (cos(angle.getYaw()) * cos(angle.getRoll())) + tdy
        ).intValue();
        y1 = Double.valueOf(
                size * (cos(angle.getPitch()) *
                        sin(angle.getRoll()) *
                        sin(angle.getPitch()) *
                        sin(angle.getYaw())
                ) + tdy
        ).intValue();

        x2 = Double.valueOf(
                size * (-cos(angle.getYaw()) * sin(angle.getRoll())) + tdy
        ).intValue();
        y2 = Double.valueOf(
                size * -(
                        cos(angle.getPitch()) *
                        cos(angle.getRoll()) -
                        sin(angle.getPitch()) *
                        sin(angle.getYaw()) *
                        sin(angle.getRoll())
                ) + tdy
        ).intValue();

        x3 = Double.valueOf(
                size * (sin(angle.getYaw())) + tdy
        ).intValue();
        y3 = Double.valueOf(
                size * (-cos(angle.getYaw()) * sin(angle.getPitch())) + tdy
        ).intValue();

        Point p0 = new Point(tdx,tdy);

        opencv_imgproc.line(imageMatrix,p0,new Point(x1,y1),new Scalar(0,0,255,0),2,8,0);
        opencv_imgproc.line(imageMatrix,p0,new Point(x2,y2),new Scalar(0,255,0,0),2,8,0);
        opencv_imgproc.line(imageMatrix,p0,new Point(x3,y3),new Scalar(255,0,0,0),2,8,0);

    }

}
