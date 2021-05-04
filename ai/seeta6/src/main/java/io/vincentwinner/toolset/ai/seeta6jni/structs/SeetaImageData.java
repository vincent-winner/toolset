package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaImageData implements Serializable {

    private static final long serialVersionUID = -4176677218956731086L;

    private int width;
    private int height;
    private int channels;
    private byte[] data;

    public SeetaImageData() {
    }

    public SeetaImageData(int width, int height, int channels, byte[] data) {
        this.width = width;
        this.height = height;
        this.channels = channels;
        this.data = data;
    }

    public int getWidth() {
        return width;
    }

    public SeetaImageData setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public SeetaImageData setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getChannels() {
        return channels;
    }

    public SeetaImageData setChannels(int channels) {
        this.channels = channels;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public SeetaImageData setData(byte[] data) {
        this.data = data;
        return this;
    }

}
