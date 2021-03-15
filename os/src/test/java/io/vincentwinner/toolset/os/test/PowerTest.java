package io.vincentwinner.toolset.os.test;

import org.junit.Test;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PowerSource;

import java.util.List;

public class PowerTest {

    @Test
    public void testPower(){
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        List<PowerSource> powerSources = hardware.getPowerSources();
        powerSources.forEach(System.out::println);
    }

}
