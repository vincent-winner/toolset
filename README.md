<pre align="center" style="color: #A95CF3;background-color:rgba(0,0,0,0)">
 _________  ________  ________  ___          
|\___   ___\\   __  \|\   __  \|\  \         
\|___ \  \_\ \  \|\  \ \  \|\  \ \  \        
     \ \  \ \ \  \\\  \ \  \\\  \ \  \       
      \ \  \ \ \  \\\  \ \  \\\  \ \  \____  
       \ \__\ \ \_______\ \_______\ \_______\
        \|__|  \|_______|\|_______|\|_______|
</pre>
<p align="center">
    <a href="javascript:void(0);"><img src="https://img.shields.io/:build-passing-green.svg"></a>
    <a target="_blank" href="https://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/:license-Apache2.0-blue.svg"/></a>
    <a target="_blank" href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html"><img src="https://img.shields.io/badge/JDK-8u281+-green.svg"/></a>
    <a href="javascript:void(0);"><img src="https://img.shields.io/:modules-10-blue.svg"></a>
</p>

<h3 align="center">菜鸟使用的工具包，适合用来开发软件工程专业的课程设计和毕业设计</h3>

---

## 包含内容

---

|模块|说明|
|:---:|:---|
|[core](core/README.md)|核心程序，提供了各种基本的方法|
|[console](console/README.md)|控制台相关内容（彩色文字，表格，进度条）|
|[crypto](crypto/README.md)|各种加密算法的Java实现|
|[os](os/README.md)|获取各种系统信息（CPU、硬盘、内存、显卡、声卡..）|
|[classloader](class-loader/README.md)|各种功能的类加载器|
|[ai](ai/README.md)|人工智能模块|
|[image](image/README.md)|图像处理|
|[qrcode](qrcode/README.md)|二维码|

---

## 安装方法
1. 克隆项目到本地
```shell
git clone https://gitee.com/vincent-winner/toolset
```

2. 进入项目根目录
```shell
cd toolset
```

3. 构建并安装项目到本地 Maven 仓库
```shell
mvn clean install
```

4. 安装源码（可选，非必须）
```shell
mvn source:jar install
```

---

## 注意事项：
- 人工智能模块中，`face-id`模块需要`jdk8u281`及以上jdk版本才可正常运行，如果不想更新JDK，可以使用采用更新技术的`seeta6`模块，此模块各方面效果都要优于`face-id`
- 修改pom.xml中的 project.platform 选项来指定编译平台，目前 project.platform 选项只会影响到 image 模块，需要此选项的原因是 Image 模块导入了opencv 的动态链接库，而动态链接库不跨平台
```xml
<!--使用以下方式导入对应平台的image模块，project.platform的值如下-->
<!--
    windows-x86_64
    linux-x86_64
-->
<dependency>
    <groupId>io.vincentwinner.toolset</groupId>
    <artifactId>image</artifactId>
    <version>1.0.1-${project.platform}</version>
</dependency>
```
