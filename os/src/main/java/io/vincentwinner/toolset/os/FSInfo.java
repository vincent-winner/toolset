package io.vincentwinner.toolset.os;

import java.io.File;

/**
 * 文件系统信息
 */
public class FSInfo {

    private static final File userDir = new File(userDir());

    /**
     * @return 当前程序运行目录
     */
    public static String userDir(){
        return System.getProperty("user.dir");
    }

    /**
     * Windows  \r\n
     * Linux    \n
     * MacOS    \r
     * @return 当前系统行分隔符
     */
    public static String lineSeparator(){
        return System.getProperty("line.separator");
    }

    /**
     * Windows ;
     * Linux   :
     * MacOS   :
     * @return 当前系统路径分隔符
     */
    public static String pathSeparator(){
        return File.pathSeparator;
    }

    /**
     * Windows \
     * Linux   /
     * MacOs   /
     * @return 当前系统文件路径分隔符
     */
    public static String fileSeparator(){
        return File.separator;
    }

    /**
     * 单位： Bytes
     * @return 当前文件系统总空间
     */
    public static long totalSpace(){
        return userDir.getTotalSpace();
    }

    /**
     * 单位： Bytes
     * @return 当前文件系统空闲空间
     */
    public static long freeSpace(){
        return userDir.getFreeSpace();
    }

    /**
     * 单位： Bytes
     * @return 当前文件系统已经使用的空间
     */
    public static long usedSpace(){
        return totalSpace() - freeSpace();
    }

    /**
     * 0 ≤ 使用率 ≤ 1
     * @return 当前文件系统空间使用率
     */
    public static strictfp double usage(){
        return (double) usedSpace() / (double)totalSpace();
    }

}
