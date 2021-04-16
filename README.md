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

注意：
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