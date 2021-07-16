package io.vincentwinner.toolset.ai.seeta6jni.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Seeta6人脸工具配置类
 * 如果类路径根目录中有用户自定义的 seeta6jni-config.json 则读取该文件
 * 否则使用本工具内置的 seeta6jni-config-default.json 作为默认配置文件
 */
public class Seeta6Config implements Serializable {

    private static final long serialVersionUID = 5716495960524856052L;

    static {
        ClassLoader classLoader = Seeta6Config.class.getClassLoader();
        InputStream configStream = classLoader.getResourceAsStream("seeta6jni-config.json");
        Seeta6Config config = null;
        try {
            if (configStream == null) {
                configStream = classLoader.getResourceAsStream("seeta6jni-config-default.json");
            }
            Seeta6ConfigInstance.INSTANCE = new ObjectMapper().readValue(configStream,Seeta6Config.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 模型目录
     */
    private String modelRoot;

    /**
     * 动态链接库目录
     */
    private String libraryRoot;

    /**
     * 常驻线程池线程数量
     */
    private Integer corePoolSize;

    /**
     * 线程池最大线程数量
     */
    private Integer maxPoolSize;

    /**
     * 线程池空闲线程存活时间
     */
    private Integer keepAliveTime;

    /**
     * 阻塞队列大小
     */
    private Integer blockQueueSize;

    /**
     * 基本库，所有模块均需要用到的库，需要最先加载
     */
    private List<String> commonLibrary;

    /**
     * 开启的功能
     */
    private List<String> loadFunctions;

    /**
     * 人脸检测模块 FaceDetector 配置
     */
    private Map<String,Object> faceDetector;

    /**
     * 人脸关键点检测（5点）配置
     */
    private Map<String, Object> faceLandmark;

    /**
     * 人脸关键点检测（68点）配置
     */
    private Map<String, Object> faceLandmark68;

    /**
     * 人脸识别（比较）配置
     */
    private Map<String, Object> faceRecognizer;

    /**
     * 人脸姿态估计 PoseEstimation 配置
     */
    private Map<String,Object> poseEstimation;

    private Seeta6Config(){
    }

    private static class Seeta6ConfigInstance{
        private static Seeta6Config INSTANCE = new Seeta6Config();
    }

    /**
     * 获取配置实例
     * @return 配置实例
     */
    public static Seeta6Config getInstance() {
        return Seeta6ConfigInstance.INSTANCE;
    }

    /**
     * 获取模型存放目录
     * @return 模型目录
     */
    public String getModelRoot() {
        return modelRoot;
    }

    /**
     * 获取动态链接库存放目录
     * @return 动态链接库目录
     */
    public String getLibraryRoot() {
        return libraryRoot;
    }

    /**
     * 获取常驻线程池线程数量
     * @return 常驻线程池线程数量
     */
    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * 获取线程池最大线程数量
     * @return 线程池最大线程数量
     */
    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * 获取空闲线程存活时间
     * @return 空闲线程存活时间
     */
    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    /**
     * 获取阻塞队列大小
     * @return 阻塞队列大小
     */
    public Integer getBlockQueueSize() {
        return blockQueueSize;
    }

    /**
     * 获取基本库列表
     * 所有模块均依赖基本库，基本库应在其他库加载前加载
     * @return 基本库
     */
    public List<String> getCommonLibrary() {
        return commonLibrary;
    }

    /**
     * 获取开启功能列表
     * @return 开启功能列表
     */
    public List<String> getLoadFunctions() {
        return loadFunctions;
    }

    /**
     * 获取人脸检测模块（FaceDetector）配置
     * @return 人脸检测配置
     */
    public Map<String, Object> getFaceDetector() {
        return faceDetector;
    }

    /**
     * 获取人脸关键点检测（5点）模块（FaceLandmark）配置
     * @return 人脸关键点检测配置
     */
    public Map<String, Object> getFaceLandmark() {
        return faceLandmark;
    }

    /**
     * 获取人脸检测（FaceRecognizer）配置
     * @return 人脸检测配置
     */
    public Map<String, Object> getFaceRecognizer() {
        return faceRecognizer;
    }

    /**
     * 获取人脸关键点检测（FaceLandmark68）
     * @return 人脸关键点检测配置
     */
    public Map<String, Object> getFaceLandmark68() {
        return faceLandmark68;
    }

    /**
     * 获取人脸姿态估计（PoseEstimation）配置
     * @return 人脸姿态估计配置
     */
    public Map<String,Object> getPoseEstimation(){
        return poseEstimation;
    }

    @Override
    public String toString() {
        return "Seeta6Config{" +
                "modelRoot='" + modelRoot + '\'' +
                ", libraryRoot='" + libraryRoot + '\'' +
                ", corePoolSize=" + corePoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", keepAliveTime=" + keepAliveTime +
                ", blockQueueSize=" + blockQueueSize +
                ", commonLibrary=" + commonLibrary +
                ", loadFunctions=" + loadFunctions +
                ", faceDetector=" + faceDetector +
                ", faceLandmark=" + faceLandmark +
                ", faceLandmark68=" + faceLandmark68 +
                ", faceRecognizer=" + faceRecognizer +
                ", poseEstimation=" + poseEstimation +
                '}';
    }
}
