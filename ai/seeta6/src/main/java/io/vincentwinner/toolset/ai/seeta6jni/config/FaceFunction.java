package io.vincentwinner.toolset.ai.seeta6jni.config;

import io.vincentwinner.toolset.ai.seeta6jni.Seeta6JNI;
import io.vincentwinner.toolset.ai.seeta6jni.util.LibraryUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 人脸处理方法（人脸工具）
 */
public abstract class FaceFunction {

    protected FaceFunction(){
        loadDependencies();
        loadLibrary();
        loadModel();
    }

    protected final Seeta6Config config = Seeta6Config.getInstance();

    protected boolean libraryLoaded = false;
    protected boolean jniLoaded = false;
    protected boolean modelLoaded = false;

    /**
     * 加载依赖项
     */
    protected abstract void loadDependencies();

    /**
     * 加载依赖项
     * @param functionConfigs 人脸方法配置文件内容
     */
    @SuppressWarnings("unchecked")
    protected void loadDependencies0(Map<String,Object> functionConfigs){
        AtomicReference<List<String>> dependencies = new AtomicReference<List<String>>();
        functionConfigs.forEach((k,v) -> {
            if("dependsOn".equals(k)){
                dependencies.set((List<String>) v);
            }
        });
        if(dependencies.get() != null && dependencies.get().size() > 0){
            Seeta6JNI.loadFunctions(dependencies.get());
        }
    }

    /**
     * 加载功能动态链接库与jni动态链接库
     */
    protected abstract void loadLibrary();

    /**
     * 加载功能动态链接库与jni动态链接库
     */
    protected void loadLibrary0(Map<String, Object> functionConfigs){
        LibraryUtil.judgePlatformLoadLibrary(Collections.singletonList((String) functionConfigs.get("library")));
        this.libraryLoaded = true;
        LibraryUtil.judgePlatformLoadLibrary(Collections.singletonList((String) functionConfigs.get("jni")));
        this.jniLoaded = true;
    }

    /**
     * 加载模型
     */
    protected abstract void loadModel();

}
