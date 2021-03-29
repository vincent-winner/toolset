package io.vincentwinner.toolset.image.filter.blur;

import io.vincentwinner.toolset.image.DDepth;
import io.vincentwinner.toolset.image.util.MatUtil;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.io.InputStream;

import static io.vincentwinner.toolset.image.DDepth.ORIGINAL;

/**
 * 方框模糊
 */
public class BoxBlur {

    /**
     * 将输入流中的图像进行方框模糊滤镜处理
     * @param inputStream 图像输入流
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 处理后的图像矩阵信息
     */
    public static Mat boxBlur(InputStream inputStream,int kernelWidth,int kernelHeight){
        Mat src = MatUtil.inputStreamToMat(inputStream,4096);
        return boxConvolution(src,ORIGINAL,kernelWidth,kernelHeight);
    }

    /**
     * 将图片文件进行方框模糊滤镜处理
     * @param filename 图片文件路径
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 处理后的图像矩阵信息
     */
    public static Mat boxBlur(String filename,int kernelWidth,int kernelHeight){
        Mat src = MatUtil.readFileToMat(filename);
        return boxConvolution(src, ORIGINAL,kernelWidth,kernelHeight);
    }

    /**
     * 方框模糊卷积函数
     * @param src 源图片矩阵
     * @param ddepth ddepth
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @return 处理后的矩阵
     */
    public static Mat boxConvolution(Mat src, DDepth ddepth, int kernelWidth, int kernelHeight){
        Mat dst = new Mat();
        opencv_imgproc.boxFilter(src,dst,ddepth.value(),new Size(kernelWidth,kernelHeight));
        src.release();
        return dst;
    }

}
