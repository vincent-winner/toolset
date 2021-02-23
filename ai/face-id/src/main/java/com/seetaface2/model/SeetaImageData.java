package com.seetaface2.model;

/**
 * 图像的宽高和通道信息
 * 为配合深度学习模型图像数据做线性化处理，直接读取自图片的数据无效
 */
public class SeetaImageData {

    public byte[] data;
    public int width;
    public int height;
    public int channels;

    public SeetaImageData() {

    }

    public SeetaImageData(int width, int height, int channels) {
        this.data = new byte[width * height * channels];
        this.width = width;
        this.height = height;
        this.channels = channels;
    }

    public SeetaImageData(int width, int height) {
        this(width, height, 3);
    }


}
