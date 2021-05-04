package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaPointF implements Serializable {

    private static final long serialVersionUID = -1801303342256845474L;

    private double x;
    private double y;

    public SeetaPointF() {
    }

    public SeetaPointF(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public SeetaPointF setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public SeetaPointF setY(double y) {
        this.y = y;
        return this;
    }

}
