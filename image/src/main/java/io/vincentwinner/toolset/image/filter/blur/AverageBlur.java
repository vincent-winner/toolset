package io.vincentwinner.toolset.image.filter.blur;

import io.vincentwinner.toolset.image.util.MatUtil;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.io.InputStream;

import static io.vincentwinner.toolset.image.util.ConvolutionKernelCheckUtil.checkAverageKernel;

/**
 * 均值模糊滤镜
 */
public class AverageBlur {

    /**
     * 从文件中读取图片并进行均值模糊处理
     * @param fileName 文件名
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 均值模糊后的图像
     */
    public static Mat averageBlur(String fileName,int kernelWidth,int kernelHeight){
        checkAverageKernel(kernelWidth,kernelHeight);
        Mat src = MatUtil.readFileToMat(fileName);
        return averageConvolution(src,kernelWidth,kernelHeight);
    }

    /**
     * 将输入流中的图片进行均值模糊处理
     * @param inputStream 输入流
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 均值模糊后的图像
     */
    public static Mat averageBlur(InputStream inputStream,int kernelWidth,int kernelHeight){
        checkAverageKernel(kernelWidth,kernelHeight);
        Mat src = MatUtil.inputStreamToMat(inputStream,4096);
        return averageConvolution(src,kernelWidth,kernelHeight);
    }

    /**
     * 对图片进行均值卷积运算
     * @param src 源图像
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 均值卷积计算后的图像
     */
    public static Mat averageConvolution(Mat src,int kernelWidth,int kernelHeight){
        Mat dst = new Mat();
        opencv_imgproc.blur(src,dst,new Size(kernelWidth,kernelHeight));
        src.release();
        return dst;
    }

}
