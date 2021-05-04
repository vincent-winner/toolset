package io.vincentwinner.toolset.ai.seeta6jni;

import io.vincentwinner.toolset.ai.seeta6jni.config.FaceFunction;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaPointF;

import java.io.File;

/**
 * 人脸识别
 * 用于对比给定图像和目标图像的人脸相似度
 */
public class FaceRecognizer extends FaceFunction {

    protected FaceRecognizer(){ super(); }

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
        loadDependencies0(config.getFaceRecognizer());
    }

    /**
     * 加载人脸检测动态链接库
     * 加载jni动态链接库
     */
    @Override
    protected void loadLibrary() {
        loadLibrary0(config.getFaceRecognizer());
    }

    /**
     * 加载人脸检测模型
     */
    @Override
    protected void loadModel() {
        File modelFile = new File(config.getModelRoot(),(String) config.getFaceRecognizer().get("model"));
        loadModel(modelFile.getAbsolutePath());
        this.modelLoaded = true;
    }

    /**
     * 提取图片中的人脸特征
     * @param img 包含人脸的图像
     * @param landmarks 人脸关键点（5关键点即可）
     * @return 人脸特征
     */
    public native float[] extractFaceFeature(SeetaImageData img, SeetaPointF[] landmarks);

    /**
     * 比较人脸特征
     * @param feature1 人脸特征1
     * @param feature2 人脸特征2
     * @return 人脸特征相似度
     */
    public native float compare(float[] feature1,float[] feature2);

}
