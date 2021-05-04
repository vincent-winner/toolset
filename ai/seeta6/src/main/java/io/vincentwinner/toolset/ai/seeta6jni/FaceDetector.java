package io.vincentwinner.toolset.ai.seeta6jni;

import io.vincentwinner.toolset.ai.seeta6jni.config.FaceFunction;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.util.LibraryUtil;

import java.io.File;
import java.util.Collections;

/**
 * 人脸检测器
 * 检测人脸矩形范围
 */
public class FaceDetector extends FaceFunction {

    protected FaceDetector(){
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
        loadDependencies0(config.getFaceDetector());
    }

    /**
     * 加载人脸检测模型
     */
    @Override
    protected void loadModel(){
        File modelFile = new File(config.getModelRoot(),(String) config.getFaceDetector().get("model"));
        loadModel(modelFile.getAbsolutePath());
        this.modelLoaded = true;
    }

    /**
     * 加载人脸检测动态链接库
     * 加载jni动态链接库
     */
    @Override
    protected void loadLibrary(){
        LibraryUtil.judgePlatformLoadLibrary(Collections.singletonList((String) config.getFaceDetector().get("library")));
        this.libraryLoaded = true;
        LibraryUtil.judgePlatformLoadLibrary(Collections.singletonList((String) config.getFaceDetector().get("jni")));
        this.jniLoaded = true;
    }

    /**
     * 检测图片中的人脸位置
     * @param imageData 图片数据
     * @return 人脸位置
     */
    public native synchronized SeetaFaceInfo[] detect(SeetaImageData imageData);

}
