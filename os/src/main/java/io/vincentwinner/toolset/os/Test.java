package io.vincentwinner.toolset.os;

import oshi.hardware.HardwareAbstractionLayer;

public class Test {

    public static void main(String[] args) {
        oshi.software.os.OperatingSystem os = (oshi.software.os.OperatingSystem) Ins$.getInstance().get(1);
        HardwareAbstractionLayer hard = (HardwareAbstractionLayer) Ins$.getInstance().get(2);
        System.out.println(OperatingSystem.getInstance());
        System.out.println(Memory.getInstance());
        System.out.println(Disk.getInstance());
        System.out.println(CPU.getInstance());
        System.out.println(GraphicsCard.getInstance());
        System.out.println(SoundCard.getInstance());
    }

}
