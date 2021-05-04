package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaPoint implements Serializable {

    private static final long serialVersionUID = 7679871339245889606L;

    private int x;
    private int y;

    public SeetaPoint() {
    }

    public SeetaPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public SeetaPoint setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public SeetaPoint setY(int y) {
        this.y = y;
        return this;
    }

}
