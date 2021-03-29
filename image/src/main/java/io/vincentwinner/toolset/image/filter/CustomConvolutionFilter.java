package io.vincentwinner.toolset.image.filter;

import io.vincentwinner.toolset.image.DDepth;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * 自定义卷积滤镜
 */
public class CustomConvolutionFilter {

    /**
     * 将源矩阵用指定的卷积核计算
     * @param src 源矩阵
     * @param kernel 自定义卷积核
     */
    public static Mat customConvolution(Mat src,byte[] kernel){
        return customConvolution(src,new Mat(kernel));
    }

    /**
     * 将源矩阵用指定的卷积核计算
     * @param src 源矩阵
     * @param kernel 自定义卷积核
     */
    public static Mat customConvolution(Mat src,int[] kernel){
        return customConvolution(src,new Mat(kernel));
    }

    /**
     * 将源矩阵用指定的卷积核计算
     * @param src 源矩阵
     * @param kernel 自定义卷积核
     */
    public static Mat customConvolution(Mat src,double[] kernel){
        return customConvolution(src,new Mat(kernel));
    }

    /**
     * 将源矩阵用指定的卷积核计算
     * @param src 源矩阵
     * @param kernel 自定义卷积核
     */
    public static Mat customConvolution(Mat src,float[] kernel){
        return customConvolution(src,new Mat(kernel));
    }


    /**
     * 将源矩阵用指定的卷积核计算
     * @param src 源矩阵
     * @param kernel 自定义卷积核
     */
    public static Mat customConvolution(Mat src,Mat kernel){
        Mat dst = src.clone();
        opencv_imgproc.filter2D(src,dst, DDepth.ORIGINAL.value(), kernel);
        src.release();
        return dst;
    }

}
