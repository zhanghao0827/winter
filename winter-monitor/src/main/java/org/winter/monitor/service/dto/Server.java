package org.winter.monitor.service.dto;

import cn.hutool.core.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Getter
@Setter
public class Server {

    private OS os = new OS();

    private JVM jvm = new JVM();

    protected List<Disk> disks = new ArrayList<>();

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void init() {
        SystemInfo systemInfo = new SystemInfo();
        // 获取操作系统信息
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        NetworkParams networkParams = operatingSystem.getNetworkParams();
        String hostName = networkParams.getHostName();  //主机名称
        String ipv4 = networkParams.getIpv4DefaultGateway(); //ipv4
        os.setHostName(hostName);
        os.setIp(ipv4);

        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");  //操作系统
        String osArch = props.getProperty("os.arch");  //系统架构
        String userDir = props.getProperty("user.dir");//项目路径
        os.setOsName(osName);
        os.setArch(osArch);
        os.setUserDir(userDir);

        long totalMemory = Runtime.getRuntime().totalMemory(); //JVM占用的内存总数(M)
        long maxMemory = Runtime.getRuntime().maxMemory();     //JVM最大可用内存总数(M)
        long freeMemory = Runtime.getRuntime().freeMemory();   //JVM空闲内存(M)
        String javaName = props.getProperty("java.vm.name");   //JVM名称
        String javaVersion = props.getProperty("java.version");//JDK版本
        String javaHome = props.getProperty("java.home");      //JDK路径
        jvm.setTotal(convertFileSize(totalMemory));
        jvm.setMax(convertFileSize(maxMemory));
        jvm.setFree(convertFileSize(freeMemory));
        jvm.setName(javaName);
        jvm.setVersion(javaVersion);
        jvm.setHome(javaHome);

        // 磁盘信息
        FileSystem fileSystem = operatingSystem.getFileSystem();
        OSFileStore[] fileStores = fileSystem.getFileStores();
        for (OSFileStore fs : fileStores) {
            Disk disk = new Disk();
            long free = fs.getUsableSpace(); //可用磁盘大小
            long total = fs.getTotalSpace(); //总共磁盘大小
            long used = total - free;        //可用磁盘大小
            disk.setName(fs.getName());
            disk.setType(fs.getType());
            disk.setDir(fs.getMount());
            disk.setFree(convertFileSize(free));
            disk.setTotal(convertFileSize(total));
            disk.setUsed(convertFileSize(used));
            disk.setUsage(Double.valueOf(df.format(NumberUtil.div(used, total) * 100)));
            disks.add(disk);
        }
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }

}
