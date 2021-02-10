package io.vincentwinner.toolset.os;

import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;

public class Computer {

    private Computer(){}
    private static final ComputerSystem cs = ((HardwareAbstractionLayer) (Ins$.getInstance().get(2))).getComputerSystem();

    /**
     * 获取当前计算机序列号
     * @return 当前计算机序列号
     */
    public static String getSerialNumber(){
        return cs.getSerialNumber();
    }

    /**
     * 获取当前计算机硬件唯一标识
     * @return 当前计算机硬件唯一标识
     */
    public static String getHardwareUUID(){
        return cs.getHardwareUUID();
    }

    /**
     * 获取计算机厂商
     * @return 计算机制造商
     */
    public static String getManufacturer(){
        return cs.getManufacturer();
    }

    /**
     * 获取计算机型号
     * @return 计算机型号
     */
    public static String getComputerModel(){
        return cs.getModel();
    }

    /**
     * 获取CPU信息
     * @return CPU
     */
    public static CPU getCPU(){
        return CPU.getInstance();
    }

    /**
     * 获取磁盘信息
     * @return 磁盘
     */
    public static Disk getDisk(){
        return Disk.getInstance();
    }

    /**
     * 获取内存信息
     * @return 内存
     */
    public static Memory getMemory(){
        return Memory.getInstance();
    }

    /**
     * 获取操作系统信息
     * @return 操作系统
     */
    public static OperatingSystem getOperatingSystem(){
        return OperatingSystem.getInstance();
    }

    /**
     * 获取显卡信息
     * @return 显卡
     */
    public static GraphicsCard getGraphicsCard(){
        return GraphicsCard.getInstance();
    }

    /**
     * 获取声卡信息
     * @return 声卡
     */
    public static SoundCard getSoundCard(){
        return SoundCard.getInstance();
    }

    /**
     * 获取描述计算机硬件信息的字符串
     * @return 用于描述计算机硬件信息的字符串
     */
    public static String computerInfoString(){
        String NEWLINE = OperatingSystem.getStaticSystemInfo().lineSeparator();
        StringBuilder sb = new StringBuilder(10240);
        sb.append("------计算机信息------").append(NEWLINE);
        sb.append("制造厂商:      ").append(getManufacturer()).append(NEWLINE);
        sb.append("电脑型号:      ").append(getComputerModel()).append(NEWLINE);
        sb.append("序列编号:      ").append(getSerialNumber()).append(NEWLINE);
        sb.append("硬件标识:      ").append(getHardwareUUID()).append(NEWLINE);
        return sb.append(NEWLINE)
                .append(getOperatingSystem()).append(NEWLINE)
                .append(getCPU()).append(NEWLINE)
                .append(getMemory()).append(NEWLINE)
                .append(getDisk()).append(NEWLINE)
                .append(getGraphicsCard()).append(NEWLINE)
                .append(getSoundCard()).append(NEWLINE)
                .toString();
    }

}
