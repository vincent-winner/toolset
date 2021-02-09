package io.vincentwinner.toolset.os;

import cn.hutool.system.OsInfo;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.HashMap;
import java.util.Map;

public final class Ins$ {

    private Ins$(){
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem os = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        context.put(0, new OsInfo());
        context.put(1, os);
        context.put(2, hardware);
    }
    private static final class InsInstance {
        private static final Ins$ INS_$ = new Ins$();
    }
    protected static Ins$ getInstance() {
        return InsInstance.INS_$;
    }

    private final Map<Integer,Object> context = new HashMap<>(3);

    protected Object get(int id){
        return context.get(id);
    }

}
