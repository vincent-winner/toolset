package io.vincentwinner.toolset.image.util;

import io.vincentwinner.toolset.image.ImageExtension;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mat 和其他 Java 对象转换工具
 */
public class MatUtil {

    private static final Java2DFrameConverter convertor = new Java2DFrameConverter();
    private static final OpenCVFrameConverter.ToMat toMatConverter = new OpenCVFrameConverter.ToMat();

    /**
     * 图片输入流转矩阵
     * 默认缓冲区大小 1024 byte
     * @param inputStream 图片输入流
     * @return 矩阵对象
     */
    public static org.opencv.core.Mat inputStreamToRawMat(InputStream inputStream){
        return inputStreamToRawMat(inputStream,4096);
    }

    /**
     * 图片输入流转矩阵
     * @param inputStream 图片输入流
     * @param bufferSize 缓冲区大小（字节）
     * @return 矩阵对象
     */
    public static org.opencv.core.Mat inputStreamToRawMat(InputStream inputStream, int bufferSize){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[bufferSize];
        int bytesRead = 0;
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) os.write(buffer, 0, bytesRead);
            os.flush();
            os.close();
            inputStream.close();
        }catch (IOException e){
            throw new RuntimeException("将 InputStream 转换为 Mat 失败",e);
        }
        org.opencv.core.Mat encoded = new org.opencv.core.Mat(1, os.size(), 0);
        encoded.put(0, 0, os.toByteArray());
        org.opencv.core.Mat decoded = Imgcodecs.imdecode(encoded, -1);
        encoded.release();
        return decoded;
    }

    /**
     * 图片输入流转矩阵
     * @param inputStream 图片输入流
     * @param bufferSize 缓冲区大小
     * @return 图片矩阵
     */
    public static Mat inputStreamToMat(InputStream inputStream,int bufferSize){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[bufferSize];
        int b = 0;
        try {
            while ((b = inputStream.read(buffer)) != -1) os.write(buffer, 0, b);
        }catch (Exception e){
            throw new RuntimeException("将 InputStream 转换为 Mat 失败",e);
        }
        Mat encoded = new Mat(os.toByteArray());
        Mat decoded = opencv_imgcodecs.imdecode(encoded,-1);
        encoded.release();
        return decoded;
    }

    /**
     * 将图片文件读取为矩阵
     * @param fileName 文件名
     * @return 矩阵对象
     */
    public static Mat readFileToMat(String fileName){
        return opencv_imgcodecs.imread(fileName);
    }

    /**
     * 将矩阵对象转换为图片写入到文件中
     * @param src 矩阵对象
     * @param fileName 文件名
     */
    public static void writeMatToFile(Mat src,String fileName){
        opencv_imgcodecs.imwrite(fileName,src);
    }

    /**
     * 将 JavaCV 矩阵转换为 BufferedImage
     * @param mat JavaCV矩阵
     * @return BufferedImage
     */
    public static BufferedImage mat2BufferedImage(Mat mat, ImageExtension extension){
        BytePointer buf = new BytePointer();
        opencv_imgcodecs.imencode(extension.value(), mat, buf);
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
//        return convertor.getBufferedImage(toMatConverter.convert(mat),1.0,false, ColorSpace.getInstance(ColorSpace.CS_sRGB));
        return Java2DFrameUtils.toBufferedImage(mat);
    }

}
