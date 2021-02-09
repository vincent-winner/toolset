package io.vincentwinner.toolset.os;

/**
 * java虚拟机信息
 */
public class JavaVirtualMachine {

    private JavaVirtualMachine(){}
    private static final class JVMInstance{
        private static final JavaVirtualMachine JVM = new JavaVirtualMachine();
    }
    public static JavaVirtualMachine getInstance() {
        return JVMInstance.JVM;
    }

    /**
     * @return java运行时环境版本
     */
    public String javaVersion(){
        return System.getProperty("java.version");
    }

    /**
     * @return java安装目录
     */
    public String javaHome(){
        return System.getProperty("java.home");
    }

    /**
     * @return 获取java类路径
     */
    public String classpath(){
        return System.getProperty("java.class.path");
    }

    /**
     * @return 获取java库路径
     */
    public String libraryPath(){
        return System.getProperty("library.path");
    }

    /**
     * @return 理论jvm总共使用内存
     */
    public long jvmTotalMemory(){
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * @return jvm最大可使用内存
     */
    public long jvmMaxMemory(){
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * @return jvm空闲内存
     */
    public long jvmFreeMemory(){
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 0 ≤ 使用率 ≤ 1
     * @return jvm内存使用率
     */
    public double jvmUsage(){
        return (double) jvmTotalMemory() / (double) jvmMaxMemory();
    }


}
