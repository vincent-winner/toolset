package io.vincentwinner.toolset.os;

import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件系统信息
 */
public class Disk {

    private Disk(){}
    private static abstract class DiskInstance{
        private static final Disk DISK = new Disk();
    }
    public static Disk getInstance() {
        return DiskInstance.DISK;
    }

    private final File userDir = new File(System.getProperty("user.dir"));
    private final List<OSFileStore> osFileStoreList = ((OperatingSystem)(Ins$.getInstance().get(1))).getFileSystem().getFileStores();

    /**
     * 磁盘硬件信息
     * name            磁盘名称
     * volume          磁盘卷标识
     * label           磁盘标签
     * logicVolume     逻辑卷
     * mountPoint      挂载点
     * description     描述信息
     * type            文件系统类型
     * options         磁盘参数
     * uuid            磁盘唯一标识
     * freeSpace       空闲空间
     * totalSpace      总空间
     * usedSpace       已使用空间
     * usage           空间使用率
     */
    public static final class DiskHardwareInfo implements Serializable {
        private static final Long serialVersionUID = 3996093018536610267L;
        private final String name;
        private final String volume;
        private final String label;
        private final String logicVolume;
        private final String mountPoint;
        private final String description;
        private final String type;
        private final String options;
        private final String uuid;
        private final Long freeSpace;
        private final Long totalSpace;
        private final Long usedSpace;
        private final Double usage;
        public DiskHardwareInfo(OSFileStore f) {
            this.name = f.getName();
            this.volume = f.getVolume();
            this.label = f.getLabel();
            this.logicVolume = f.getLogicalVolume();
            this.mountPoint = f.getMount();
            this.description = f.getDescription();
            this.type = f.getType();
            this.options = f.getOptions();
            this.uuid = f.getUUID();
            this.freeSpace = f.getFreeSpace();
            this.totalSpace = f.getTotalSpace();
            this.usedSpace = totalSpace - freeSpace;
            this.usage = (double) freeSpace / totalSpace;
        }
        public String getName() {
            return name;
        }
        public String getVolume() {
            return volume;
        }
        public String getLabel() {
            return label;
        }
        public String getLogicVolume() {
            return logicVolume;
        }
        public String getMountPoint() {
            return mountPoint;
        }
        public String getDescription() {
            return description;
        }
        public String getType() {
            return type;
        }
        public String getOptions() {
            return options;
        }
        public String getUuid() {
            return uuid;
        }
        public Long getFreeSpace() {
            return freeSpace;
        }
        public Long getTotalSpace() {
            return totalSpace;
        }
        public Long getUsedSpace() {
            return usedSpace;
        }
        public strictfp Double getUsage() {
            return usage;
        }

        @Override
        public String toString() {
            return "{" +
                    "磁盘名称='" + name + '\'' +
                    ", 卷='" + volume + '\'' +
                    ", 标签='" + label + '\'' +
                    ", 逻辑卷='" + logicVolume + '\'' +
                    ", 挂载点='" + mountPoint + '\'' +
                    ", 描述='" + description + '\'' +
                    ", 类型='" + type + '\'' +
                    ", 参数='" + options + '\'' +
                    ", 唯一标识='" + uuid + '\'' +
                    ", 空闲空间=" + freeSpace +
                    ", 总空间=" + totalSpace +
                    ", 已使用空间=" + usedSpace +
                    ", 空间使用率=" + usage +
                    '}';
        }
    }

    /**
     * 单位： Bytes
     * 获取指定磁盘编号的总空间，如果磁盘编号小于等于 0，则获取当前程序所在磁盘的总空间
     * @param diskNum 磁盘编号
     * @return 指定磁盘总空间
     */
    public long totalSpace(int diskNum){
        long result = -1L;
        if(diskNum > 0){
            try{
                result = osFileStoreList.get(diskNum - 1).getTotalSpace();
            }catch (ArrayIndexOutOfBoundsException e){
                throw new IllegalArgumentException("不存在的磁盘: 磁盘" + diskNum,e);
            }
        }else {
            result = userDir.getTotalSpace();
        }
        return result;
    }

    /**
     * 单位： Bytes
     * 获取指定磁盘编号的空闲空间，如果磁盘编号小于等于 0，则获取当前程序所在磁盘的空闲空间
     * @param diskNum 磁盘编号
     * @return 当前程序所在磁盘空闲空间
     */
    public long freeSpace(int diskNum){
        long result = -1L;
        if(diskNum > 0){
            try{
                result = osFileStoreList.get(diskNum - 1).getFreeSpace();
            }catch (ArrayIndexOutOfBoundsException e){
                throw new IllegalArgumentException("不存在的磁盘: 磁盘" + diskNum,e);
            }
        }else {
            result = userDir.getFreeSpace();
        }
        return result;
    }

    /**
     * 单位： Bytes
     * 获取指定磁盘编号的已用空间，如果磁盘编号小于等于 0，则获取当前程序所在磁盘的已用空间
     * @param diskNum 磁盘编号
     * @return 当前程序所在磁盘已经使用的空间
     */
    public long usedSpace(int diskNum){
        return totalSpace(diskNum) - freeSpace(diskNum);
    }

    /**
     * 0 ≤ 使用率 ≤ 1
     * 获取指定磁盘编号的磁盘使用率，如果磁盘编号小于等于 0，则获取当前程序所在磁盘的磁盘使用率
     * @param diskNum 磁盘编号
     * @return 当前程序所在磁盘空间使用率
     */
    public strictfp double usage(int diskNum){
        return (double) usedSpace(diskNum) / totalSpace(diskNum);
    }

    /**
     * 获取磁盘信息列表
     * @return 磁盘信息列表
     */
    public List<DiskHardwareInfo> getDiskList(){
        List<DiskHardwareInfo> list = new ArrayList<>(osFileStoreList.size());
        osFileStoreList.forEach(d -> { list.add(new DiskHardwareInfo(d)); });
        return list;
    }

    @Override
    public String toString() {
        String NEWLINE = io.vincentwinner.toolset.os.OperatingSystem.getStaticSystemInfo().lineSeparator();
        AtomicInteger count = new AtomicInteger();
        StringBuilder sb = new StringBuilder(2048);
        sb.append("------磁盘信息------").append(NEWLINE);
        getDiskList().forEach(d -> { sb.append("磁盘").append(count.getAndIncrement()).append(":        ").append(d).append(NEWLINE); });
        return sb.toString();
    }
}
