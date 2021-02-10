package io.vincentwinner.toolset.os;

import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Memory {

    private Memory(){}
    private static abstract class MemoryInstance{
        private static final Memory INSTANCE = new Memory();
    }
    protected static Memory getInstance() {
        return MemoryInstance.INSTANCE;
    }

    private final GlobalMemory globalMemory = ((HardwareAbstractionLayer)(Ins$.getInstance().get(2))).getMemory();

    /**
     * 内存条硬件信息
     */
    public static final class MemoryHardwareInfo implements Serializable {
        private static final long serialVersionUID = 6013658930508617756L;
        private final String bankLabel;
        private final Long capacity;
        private final Long clockSpeed;
        private final String manufacturer;
        private final String memoryType;
        private MemoryHardwareInfo(PhysicalMemory physicalMemory) {
            this.bankLabel = physicalMemory.getBankLabel();
            this.capacity = physicalMemory.getCapacity();
            this.clockSpeed = physicalMemory.getClockSpeed();
            this.manufacturer = physicalMemory.getManufacturer();
            this.memoryType = physicalMemory.getMemoryType();
        }

        /**
         * @return 存储单元标签或插槽标签
         */
        public String getBankLabel() {
            return bankLabel;
        }

        /**
         * @return 内存容量
         */
        public Long getCapacity() {
            return capacity;
        }

        /**
         * @return 内存时钟频率
         */
        public Long getClockSpeed() {
            return clockSpeed;
        }

        /**
         * @return 制造商
         */
        public String getManufacturer() {
            return manufacturer;
        }

        /**
         * @return 内存类型（DDR几）
         */
        public String getMemoryType() {
            return memoryType;
        }

        @Override
        public String toString() {
            return "{" +
                    "内存标签='" + bankLabel + '\'' +
                    ", 容量=" + (capacity >> 30) +
                    "GiB, 时钟频率=" + (clockSpeed / 1000000) +
                    "MHz, 制造商='" + manufacturer + '\'' +
                    ", 内存类型='" + memoryType + '\'' +
                    '}';
        }
    }

    /**
     * 系统总共物理内存
     * @return 系统总共物理内存
     */
    public long physicalTotal(){
        return globalMemory.getTotal();
    }

    /**
     * 系统空闲物理内存
     * @return 系统空闲物理内存
     */
    public long physicalFree(){
        return globalMemory.getAvailable();
    }

    /**
     * 已经使用的物理内存空间
     * @return 已经使用的物理内存空间
     */
    public long physicalUsed(){
        return physicalTotal() - physicalFree();
    }

    /**
     * 物理内存使用率
     * @return 系统物理内存使用率
     */
    public strictfp double physicalUsage(){
        return (double) physicalUsed() / physicalTotal();
    }

    /**
     * Linux 内核系统下为内存交换分区大小
     * Windows 系统下为内存分页文件总大小
     * @return 内存交换分区大小
     */
    public long swapTotal(){
        return globalMemory.getVirtualMemory().getSwapTotal();
    }

    /**
     * Linux 内核系统下为内存交换分区空闲空间大小
     * Windows 系统下为内存分页文件空闲空间大小
     * @return 内存交换分区空闲空间大小
     */
    public long swapFree(){
        return swapTotal() - swapUsed();
    }

    /**
     * Linux 内核系统下为内存交换分区已经使用的空间大小
     * Windows 系统下为内存分页文件已经使用的空间大小
     * @return 内存交换分区已经使用的空间大小
     */
    public long swapUsed(){
        return globalMemory.getVirtualMemory().getSwapUsed();
    }

    /**
     * Linux 内核系统下为内存交换分区使用率
     * Windows 系统下为内存分页文件空间使用率
     * @return 内存交换分区使用率
     */
    public strictfp double swapUsage(){
        return (double) swapUsed() / swapTotal();
    }

    /**
     * 获取内存硬件信息
     * @return 内存条硬件信息
     */
    public List<MemoryHardwareInfo> hardwareInfo(){
        List<PhysicalMemory> physicalMemoryList = globalMemory.getPhysicalMemory();
        List<MemoryHardwareInfo> infoList = new ArrayList<>(physicalMemoryList.size());
        physicalMemoryList.forEach(mem -> { infoList.add(new MemoryHardwareInfo(mem)); });
        return infoList;
    }

    @Override
    public String toString() {
        String NEWLINE = OperatingSystem.getStaticSystemInfo().lineSeparator();
        List<MemoryHardwareInfo> mems = hardwareInfo();
        AtomicInteger count = new AtomicInteger();
        StringBuilder sb = new StringBuilder(1024);
        sb.append("------内存信息------").append(NEWLINE);
        sb.append("物理内存大小:   ").append(physicalTotal() >> 20).append(" Mib").append(NEWLINE);
        sb.append("物理内存已用:   ").append(physicalUsed() >> 20).append(" Mib").append(NEWLINE);
        sb.append("物理内存已用:   ").append((float) (physicalUsage() * 100)).append(" %").append(NEWLINE);
        sb.append("交换分区大小:   ").append(swapTotal() >> 20).append(" Mib").append(NEWLINE);
        sb.append("交换分区已用:   ").append(swapUsed() >> 20).append(" Mib").append(NEWLINE);
        sb.append("交换分区已用:   ").append((float) (swapUsage() * 100)).append(" %").append(NEWLINE);
        mems.forEach(mem -> { sb.append("内存插槽").append(count.getAndIncrement()).append(":     ").append(mem).append(NEWLINE); });
        return sb.toString();
    }
}
