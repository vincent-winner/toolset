package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaFaceInfo implements Serializable {

    private static final long serialVersionUID = 1122366922720149721L;

    private int x;
    private int y;
    private int width;
    private int height;
    private float score;

    public SeetaFaceInfo() {
    }

    public SeetaFaceInfo(int x, int y, int width, int height, float score) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

}
