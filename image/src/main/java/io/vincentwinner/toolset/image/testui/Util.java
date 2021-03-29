package io.vincentwinner.toolset.image.testui;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import java.awt.image.BufferedImage;

public class Util {

    private static final Java2DFrameConverter convertor = new Java2DFrameConverter();
    private static final OpenCVFrameConverter.ToMat toMatConverter = new OpenCVFrameConverter.ToMat();

    /**
     * 将 BufferedImage 转换为 JavaCV 矩阵对象
     * @param bufferedImage bufferedImage 对象
     * @return JavaCV
     */
    public static Mat bufferedImageToMat(BufferedImage bufferedImage){
        return toMatConverter.convert(convertor.convert(bufferedImage));
    }

    /**
     * 将 JavaCV 矩阵转换为 BufferedImage
     * @param mat JavaCV矩阵
     * @return BufferedImage
     */
    public static BufferedImage matToBufferedImage(Mat mat){
        return convertor.getBufferedImage(toMatConverter.convert(mat));
    }

}
