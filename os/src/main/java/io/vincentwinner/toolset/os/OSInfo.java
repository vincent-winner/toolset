package io.vincentwinner.toolset.os;

/**
 * 系统信息
 */
public class OSInfo {

    /**
     * @return 系统名称
     */
    public static String osName(){
        return System.getProperty("os.name");
    }

    /**
     * @return 系统架构
     */
    public static String arch(){
        return System.getProperty("os.arch");
    }

    /**
     * @return 系统版本
     */
    public static String osVersion(){
        return System.getProperty("os.version");
    }

    /**
     * 一般情况下可当做全部 cpu 内核数量
     * 数量包括 Intel 超线程核心
     * @return 可使用的 cpu 内核数量
     */
    public static int kernelNum(){
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * @return 系统用户名
     */
    public static String osUserName(){
        return System.getProperty("user.name");
    }

    /**
     * @return 当前系统用户主文件夹
     */
    public static String userHome(){
        return System.getProperty("user.home");
    }

    /**
     * @return 是否是 Windows 系统
     */
    public static boolean isWindows(){
        return osName().toLowerCase().startsWith("win") &&
                FSInfo.fileSeparator().equals("\\") &&
                FSInfo.pathSeparator().equals(";");
    }

    /**
     * @return 是否是 Linux 系统
     */
    public static boolean isLinux(){
        return osName().toLowerCase().startsWith("linux") &&
                FSInfo.fileSeparator().equals("/") &&
                FSInfo.pathSeparator().equals(":");
    }

}
