package io.vincentwinner.toolset.os.test;

import io.vincentwinner.toolset.os.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardParameterTest {

    @Test
    public void _01_testComputerHardwareInfo(){
        System.out.println(Computer.getComputerModel());
        System.out.println(Computer.getHardwareUUID());
        System.out.println(Computer.getManufacturer());
        System.out.println(Computer.getSerialNumber());

        Assert.assertNotNull(Computer.getComputerModel());
        Assert.assertNotNull(Computer.getHardwareUUID());
        Assert.assertNotNull(Computer.getManufacturer());
        Assert.assertNotNull(Computer.getSerialNumber());
    }

    @Test
    public void _02_testCpuInfo(){
        CPU cpu = Computer.getCPU();
        System.out.println(cpu);
        Assert.assertNotNull(cpu.getCPUInfo());
    }

    @Test
    public void _03_testDiskInfo(){
        Disk disk = Computer.getDisk();
        System.out.println(disk);
        Assert.assertNotNull(disk.getDiskList());
    }

    @Test
    public void _04_testMemoryInfo(){
        Memory memory = Computer.getMemory();
        System.out.println(memory);
        Assert.assertNotNull(memory);
    }

    @Test
    public void _05_testGraphicsCard(){
        GraphicsCard gpc = Computer.getGraphicsCard();
        System.out.println(gpc);
    }

    @Test
    public void _06_testSoundCard(){
        SoundCard soundCard = Computer.getSoundCard();
        System.out.println(soundCard);
    }

    @Test
    public void _07_testAll(){
        System.out.println(Computer.computerInfoString());
    }

}
