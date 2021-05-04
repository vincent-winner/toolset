package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaRect implements Serializable {

    private static final long serialVersionUID = -8096142208816535610L;

    private int x;
    private int y;
    private int width;
    private int height;

    public SeetaRect() {
    }

    public SeetaRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public SeetaRect(SeetaFaceInfo faceInfo){
        this.x = faceInfo.getX();
        this.y = faceInfo.getY();
        this.width = faceInfo.getWidth();
        this.height = faceInfo.getHeight();
    }

    public int getX() {
        return x;
    }

    public SeetaRect setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public SeetaRect setY(int y) {
        this.y = y;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public SeetaRect setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public SeetaRect setHeight(int height) {
        this.height = height;
        return this;
    }
}
