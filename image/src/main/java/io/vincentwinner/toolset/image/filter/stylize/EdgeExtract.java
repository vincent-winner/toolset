package io.vincentwinner.toolset.image.filter.stylize;

import io.vincentwinner.toolset.image.DDepth;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * 边缘提取
 */
public class EdgeExtract {

    /**
     * 拉普拉斯边缘提取
     * @param src 源矩阵
     * @param kernelSize 卷积核大小（1,3,5,7）
     * @return 拉普拉斯算子提取边缘
     */
    public static Mat laplacian(Mat src,int kernelSize){
        Mat dst = new Mat();
        opencv_imgproc.Laplacian(src,dst,DDepth.CV_16S.value(),kernelSize,-1,0,4);
        src.release();
        return dst;
    }

    /**
     * 索贝尔边缘提取
     * @param src 源矩阵
     * @param dx x
     * @param dy y
     * @param kernelSize 卷积核大小（1,3,5,7）
     * @return 索贝尔算子提取边缘
     */
    private static Mat sobel(Mat src,int dx,int dy,int kernelSize){
        Mat dst = new Mat();
        opencv_imgproc.Sobel(src,dst,DDepth.CV_16S.value(),dx,dy,kernelSize,-1,0,4);
        src.release();
        return dst;
    }

    /**
     * 索贝尔 X 横向边缘提取
     * @param src 源矩阵
     * @param kernelSize 卷积核大小（1,3,5,7）
     * @return 索贝尔算子X提取横向边缘
     */
    public static Mat sobelX(Mat src,int kernelSize){
        return sobel(src,1,0,kernelSize);
    }

    /**
     * 索贝尔 Y 纵向边缘提取
     * @param src 源矩阵
     * @param kernelSize 卷积核大小（1,3,5,7）
     * @return 索贝尔算子Y提取纵向边缘
     */
    public static Mat sobelY(Mat src,int kernelSize){
        return sobel(src,0,1,kernelSize);
    }

    /**
     * 索贝尔边缘提取，同时提取横纵边缘
     * @param src 源矩阵
     * @param kernelSize 卷积核大小（1,3,5,7）
     * @return 索贝尔边缘提取，同时提取横纵边缘
     */
    public static Mat sobel(Mat src,int kernelSize){
        Mat copy = src.clone();
        Mat gX = sobelX(src, kernelSize);
        Mat gY = sobelY(copy,kernelSize);
        src.release();
        copy.release();
        Mat result = new Mat();
        opencv_core.convertScaleAbs(gX,gX);
        opencv_core.convertScaleAbs(gY,gY);
        opencv_core.addWeighted(gX, 1, gY, 0, 1, result);
        return result;
    }

}
