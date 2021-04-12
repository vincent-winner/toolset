package io.vincentwinner.toolset.image.filter.stylize;

import org.bytedeco.opencv.global.opencv_photo;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * 水彩效果
 * 使用边缘感知滤波器
 */
public class WatercolorPainting {

    public static Mat waterColor(Mat src,float sigmaS,float sigmaR){
        Mat dst = new Mat();
        opencv_photo.stylization(src,dst,sigmaS,sigmaR);
        src.release();
        return dst;
    }

    public static Mat waterColor(Mat src){
        return waterColor(src,60f,0.45f);
    }

}
