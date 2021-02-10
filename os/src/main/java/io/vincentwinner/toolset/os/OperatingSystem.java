package io.vincentwinner.toolset.os;

import cn.hutool.system.OsInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OSService;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 系统信息
 */
public final class OperatingSystem {

    /**
     * 线程安全单例模式
     */
    private OperatingSystem(){}
    private static final class OperatingSystemInstance{
        static {
            class staticSystemInfo$ extends StaticSystemInfo {}
            staticSystemInfoInstance = new staticSystemInfo$();
        }
        private static final StaticSystemInfo staticSystemInfoInstance;
        private static final OperatingSystem thisInstance = new OperatingSystem();
    }
    protected static OperatingSystem getInstance() {
        return OperatingSystemInstance.thisInstance;
    }
    public static StaticSystemInfo getStaticSystemInfo(){
        return OperatingSystemInstance.staticSystemInfoInstance;
    }
    private static final OsInfo osInfo = (OsInfo) Ins$.getInstance().get(0);
    private static final oshi.software.os.OperatingSystem os = (oshi.software.os.OperatingSystem) Ins$.getInstance().get(1);

    /**
     * 操作系统类型
     */
    public enum SystemType{
        Linux,               // Linux 系统
        MACOS,               // Mac 系统
        MACOSX,              // MacOS X 操作系统
        WINDOWS,             // Windows 系统（所有版本）
        WINDOWS_2000,        // Windows2000 系统
        WINDOWS_95,          // Windows95 系统
        WINDOWS_98,          // Windows98 系统
        WINDOWS_ME,          // WindowsME 系统
        WINDOWS_NT,          // WindowsNT 系统
        WINDOWS_XP,          // WindowsXP 系统
        WINDOWS_7,           // Windows7  系统
        WINDOWS_8,           // Windows8  系统
        WINDOWS_8_1,         // Windows8.1系统
        WINDOWS_10,          // Windows10 系统
        AIX,                 // AIX 操作系统
        HP_UX,               // 惠普 Unix 操作系统
        IRIX,                // IRIX 操作系统
        OS2,                 // OS2 操作系统
        SOLARIS,             // SOLARIS 操作系统
        SUNOS,               // SunOS 操作系统
        UNKNOWN;             // 未知操作系统
    }

    protected abstract static class StaticSystemInfo {

        private StaticSystemInfo(){}

        /**
         * @return 系统名称
         */
        public String osName(){
            return osInfo.getName();
        }

        /**
         * @return 系统架构
         */
        public String arch(){
            return osInfo.getArch();
        }

        /**
         * @return 系统版本
         */
        public String osVersion(){
            return osInfo.getVersion();
        }

        /**
         * @return 系统用户名
         */
        public String osUserName(){
            return System.getProperty("user.name");
        }

        /**
         * @return 当前系统用户主文件夹
         */
        public String userHome(){
            return System.getProperty("user.home");
        }

        /**
         * Windows  \r\n
         * Linux    \n
         * MacOS    \r
         * @return 当前系统行分隔符
         */
        public String lineSeparator(){
            return osInfo.getLineSeparator();
        }

        /**
         * Windows ;
         * Linux   :
         * MacOS   :
         * @return 当前系统路径分隔符
         */
        public String pathSeparator(){
            return File.pathSeparator;
        }

        /**
         * Windows \
         * Linux   /
         * MacOs   /
         * @return 当前系统文件路径分隔符
         */
        public String fileSeparator(){
            return File.separator;
        }

        /**
         * @return 系统位数
         */
        public int bit(){
            return os.getBitness();
        }

        @Override
        public String toString() {
            String NEWLINE = getStaticSystemInfo().lineSeparator();
            return new StringBuilder(200)
                    .append("系统名称:      ").append(getStaticSystemInfo().osName()).append(NEWLINE)
                    .append("系统架构:      ").append(getStaticSystemInfo().arch()).append(NEWLINE)
                    .append("系统位数:      ").append(getStaticSystemInfo().bit()).append(NEWLINE)
                    .append("系统版本:      ").append(getStaticSystemInfo().osVersion()).append(NEWLINE)
                    .append("系统用户:      ").append(getStaticSystemInfo().osUserName()).append(NEWLINE)
                    .append("用户目录:      ").append(getStaticSystemInfo().userHome()).append(NEWLINE)
                    .append("路径分隔符:    ").append(getStaticSystemInfo().pathSeparator()).append(NEWLINE)
                    .append("文件目录分隔符: ").append(getStaticSystemInfo().fileSeparator()).append(NEWLINE)
                    .toString();
        }
    }

    /**
     * 系统已经启动了多少秒
     * @return 系统已经启动时间
     */
    public long sinceBootTime(){
        return os.getSystemUptime();
    }

    /**
     * 获取指定 进程id 的进程
     * @param pid 进程id
     * @return 进程信息
     */
    public OSProcess getProcess(int pid){
        return os.getProcess(pid);
    }

    /**
     * 获取当前系统内所有进程
     * @return 当前系统内所有进程信息列表
     */
    public List<OSProcess> getProcesses(){
        return os.getProcesses();
    }

    /**
     * 根据给出的 pid 获取所有符合的进程
     * @param pids pid列表
     * @return 所有符合 pid列表 的进程
     */
    public List<OSProcess> getProcesses(Collection<Integer> pids){
        return os.getProcesses(pids);
    }

    /**
     * 获取当前系统所有进程的数量
     * @return 当前系统进程数量
     */
    public int getProcessesCount(){
        return os.getProcessCount();
    }

    /**
     * 获取当前系统所有线程数量
     * @return 当先系统线程数量
     */
    public int getThreadCount(){
        return os.getThreadCount();
    }

    /**
     * 获取当前系统所有服务（守护进程）
     * @return 当前系统所有服务信息
     */
    public List<OSService> getServices(){
        return Arrays.asList(os.getServices());
    }



    /**
     * 验证当前系统是否是目标系统
     * @param systemType 目标系统类型
     * @return 当前系统类型和参数系统类型是否匹配
     *         参数中含有在{@link OperatingSystem.SystemType}中不存在的系统类型则直接返回 false
     */
    public boolean isTargetSystem(SystemType systemType){
        switch (systemType){
            case Linux : return osInfo.isLinux();
            case MACOS : return osInfo.isMac();
            case MACOSX: return osInfo.isMacOsX();
            case WINDOWS: return osInfo.isWindows();
            case WINDOWS_2000: return osInfo.isWindows2000();
            case WINDOWS_95: return osInfo.isWindows95();
            case WINDOWS_98: return osInfo.isWindows98();
            case WINDOWS_ME: return osInfo.isWindowsME();
            case WINDOWS_NT: return osInfo.isWindowsNT();
            case WINDOWS_XP: return osInfo.isWindowsXP();
            case WINDOWS_7: return osInfo.isWindows7();
            case WINDOWS_8: return osInfo.isWindoows8();
            case WINDOWS_8_1: return osInfo.isWindows8_1();
            case WINDOWS_10: return osInfo.isWindows10();
            case AIX: return osInfo.isAix();
            case HP_UX: return osInfo.isHpUx();
            case IRIX: return osInfo.isIrix();
            case OS2: return osInfo.isOs2();
            case SOLARIS: return osInfo.isSolaris();
            case SUNOS: return osInfo.isSunOS();
        }
        return false;
    }

    @Override
    public String toString() {
        String NEWLINE = getStaticSystemInfo().lineSeparator();
        StringBuilder sb = new StringBuilder(512);
        sb.append("------系统信息------").append(NEWLINE);
        sb.append(getStaticSystemInfo());
        sb.append("运行时间:      ").append(sinceBootTime() / 60 ).append(" 分").append(NEWLINE);
        sb.append("进程数量:      ").append(getProcessesCount()).append(NEWLINE);
        sb.append("线程数量:      ").append(getThreadCount()).append(NEWLINE);
        sb.append("服务数量:      ").append(getServices().size()).append(NEWLINE);
        return sb.toString();
    }
}
