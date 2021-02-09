package io.vincentwinner.toolset.os;

import java.io.File;

/**
 * 文件系统信息
 */
public class Disk {

    private Disk(){}
    private static final class DiskInstance{
        private static final Disk DISK = new Disk();
    }
    public static Disk getInstance() {
        return DiskInstance.DISK;
    }

    private static final File userDir = new File(userDir());

    /**
     * @return 当前程序运行目录
     */
    public static String userDir(){
        return System.getProperty("user.dir");
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
