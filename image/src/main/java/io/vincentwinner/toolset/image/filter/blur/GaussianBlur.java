package io.vincentwinner.toolset.image.filter.blur;

import io.vincentwinner.toolset.image.util.MatUtil;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import java.io.InputStream;

import static io.vincentwinner.toolset.image.util.ConvolutionKernelCheckUtil.checkGaussianKernel;

/**
 * 高斯模糊滤镜
 */
public class GaussianBlur {

    /**
     * 将输入流中的图片做高斯模糊滤镜处理
     * @param inputStream 图片输入流
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 高斯模糊后的图像
     */
    public static Mat gaussianBlur(InputStream inputStream, int kernelWidth,int kernelHeight) {
        checkGaussianKernel(kernelWidth,kernelHeight);
        Mat src = MatUtil.inputStreamToMat(inputStream,4096);
        return gaussianConvolution(src,kernelWidth,kernelHeight);
    }

    /**
     * 从文件中读取图片并做高斯模糊处理
     * @param fileName 文件名
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 高斯模糊后的图像
     */
    public static Mat gaussianBlur(String fileName, int kernelWidth, int kernelHeight){
        checkGaussianKernel(kernelWidth,kernelHeight);
        Mat src = MatUtil.readFileToMat(fileName);
        return gaussianConvolution(src,kernelWidth,kernelHeight);
    }

    /**
     * 高斯卷积函数
     * @param src 源图像矩阵
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 处理后的矩阵
     */
    public static Mat gaussianConvolution(Mat src,int kernelWidth,int kernelHeight){
        Mat dst = new Mat();
        opencv_imgproc.GaussianBlur(src,dst,new Size(kernelWidth,kernelHeight),5);
        src.release();
        return dst;
    }

    /**
     * 高斯卷积函数
     * @param src 源图像矩阵
     * @param sigmaX σX
     * @param sigmaY σY
     * @return 处理后的矩阵
     */
    public static Mat gaussianConvolution(Mat src,double sigmaX,double sigmaY){
        Mat dst = new Mat();
        opencv_imgproc.GaussianBlur(src,dst,new Size(0,0),sigmaX,sigmaY,0);
        src.release();
        return dst;
    }

    /**
     * 高斯卷积函数
     * @param src 源图像矩阵
     * @param sigmaX σX
     * @return 处理后的矩阵
     */
    public static Mat gaussianConvolution(Mat src,double sigmaX){
        Mat dst = new Mat();
        opencv_imgproc.GaussianBlur(src,dst,new Size(0,0),sigmaX);
        src.release();
        return dst;
    }

}
