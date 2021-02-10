package io.vincentwinner.toolset.os;

import oshi.hardware.HardwareAbstractionLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 显卡信息
 */
public final class GraphicsCard {

    private GraphicsCard(){}
    private static abstract class GraphicsCardInstance{
        private static final GraphicsCard INSTANCE = new GraphicsCard();
    }
    protected static GraphicsCard getInstance() {
        return GraphicsCardInstance.INSTANCE;
    }

    private final List<oshi.hardware.GraphicsCard> cards = ((HardwareAbstractionLayer)(Ins$.getInstance().get(2))).getGraphicsCards();

    /**
     * 显卡信息
     * venndor    制造商
     * name       产品名称
     * version    版本
     * deviceId   设备id
     * vram       显存大小 (Video RAM)
     */
    public static final class GraphicsCardInfo implements Serializable{
        private static final long serialVersionUID = 5404137212318550910L;
        private final String vendor;
        private final String name;
        private final String version;
        private final String deviceId;
        private final Long vram;
        public GraphicsCardInfo(oshi.hardware.GraphicsCard g) {
            this.vendor = g.getVendor();
            this.name = g.getName();
            this.version = g.getVersionInfo();
            this.deviceId = g.getDeviceId();
            this.vram = g.getVRam();
        }
        public String getVendor() {
            return vendor;
        }
        public String getName() {
            return name;
        }
        public String getVersion() {
            return version;
        }
        public String getDeviceId() {
            return deviceId;
        }
        public Long getVram() {
            return vram;
        }

        @Override
        public String toString() {
            return "GraphicsCardInfo{" +
                    "制造商='" + vendor + '\'' +
                    ", 产品名称='" + name + '\'' +
                    ", 版本='" + version + '\'' +
                    ", 设备id='" + deviceId + '\'' +
                    ", 显存=" + (vram >> 20) + "Mib" +
                    '}';
        }
    }

    /**
     * 获取所有显卡信息
     * @return 所有显卡信息
     */
    public List<GraphicsCardInfo> getGraphicsCards(){
        List<GraphicsCardInfo> graphicsCardInfoList = new ArrayList<>(cards.size());
        cards.forEach(c -> { graphicsCardInfoList.add(new GraphicsCardInfo(c)); });
        return graphicsCardInfoList;
    }

    @Override
    public String toString() {
        String NEWLINE = OperatingSystem.getStaticSystemInfo().lineSeparator();
        AtomicInteger count = new AtomicInteger();
        StringBuilder sb = new StringBuilder(256);
        sb.append("------显卡信息------").append(NEWLINE);
        getGraphicsCards().forEach(g -> { sb.append("显卡").append(count.getAndIncrement()).append(":        ").append(g).append(NEWLINE); });
        return sb.toString();
    }

}
