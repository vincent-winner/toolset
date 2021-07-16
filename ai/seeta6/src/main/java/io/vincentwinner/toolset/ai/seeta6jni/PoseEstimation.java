package io.vincentwinner.toolset.ai.seeta6jni;

import io.vincentwinner.toolset.ai.seeta6jni.config.FaceFunction;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaAngle;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;
import io.vincentwinner.toolset.ai.seeta6jni.util.LibraryUtil;

import java.io.File;
import java.util.Collections;

public class PoseEstimation extends FaceFunction {

    protected PoseEstimation() {
        super();
    }

    /**
     * 加载模型
     * 模型路径可以填写绝对路径或相对于当前项目的相对路径
     * @param modelPath 模型路径
     */
    private native void loadModel(String modelPath);

    /**
     * 加载依赖组件
     */
    @Override
    protected void loadDependencies() {
        loadDependencies0(config.getPoseEstimation());
    }

    /**
     * 加载人脸检测动态链接库
     * 加载jni动态链接库
     */
    @Override
    protected void loadLibrary() {
        LibraryUtil.judgePlatformLoadLibrary(Collections.singletonList((String) config.getPoseEstimation().get("library")));
        this.libraryLoaded = true;
        LibraryUtil.judgePlatformLoadLibrary(Collections.singletonList((String) config.getPoseEstimation().get("jni")));
        this.jniLoaded = true;
    }

    /**
     * 加载人脸检测模型
     */
    @Override
    protected void loadModel() {
        File modelFile = new File(config.getModelRoot(),(String) config.getPoseEstimation().get("model"));
        loadModel(modelFile.getAbsolutePath());
        this.modelLoaded = true;
    }

    /**
     * 估计人脸姿态
     * @param imageData 图片数据
     * @param seetaRect 人脸位置
     * @return 人脸姿态角（欧拉角 yaw, pitch, roll）
     */
    public native SeetaAngle estimationPose(SeetaImageData imageData, SeetaRect seetaRect);

}
