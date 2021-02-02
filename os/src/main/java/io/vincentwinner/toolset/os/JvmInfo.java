package io.vincentwinner.toolset.os;

/**
 * java虚拟机信息
 */
public class JvmInfo {

    /**
     * @return java运行时环境版本
     */
    public static String javaVersion(){
        return System.getProperty("java.version");
    }

    /**
     * @return java安装目录
     */
    public static String javaHome(){
        return System.getProperty("java.home");
    }

    /**
     * @return 获取java类路径
     */
    public static String classpath(){
        return System.getProperty("java.class.path");
    }

    /**
     * @return 获取java库路径
     */
    public static String libraryPath(){
        return System.getProperty("library.path");
    }

    /**
     * @return 理论jvm总共使用内存
     */
    public static long jvmTotalMemory(){
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * @return jvm最大可使用内存
     */
    public static long jvmMaxMemory(){
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * @return jvm空闲内存
     */
    public static long jvmFreeMemory(){
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 0 ≤ 使用率 ≤ 1
     * @return jvm内存使用率
     */
    public static double jvmUsage(){
        return (double) jvmTotalMemory() / (double) jvmMaxMemory();
    }
}
