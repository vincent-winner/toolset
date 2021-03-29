package io.vincentwinner.toolset.image.util;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mat 和其他 Java 对象转换工具
 */
public class MatUtil {

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

}
