## OS 系统模块
主要用于获取系统信息，包括硬件信息和软件信息
<p>注意：本模块所有内容全部通过 Computer 类调用</p>

---
#### 目录
> #### [1.将全部系统信息输出到控制台](#all)
> #### [2.获取相关计算机模块的信息](#test)
> #### [3.判断操作系统类型](#target_os)
---

#### <span id="all">1.将全部系统信息输出到控制台<span>

```java
import io.vincentwinner.toolset.os.Computer;

/**
 * 获取全部电脑信息形式如下，单独获取信息打印到控制台显示形式相同
 ------计算机信息------
 制造厂商:      Dell Inc.
 电脑型号:      G3 3579
 序列编号:      XXXXXXX
 硬件标识:      XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX

 ------系统信息------
 系统名称:      Windows 10
 系统架构:      amd64
 系统位数:      64
 系统版本:      10.0
 系统用户:      user
 用户目录:      C:\Users\username
 路径分隔符:    ;
 文件目录分隔符: \
 运行时间:      458 分
 进程数量:      261
 线程数量:      3362
 服务数量:      369

 ------处理器信息------
 处理器制造商:   GenuineIntel
 处理器产品名:   Intel(R) Core(TM) i7-8750H CPU @ 2.20GHz
 处理器产品族:   6
 逻辑核心数量:   12
 物理核心数量:   6
 产品型号信息:   158
 产品步进信息:   10
 产品出厂频率:   2.208GHz
 产品最大频率:   2.208GHz
 是否支持64bit: 是
 处理器使用率:   5.086417%

 ------内存信息------
 物理内存大小:   16243 Mib
 物理内存已用:   9683 Mib
 物理内存已用:   59.611797 %
 交换分区大小:   5501 Mib
 交换分区已用:   147 Mib
 交换分区已用:   2.6742623 %
 内存插槽0:     {内存标签='', 容量=8GiB, 时钟频率=2667MHz, 制造商='802C0000802C', 内存类型='DDR4'}
 内存插槽1:     {内存标签='', 容量=8GiB, 时钟频率=2667MHz, 制造商='859B0000802C', 内存类型='DDR4'}

 ------磁盘信息------
 磁盘0:        {磁盘名称='本地固定磁盘 (C:)', 卷='\\?\Volume{ff99d5e1-0c99-45d5-870b-44a4ab7e5f49}\', 标签='OS', 逻辑卷='', 挂载点='C:\', 描述='Fixed drive', 类型='NTFS', 参数='rw,reparse,sparse,trans,journaled,quota,casess,oids,casepn,efs,streams,unicode,acls,fcomp', 唯一标识='ff99d5e1-0c99-45d5-870b-44a4ab7e5f49', 空闲空间=4095561728, 总空间=111852646400, 已使用空间=107757084672, 空间使用率=0.03661568912150477}
 磁盘1:        {磁盘名称='本地固定磁盘 (D:)', 卷='\\?\Volume{ac6cf8aa-2cf2-43f8-b164-26c0237478a3}\', 标签='DATA', 逻辑卷='', 挂载点='D:\', 描述='Fixed drive', 类型='NTFS', 参数='rw,reparse,sparse,trans,journaled,quota,casess,oids,casepn,efs,streams,unicode,acls,fcomp', 唯一标识='ac6cf8aa-2cf2-43f8-b164-26c0237478a3', 空闲空间=24519626752, 总空间=1000066772992, 已使用空间=975547146240, 空间使用率=0.02451798961247575}
 磁盘2:        {磁盘名称='本地固定磁盘 (V:)', 卷='\\?\Volume{84630a67-0000-0000-0080-000000000000}\', 标签='Vincent', 逻辑卷='', 挂载点='V:\', 描述='Fixed drive', 类型='exFAT', 参数='rw,casepn,efs,unicode', 唯一标识='84630a67-0000-0000-0080-000000000000', 空闲空间=154420707328, 总空间=2000053796864, 已使用空间=1845633089536, 空间使用率=0.07720827688241444}

 ------显卡信息------
 显卡0:        GraphicsCardInfo{制造商='Intel Corporation (0x8086)', 产品名称='Intel(R) UHD Graphics 630', 版本='DriverVersion=26.20.100.8141', 设备id='0x3e9b', 显存=1024Mib}
 显卡1:        GraphicsCardInfo{制造商='NVIDIA (0x10de)', 产品名称='NVIDIA GeForce GTX 1050', 版本='DriverVersion=27.21.14.6089', 设备id='0x1c8d', 显存=4095Mib}

 ------声卡------
 声卡0:        SoundCardInfo{名称='NVIDIA NVIDIA Virtual Audio Device (Wave Extensible) (WDM)', 驱动版本='nvvad64v.sys 4.13.0.0', 编码器='NVIDIA Virtual Audio Device (Wave Extensible) (WDM)'}
 声卡1:        SoundCardInfo{名称='Realtek Semiconductor Corp. Realtek Audio', 驱动版本='RTKVHD64.sys 6.0.8899.1', 编码器='Realtek Audio'}
 声卡2:        SoundCardInfo{名称='Intel(R) Corporation 英特尔(R) 显示器音频', 驱动版本='IntcDAud.sys 10.27.0.9', 编码器='英特尔(R) 显示器音频'}
 声卡3:        SoundCardInfo{名称='Valve Corporation Steam Streaming Speakers', 驱动版本='SteamStreamingSpeakers.sys 17.56.13.764', 编码器='Steam Streaming Speakers'}
 声卡4:        SoundCardInfo{名称='Valve Corporation Steam Streaming Microphone', 驱动版本='SteamStreamingMicrophone.sys 8.33.15.17', 编码器='Steam Streaming Microphone'}
 声卡5:        SoundCardInfo{名称='Visicom Media Inc. ManyCam Virtual Microphone', 驱动版本='mcaudrv_x64.sys 4.1.0.0', 编码器='ManyCam Virtual Microphone'}
 声卡6:        SoundCardInfo{名称='NVIDIA Corporation NVIDIA High Definition Audio', 驱动版本='nvhda64v.sys 1.3.38.40', 编码器='NVIDIA High Definition Audio'}
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Computer.computerInfoString());
    }
}
```

#### <span id="test">2.获取相关计算机模块的信息<span>

```java
import io.vincentwinner.toolset.os.Computer;

/**
 * 利用以下方法可以将各种计算机模块的信息输出到控制台中
 * 详细获取相关模块的指定信息可以利用 Computer.getXXX() 方法获取模块对象
 * 调用获取到的模块对象可对其包含的信息做细致处理
 */
public class Test {
    public static void main(String[] args) {
        // 获取电脑型号
        System.out.println(Computer.getComputerModel());
        // 获取计算机制造商
        System.out.println(Computer.getManufacturer());
        // 计算机序列号
        System.out.println(Computer.getSerialNumber());
        // 获取硬件唯一标识
        System.out.println(Computer.getHardwareUUID());
        // 操作系统信息
        System.out.println(Computer.getOperatingSystem());
        // CPU信息
        System.out.println(Computer.getCPU());
        // 内存信息
        System.out.println(Computer.getMemory());
        // 硬盘信息
        System.out.println(Computer.getDisk());
        // 显卡信息
        System.out.println(Computer.getGraphicsCard());
        // 声卡信息
        System.out.println(Computer.getSoundCard());
    }
}
```

#### <span id="target_os">3.判断操作系统类型</span>
```java
import io.vincentwinner.toolset.os.Computer;
import io.vincentwinner.toolset.os.OperatingSystem;

/**
 * 假如当前系统为 Windows8
 * 则 
 *   OperatingSystem.isTargetSystem(OperatingSystem.SystemType.WINDOWS)
 *   OperatingSystem.isTargetSystem(OperatingSystem.SystemType.WINDOWS_8)
 * 都将返回true
 */
public class Test {
    public static void main(String[] args) {
        OperatingSystem system = Computer.getOperatingSystem();

        // 当前系统是 Windows 系统返回true
        System.out.println(system.isTargetSystem(OperatingSystem.SystemType.WINDOWS));
        // 当前系统是 Linux 系统返回true
        System.out.println(system.isTargetSystem(OperatingSystem.SystemType.Linux));
        // 当前系统是 Windows7 系统返回true
        System.out.println(system.isTargetSystem(OperatingSystem.SystemType.WINDOWS_7));
    }
}
```