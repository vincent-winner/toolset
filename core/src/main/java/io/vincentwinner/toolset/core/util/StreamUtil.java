package io.vincentwinner.toolset.core.util;

import java.io.*;

public class StreamUtil {

    /**
     * 将输入流转换为输出流
     * @param in 输入流
     * @return 输出流
     */
    public static OutputStream inToOut(InputStream in) {
        int b = 0;
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            while ((b = in.read()) != -1) {
                outputStream.write(b);
            }
        } catch (Exception e) {
            throw new RuntimeException("输入流转换为输出流时发生错误", e);
        }
        return outputStream;
    }

    /**
     * 将输入流转换为输出流
     * @param in 输入流
     * @param bufferSize 缓冲区大小
     * @return 输出流
     */
    public static OutputStream inToOutWithBuffer(InputStream in,int bufferSize){
        byte[] buf = new byte[bufferSize];
        int b;
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            while ((b = in.read(buf)) > 0) {
                outputStream.write(buf,0,b);
            }
        } catch (Exception e) {
            throw new RuntimeException("输入流转换为输出流时发生错误", e);
        }
        return outputStream;
    }

    /**
     * 将输入流转储到文件当中
     * @param in 输入流
     * @param file 输入流将保存到此文件
     * @param bufferSize 缓冲区大小
     * @return 是否转储成功
     */
    public static boolean saveInToFile(InputStream in, File file, int bufferSize){
        if(file == null){
            return false;
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buf = new byte[bufferSize];
            int n;
            while ((n = in.read(buf)) > 0) {
                outputStream.write(buf, 0, n);
            }
        } catch (Exception e) {
            System.err.println(file.getAbsolutePath());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 将输入流转换为二进制数组
     * @param input 输入流
     * @return 二进制数组
     */
    public static byte[] inToByteArray(InputStream input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        try {
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }catch (Exception e){
            throw new RuntimeException("输入流转换为输出流时发生错误", e);
        }
        return output.toByteArray();
    }


}
