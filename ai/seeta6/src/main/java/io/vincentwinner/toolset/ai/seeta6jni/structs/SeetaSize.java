package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaSize implements Serializable {

    private static final long serialVersionUID = 2703933072802352976L;

    private int width;
    private int height;

    public SeetaSize() {
    }

    public SeetaSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public SeetaSize setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public SeetaSize setHeight(int height) {
        this.height = height;
        return this;
    }

}
