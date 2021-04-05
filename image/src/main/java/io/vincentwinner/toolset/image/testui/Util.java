package io.vincentwinner.toolset.image.testui;

import io.vincentwinner.toolset.image.ImageExtension;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Util {

    private static final Java2DFrameConverter convertor = new Java2DFrameConverter();
    private static final OpenCVFrameConverter.ToMat toMatConverter = new OpenCVFrameConverter.ToMat();

    /**
     * 将 BufferedImage 转换为 JavaCV 矩阵对象
     * @param bufferedImage bufferedImage 对象
     * @return JavaCV
     */
    public static Mat bufferedImageToMat(BufferedImage bufferedImage){
        return toMatConverter.convert(convertor.getFrame(bufferedImage,1.0,true));
//        return Java2DFrameUtils.toMat(bufferedImage);
    }

    /**
     * 将 JavaCV 矩阵转换为 BufferedImage
     * @param mat JavaCV矩阵
     * @return BufferedImage
     */
    public static BufferedImage matToBufferedImage(Mat mat){
        return convertor.getBufferedImage(toMatConverter.convert(mat),1.0,false, ColorSpace.getInstance(ColorSpace.CS_sRGB));
//        return Java2DFrameUtils.toBufferedImage(mat);
    }

    /**
     * 将 JavaCV 矩阵转换为 BufferedImage
     * @param mat JavaCV矩阵
     * @return BufferedImage
     */
    public static BufferedImage mat2BufferedImage(Mat mat, ImageExtension extension){
        BytePointer buf = new BytePointer();
        Mat dst = new Mat();
        opencv_imgproc.cvtColor(mat,dst,opencv_imgproc.COLOR_BGRA2RGBA);
        mat.release();
        opencv_imgcodecs.imencode(extension.value(), dst, buf);
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

}
