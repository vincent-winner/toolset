## Crypto 加密解密模块

---
#### 目录
> #### [1.基本加密解密方法](#s_encode)
> #### [2.多重加密解密模式](#multi)

---

#### 加密解密方式
```
base64      base64 编码
urlbase64   用于 url 的 base64 编码（替换url歧义字符）
xor         异或（需要密钥）
aes         aes（需要密钥和初始化向量）
des         des（需要密钥和初始化向量）
md5         md5（不可解密，默认32位小写摘要）
```

#### <span id="s_encode">1.基本加密解密方法<span>
```java
import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;
import io.vincentwinner.toolset.crypto.strategy.AESCrypto;
import io.vincentwinner.toolset.crypto.strategy.Base64Crypto;

/**
 * 本例中用 base64 算法演示基本加密解密模式
 * CryptoData 构造函数中第一个参数为需要加密或解密的数据
 * 第二个参数为密钥，在需要密钥的加密算法中起作用
 * 第三个参数为初始化向量，在需要初始化向量的算法中起作用，大小必须为 16 byte，否则将抛出异常
 */
public class Test {
    public static void main(String[] args) {
        CryptoData data = new CryptoData("123".getBytes(), null, null);
        Encoder encoder = Base64Crypto.getEncoder();
        Decoder decoder = Base64Crypto.getDecoder();
        byte[] encodedData = encoder.encode(data);
        data.setData(encodedData);
        System.out.println(new String(decoder.decode(data)));
    }
}
```

#### <span id="multi">2.多重加密解密模式</span>
```java
import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;
import io.vincentwinner.toolset.crypto.factory.DecoderFactory;
import io.vincentwinner.toolset.crypto.factory.EncoderFactory;
import io.vincentwinner.toolset.crypto.factory.impl.MultiAlgorithmDecoderFactory;
import io.vincentwinner.toolset.crypto.factory.impl.MultiAlgorithmEncoderFactory;

import javax.crypto.spec.IvParameterSpec;

public class Test {
    public static void main(String[] args) {
        
        // 编码解码规则，按顺序填写加密方式，分隔符自定义，不区分大小写
        // 加密解密方式查看本文开头的 "加密解密方式"
        String rule = "base64,aes,aes";
        
        /**
         * 初始化编码器解码器工厂，第二个参数为分隔符
         * 需要特别注意的是 
         * 当编码器类型中使用了不可解码的编码方式，则不可以初始化解码器工厂，加密的内容也不可解密
         */
        EncoderFactory encoderFactory = new MultiAlgorithmEncoderFactory(rule, ",");
        DecoderFactory decoderFactory = new MultiAlgorithmDecoderFactory(rule, ",");
        
        Encoder encoder = encoderFactory.getEncoder();
        Decoder decoder = decoderFactory.getDecoder();
        
        // 被加密的数据
        byte[] byteData = "123".getBytes();
        // 密钥
        byte[] key = "123456789".getBytes();
        // 初始化向量（大小必须为 16 byte）
        IvParameterSpec iv = new IvParameterSpec("1234567890abcdef".getBytes());
        
        
        CryptoData data = new CryptoData("123".getBytes(), "123456789".getBytes(), new IvParameterSpec("1234567890abcdef".getBytes()));

        byte[] encodedData = encoder.encode(data);
        data.setData(encodedData);
        System.out.println(new String(decoder.decode(data)));
    }
}
```
