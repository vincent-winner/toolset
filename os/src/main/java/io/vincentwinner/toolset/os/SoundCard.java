package io.vincentwinner.toolset.os;

import oshi.hardware.HardwareAbstractionLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 声卡信息
 */
public class SoundCard {

    private SoundCard(){}
    private static abstract class SoundCardInstance{
        private static final SoundCard INSTANCE = new SoundCard();
    }
    public static SoundCard getInstance() {
        return SoundCardInstance.INSTANCE;
    }
    private final List<oshi.hardware.SoundCard> cards = ((HardwareAbstractionLayer)(Ins$.getInstance().get(2))).getSoundCards();

    /**
     * 声卡硬件信息
     * name            声卡名称
     * driverVersion   驱动版本
     * codec           编码器
     */
    public static final class SoundCardInfo implements Serializable {
        private static final long serialVersionUID = 5189203843282730272L;
        private final String name;
        private final String driverVersion;
        private final String codec;
        public SoundCardInfo(oshi.hardware.SoundCard s) {
            this.name = s.getName();
            this.driverVersion = s.getDriverVersion();
            this.codec = s.getCodec();
        }
        public String getName() {
            return name;
        }
        public String getDriverVersion() {
            return driverVersion;
        }
        public String getCodec() {
            return codec;
        }

        @Override
        public String toString() {
            return "SoundCardInfo{" +
                    "名称='" + name + '\'' +
                    ", 驱动版本='" + driverVersion + '\'' +
                    ", 编码器='" + codec + '\'' +
                    '}';
        }
    }

    /**
     * 获取所有声卡信息
     * @return 声卡信息
     */
    public List<SoundCardInfo> getSoundCards(){
        List<SoundCardInfo> cardInfos = new ArrayList<>(cards.size());
        cards.forEach(s -> { cardInfos.add(new SoundCardInfo(s)); });
        return cardInfos;
    }

    @Override
    public String toString() {
        String NEWLINE = OperatingSystem.getStaticSystemInfo().lineSeparator();
        AtomicInteger count = new AtomicInteger();
        StringBuilder sb = new StringBuilder(256);
        sb.append("------声卡------").append(NEWLINE);
        getSoundCards().forEach(g -> { sb.append("声卡").append(count.getAndIncrement()).append(":        ").append(g).append(NEWLINE); });
        return sb.toString();
    }
}
