package io.vincentwinner.toolset.image.filter.blur;

import io.vincentwinner.toolset.image.util.MatUtil;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

import java.io.InputStream;

import static io.vincentwinner.toolset.image.util.ConvolutionKernelCheckUtil.checkMedianKernel;

/**
 * 中值模糊滤镜
 */
public class MedianBlur {

    /**
     * 对输入流中的图片做中值模糊处理
     * @param fileName 文件名
     * @param kernelSize 卷积核大小
     * @return 处理后的图片矩阵
     */
    public static Mat medianBlur(String fileName,int kernelSize){
        checkMedianKernel(kernelSize);
        Mat src = MatUtil.readFileToMat(fileName);
        return medianConvolution(src,kernelSize);
    }

    /**
     * 对输入流中的图片做中值模糊处理
     * @param inputStream 图片输入流
     * @param kernelSize 卷积核大小
     * @return 处理后的图片矩阵
     */
    public static Mat medianBlur(InputStream inputStream,int kernelSize){
        checkMedianKernel(kernelSize);
        Mat src = MatUtil.inputStreamToMat(inputStream,kernelSize);
        return medianConvolution(src,kernelSize);
    }

    /**
     * 中值模糊卷积函数
     * 中值卷积核大小必须为奇数
     * @param src 原图片矩阵
     * @param kernelSize 卷积核大小
     * @return 计算后的图片矩阵
     */
    public static Mat medianConvolution(Mat src,int kernelSize){
        Mat dst = new Mat();
        opencv_imgproc.medianBlur(src,dst,kernelSize);
        src.release();
        return dst;
    }

}
