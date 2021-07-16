package io.vincentwinner.toolset.ai.seeta6jni;

import io.vincentwinner.toolset.ai.seeta6jni.config.FaceFunction;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;

import java.io.File;

/**
 * 人脸关键点检测器（5点）
 */
public class FaceLandmark extends FaceFunction {

    protected FaceLandmark(){
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
        loadDependencies0(config.getFaceLandmark());
    }

    /**
     * 加载人脸检测动态链接库
     * 加载jni动态链接库
     */
    @Override
    protected void loadLibrary() {
        loadLibrary0(config.getFaceLandmark());
    }

    /**
     * 加载人脸检测模型
     */
    @Override
    protected void loadModel() {
        File modelFile = new File(config.getModelRoot(),(String) config.getFaceLandmark().get("model"));
        loadModel(modelFile.getAbsolutePath());
        this.modelLoaded = true;
    }

    /**
     * 人脸关键点检测（5点）
     * @param imageData 图像信息
     * @param face 人脸区域
     * @return 人脸关键点数组
     */
    public native SeetaPointF[] mark(SeetaImageData imageData, SeetaRect face);

}
