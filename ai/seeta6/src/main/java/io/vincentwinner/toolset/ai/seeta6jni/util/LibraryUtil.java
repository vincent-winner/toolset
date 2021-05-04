package io.vincentwinner.toolset.ai.seeta6jni.util;

import com.sun.jna.Platform;
import io.vincentwinner.toolset.ai.seeta6jni.config.Seeta6Config;

import java.io.File;
import java.util.List;

public class LibraryUtil {

    private static final Seeta6Config config = Seeta6Config.getInstance();

    /**
     * 判断操作系统加载动态链接库
     * @param libNames 动态链接库名（不带 lib 和扩展名）
     */
    public static void judgePlatformLoadLibrary(List<String> libNames){
        if(Platform.isWindows()){
            libNames.forEach(l -> {
                File lib = new File(config.getLibraryRoot(),l + ".dll");
                System.load(lib.getAbsolutePath());
            });
        }else if(Platform.isLinux()){
            libNames.forEach(l -> {
                File lib = new File(config.getLibraryRoot(),"lib" + l + ".so");
                System.load(lib.getAbsolutePath());
            });
        }
    }

}
