package io.vincentwinner.toolset.os.test;

import io.vincentwinner.toolset.os.Computer;
import io.vincentwinner.toolset.os.OperatingSystem;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SoftParameterTest {

    @Test
    public void _01_testStaticSystemInfo(){
        OperatingSystem.StaticSystemInfo staticInfo =  Computer.getStaticSystemInfo();
        System.out.println(staticInfo);
        Assert.assertNotNull(staticInfo);
    }

    @Test
    public void _02_testDynamicSystemInfo(){
        OperatingSystem system = Computer.getOperatingSystem();
        System.out.println(system.getProcesses());
        System.out.println(system.getServices());
        Assert.assertNotNull(system.getProcesses());
        Assert.assertNotNull(system.getServices());
    }

    @Test
    public void _03_testAllOperatingSystemInfo(){
        OperatingSystem system = Computer.getOperatingSystem();
        System.out.println(system);
        Assert.assertNotNull(system);
    }

}
