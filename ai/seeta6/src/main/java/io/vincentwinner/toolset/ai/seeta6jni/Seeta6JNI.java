package io.vincentwinner.toolset.ai.seeta6jni;

import io.vincentwinner.toolset.ai.seeta6jni.config.Seeta6Config;
import io.vincentwinner.toolset.ai.seeta6jni.util.LibraryUtil;

import java.util.List;

public class Seeta6JNI {

    private Seeta6JNI(){}

    private static final Seeta6Config config = Seeta6Config.getInstance();

    static {
        loadCommonLibrary();
        loadFunctions(config.getLoadFunctions());
    }

    /**
     * 加载基本库
     * 所有功能都依赖基本库
     */
    private static void loadCommonLibrary(){
        LibraryUtil.judgePlatformLoadLibrary(config.getCommonLibrary());
    }

    /**
     * 根据配置文件中的 loadFunctions 加载人脸工具
     */
    public static void loadFunctions(List<String> functions){
        functions.forEach(f -> {
            switch (f) {
                case "faceDetector":
                    if(FaceFunctionInstance.faceDetector == null) FaceFunctionInstance.faceDetector = new FaceDetector();
                    break;
                case "faceLandmark":
                    if(FaceFunctionInstance.faceLandmark == null) FaceFunctionInstance.faceLandmark = new FaceLandmark();
                    break;
                case "faceLandmark68":
                    if(FaceFunctionInstance.faceLandmark68 == null) FaceFunctionInstance.faceLandmark68 = new FaceLandmark68();
                    break;
                case "faceRecognizer":
                    if(FaceFunctionInstance.faceRecognizer == null) FaceFunctionInstance.faceRecognizer = new FaceRecognizer();
                    break;
            }
        });
    }

    /**
     * 存放人脸工具实例
     */
    private static final class FaceFunctionInstance {
        private static FaceDetector faceDetector = null;
        private static FaceLandmark faceLandmark = null;
        private static FaceLandmark68 faceLandmark68 = null;
        private static FaceRecognizer faceRecognizer = null;
    }

    /**
     * 获取人脸检测器
     * 人脸检测器可以以矩形区域的形式表示人脸范围
     * @return 人脸检测器
     */
    public static FaceDetector getFaceDetector(){
        if(FaceFunctionInstance.faceDetector != null){
            return FaceFunctionInstance.faceDetector;
        }
        throw new RuntimeException("未启用的方法 faceDetector，请在配置文件中启用");
    }

    /**
     * 获取人脸关键点检测器（5点）
     * @return 人脸关键点检测器
     */
    public static FaceLandmark getFaceLandmark(){
        if(FaceFunctionInstance.faceLandmark != null){
            return FaceFunctionInstance.faceLandmark;
        }
        throw new RuntimeException("未启用的方法 faceLandmark，请在配置文件中启用");
    }

    /**
     * 获取人脸关键点检测器（68点）
     * @return 人脸关键点检测器
     */
    public static FaceLandmark68 getFaceLandmark68(){
        if(FaceFunctionInstance.faceLandmark68 != null){
            return FaceFunctionInstance.faceLandmark68;
        }
        throw new RuntimeException("未启用的方法 faceLandmark68，请在配置文件中启用");
    }

    /**
     * 获取人脸识别器
     * @return 人脸识别器
     */
    public static FaceRecognizer getFaceRecognizer(){
        if(FaceFunctionInstance.faceRecognizer != null){
            return FaceFunctionInstance.faceRecognizer;
        }
        throw new RuntimeException("未启用的方法 faceRecognizer，请在配置文件中启用");
    }

}
