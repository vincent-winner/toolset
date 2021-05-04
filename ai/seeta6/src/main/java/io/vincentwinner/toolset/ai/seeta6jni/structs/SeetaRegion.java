package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaRegion implements Serializable {

    private static final long serialVersionUID = 6740238915308653799L;

    private int top;
    private int bottom;
    private int left;
    private int right;

    public SeetaRegion() {
    }

    public SeetaRegion(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public SeetaRegion setTop(int top) {
        this.top = top;
        return this;
    }

    public int getBottom() {
        return bottom;
    }

    public SeetaRegion setBottom(int bottom) {
        this.bottom = bottom;
        return this;
    }

    public int getLeft() {
        return left;
    }

    public SeetaRegion setLeft(int left) {
        this.left = left;
        return this;
    }

    public int getRight() {
        return right;
    }

    public SeetaRegion setRight(int right) {
        this.right = right;
        return this;
    }

}
