## Face-Id 人脸识别/检测模块

---
#### 目录
> #### [0.初始化库和深度学习模型](#init)
> #### [1.人脸对比](#compare)
> #### [2.人脸识别](#detect)
> #### [3.人脸识别可视化](#detect_view)
---

#### <span id="init">0.初始化库和深度学习模型</span>
```properties
# 文件名： ai-faceid-seeta.properties

# jni 库的存放位置
# 相对路径或绝对路径皆可
library.path = lib

# 深度学习模型存放目录
# 相对路径或绝对路径皆可
model.path = bindata

# 最大并行线程数量
thread.max = 50

# 相似率在此值以下时，认为两张图片不是同一个人
# 范围 [ 0.0 ~ 1.0 ]
# 默认值 0.7
similar.rate = 0.7

# 识别因数，用于调整误差
# 该值越小，能接受的相似率误差越大
# 范围 [ 0.0 ~ 1.0 ]
# 默认值 0.92
factor = 0.92
```
```
特别注意：

以下所有操作都需要在动态链接库和深度学习模型已经初始化完毕之后运行

ai-faceid-seeta.common.properties 默认配置文件在jar根目录中，
将其文件名中的.common去掉后放入类路径中即可使用默认配置

windows运行库下载（放置在ai-faceid-seeta.properties文件中library.path定义的位置）
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/ai-face-id-seeta-lib-win-x64.zip

linux运行库下载（放置在ai-faceid-seeta.properties文件中library.path定义的位置）
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/ai-face-id-seeta-lib-linux-x64.tar.bz2
linux环境下运行需要安装额外的运行库，下载地址
http://ftp.gnu.org/gnu/gcc/gcc-5.5.0/gcc-5.5.0.tar.gz
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/openblas-0.2.20.tar.gz
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/openssl-1.0.0.tar.gz
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/protobuf-2.6.0.tar.gz
将上述四个压缩包下载好之后放入同一个文件夹，
将工程目录中的build-library-linux.sh复制到此处编译运行即可

深度学习模型下载（放置在ai-faceid-seeta.properties文件中model.path定义的位置）
模型使用tar分卷压缩（tar cf - bindata |split -b 70m - bindata.tar.）
解压请使用命令（cat bindata.tar.* | tar -x）
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/bindata.tar.aa
https://gitee.com/vincent-repo/ai-face-id-seeta-lib/raw/master/bindata.tar.ab

如果运行时出现下列错误，则应更新 jdk 版本到 1.8.0_u261 之上
Process finished with exit code -1073740940 (0xC0000374)
```

#### <span id="compare">1.人脸对比</span>

```java
import io.vincentwinner.toolset.ai.faceid.seetaface.FaceCompareResult;
import io.vincentwinner.toolset.ai.faceid.seetaface.FaceHandleService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        FaceHandleService service = FaceHandleService.getInstance();
        InputStream pic1 = new FileInputStream("人物1.jpg");
        InputStream pic2 = new FileInputStream("人物2.jpg");
        Future<FaceCompareResult> resultFuture = service.submitCompare(pic1, pic2);
        
        FaceCompareResult result = resultFuture.get();

        System.out.printf("是否为同一人： %s\n",result.isSamePerson() ? "是" : "否");
        System.out.printf("两张图片人脸相似率： %s\n",result.getSimilarRate());
        
        //不要忘记结束线程池，否则程序不会结束
        service.shutdown();
    }
}
```

#### <span id="detect">2.人脸识别</span>

```java
import com.seetaface2.model.SeetaRect;
import io.vincentwinner.toolset.ai.faceid.seetaface.FaceHandleService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        FaceHandleService service = FaceHandleService.getInstance();
        InputStream is = new FileInputStream("人物1.jpg");
        Future<SeetaRect[]> future = service.submitDetect(is);
        
        //结果数组为人脸位置
        //SeetaRect的参数如下
        //x是x轴起始位置
        //y为y轴起始位置
        //width为宽度
        //height为高度
        SeetaRect[] rects = future.get();

        //不要忘记结束线程池，否则程序不会结束
        service.shutdown();
    }
}
```

#### <span id="detect_view">3.人脸识别可视化</span>

```java
import com.seetaface2.model.SeetaRect;
import io.vincentwinner.toolset.ai.faceid.seetaface.FaceHandleService;
import io.vincentwinner.toolset.ai.faceid.seetaface.awt.ImageFrame;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        FaceHandleService service = FaceHandleService.getInstance();
        File pic = new File("人物1.jpg");
        InputStream is = new FileInputStream(pic);
        Future<SeetaRect[]> future = service.submitDetect(is);
        new ImageFrame(pic,future);

        //不要忘记结束线程池，否则程序不会结束
        service.shutdown();
    }
}
```