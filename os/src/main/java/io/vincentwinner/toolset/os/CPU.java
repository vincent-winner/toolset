package io.vincentwinner.toolset.os;

import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.Serializable;

/**
 * CPU 信息
 */
public final class CPU {

    private CPU(){}
    private static abstract class CPUInstance{
        private static final CPU CPU = new CPU();
    }
    protected static CPU getInstance() {
        return CPUInstance.CPU;
    }

    /**
     * 静态 CPU 信息
     * 频率未知值为 -1
     * vendor             制造商
     * name               名称
     * family             cpu产品族
     * logicalCoreNum     逻辑核心数量
     * physicalCoreNum    物理核心数量
     * model              型号
     * stepping           步进
     * vendorFreq         出厂频率
     * maxFreq            最大频率
     * is64Bit            是否为64位CPU
     */
    public static final class CPUHardWareInfo implements Serializable{
        private static final long serialVersionUID = 9078007820508242638L;
        private final String vendor;
        private final String name;
        private final String family;
        private final Integer logicalCoreNum;
        private final Integer physicalCoreNum;
        private final String model;
        private final String stepping;
        private final Long vendorFreq;
        private final Long maxFreq;
        private final Boolean is64Bit;
        public CPUHardWareInfo(CentralProcessor c) {
            CentralProcessor.ProcessorIdentifier i = c.getProcessorIdentifier();
            this.vendor = i.getVendor();
            this.name = i.getName();
            this.family = i.getFamily();
            this.logicalCoreNum = c.getLogicalProcessorCount();
            this.physicalCoreNum = c.getPhysicalProcessorCount();
            this.model = i.getModel();
            this.stepping = i.getStepping();
            this.vendorFreq = i.getVendorFreq();
            this.maxFreq = c.getMaxFreq();
            this.is64Bit = i.isCpu64bit();
        }
        public String getVendor() {
            return vendor;
        }
        public String getName() {
            return name;
        }
        public String getFamily() {
            return family;
        }
        public Integer getLogicalCoreNum() {
            return logicalCoreNum;
        }
        public Integer getPhysicalCoreNum() {
            return physicalCoreNum;
        }
        public String getModel() {
            return model;
        }
        public String getStepping() {
            return stepping;
        }
        public Long getVendorFreq() {
            return vendorFreq;
        }
        public Long getMaxFreq() {
            return maxFreq;
        }
        public Boolean is64Bit() {
            return is64Bit;
        }

        @Override
        public String toString() {
            String NEWLINE = OperatingSystem.getStaticSystemInfo().lineSeparator();
            StringBuilder sb = new StringBuilder(256);
            sb.append("处理器制造商:   ").append(vendor).append(NEWLINE);
            sb.append("处理器产品名:   ").append(name).append(NEWLINE);
            sb.append("处理器产品族:   ").append(family).append(NEWLINE);
            sb.append("逻辑核心数量:   ").append(logicalCoreNum).append(NEWLINE);
            sb.append("物理核心数量:   ").append(physicalCoreNum).append(NEWLINE);
            sb.append("产品型号信息:   ").append(model).append(NEWLINE);
            sb.append("产品步进信息:   ").append(stepping).append(NEWLINE);
            sb.append("产品出厂频率:   ").append(vendorFreq / 1.0E09f).append("GHz").append(NEWLINE);
            sb.append("产品最大频率:   ").append(maxFreq / 1.0E09f).append("GHz").append(NEWLINE);
            sb.append("是否支持64bit: ").append(is64Bit ? "是" : "否").append(NEWLINE);
            return sb.toString();
        }
    }

    /**
     * 获取静态 CPU 信息
     * @return 静态CPU信息
     */
    public CPUHardWareInfo getCPUInfo(){
        return new CPUHardWareInfo(((HardwareAbstractionLayer)(Ins$.getInstance().get(2))).getProcessor());
    }

    /**
     * 获取当前 cpu 使用率
     * @return cpu使用率
     */
    public double usage(){
        return ((HardwareAbstractionLayer)(Ins$.getInstance().get(2))).getProcessor().getSystemCpuLoadBetweenTicks(new long[]{10L,10L,10L,10L,10L,10L,10L,10L});
    }

    @Override
    public String toString() {
        String NEWLINE = OperatingSystem.getStaticSystemInfo().lineSeparator();
        return "------处理器信息------" + NEWLINE +
                getCPUInfo() +
                "处理器使用率:   " + (float)usage() * 100 + "%"
                + NEWLINE;
    }
}
