package io.vincentwinner.toolset.crypto.factory.impl;

import io.vincentwinner.toolset.crypto.Encoder;
import io.vincentwinner.toolset.crypto.factory.EncoderFactory;
import io.vincentwinner.toolset.crypto.strategy.*;

/**
 * 编码器工厂
 * 该类生产的编码器为系统提供的默认编码器，全局唯一单实例
 * 默认编码类型 base64
 */
public class SingletonEncoderFactory implements EncoderFactory {

    private final String type;

    public SingletonEncoderFactory(String type) {
        this.type = type;
    }

    public SingletonEncoderFactory(){ this.type = "base64"; }

    @Override
    public Encoder getEncoder() {
        return getEncoder(this.type);
    }

    public static Encoder getEncoder(String type){
        if("base64".equalsIgnoreCase(type)){
            return Base64Crypto.getEncoder();
        }else if("urlbase64".equalsIgnoreCase(type)){
            return URLBase64Crypto.getEncoder();
        }else if("xor".equalsIgnoreCase(type)){
            return XORCrypto.getEncoder();
        }else if("aes".equalsIgnoreCase(type)){
            return AESCrypto.getEncoder();
        }else if("des".equalsIgnoreCase(type)){
            return DESCrypto.getEncoder();
        }else if("md5".equalsIgnoreCase(type)){
            return MD5Crypto.getEncoder();
        }else {
            throw new IllegalArgumentException("错误的编码器类型");
        }
    }

}
