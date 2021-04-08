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

    /**
     * 精确边缘检测
     * 阈值参数建议高阈值为低阈值的两倍或三倍
     * @param src 源图像矩阵
     * @param thresholdLow 低阈值（连接高阈值检测后断续的边缘）
     * @param thresholdHigh 高阈值（检测图像明显边缘）
     * @param kernelSize 核心大小（null默认为3）
     * @param L2Gradient 是否启用 L2 范数
     *                   L2范数：两个方向的倒数的平方和再开放（更精确，计算量更大）
     *                   L1范数：直接将两个方向导数的绝对值相加
     * @return 边缘检测结果矩阵
     */
    public static Mat canny(Mat src,double thresholdLow,double thresholdHigh,Integer kernelSize,boolean L2Gradient){
        Mat dst = new Mat();
        opencv_imgproc.Canny(src,dst,thresholdLow,thresholdHigh,kernelSize == null ? 3 : kernelSize,L2Gradient);
        src.release();
        return dst;
    }

    /**
     * 精确边缘检测
     * @param src 源图像
     * @param L2Gradient 是否启用 L2 范数
     *                   L2范数：两个方向的倒数的平方和再开放（更精确，计算量更大）
     *                   L1范数：直接将两个方向导数的绝对值相加
     * @return 边缘检测结果矩阵
     */
    public static Mat canny(Mat src,boolean L2Gradient){
        return canny(src,50,150,3,L2Gradient);
    }

}
