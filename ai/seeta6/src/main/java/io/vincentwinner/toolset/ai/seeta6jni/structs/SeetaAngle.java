package io.vincentwinner.toolset.ai.seeta6jni.structs;

import java.io.Serializable;

public class SeetaAngle implements Serializable {

    private static final long serialVersionUID = 8773292494644781073L;

    private float yaw;
    private float pitch;
    private float roll;

    public SeetaAngle() { }

    public SeetaAngle(float yaw, float pitch, float roll) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

}
