package io.vincentwinner.toolset.crypto.factory.impl;

import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.factory.DecoderFactory;
import io.vincentwinner.toolset.crypto.strategy.*;

/**
 * 解码器工厂
 * 该类生产的解码器为系统提供的默认解码器，全局唯一单实例
 * 默认编码类型 base64
 */
public class SingletonDecoderFactory implements DecoderFactory {

    private final String type;

    public SingletonDecoderFactory(String type){
        this.type = type;
    }

    public SingletonDecoderFactory(){ this.type = "base64"; }

    @Override
    public Decoder getDecoder() {
        return getDecoder(this.type);
    }

    public static Decoder getDecoder(String type){
        if("base64".equalsIgnoreCase(type)){
            return Base64Crypto.getDecoder();
        }else if("urlbase64".equalsIgnoreCase(type)){
            return URLBase64Crypto.getDecoder();
        }else if("xor".equalsIgnoreCase(type)){
            return XORCrypto.getDecoder();
        } else if("aes".equalsIgnoreCase(type)){
            return AESCrypto.getDecoder();
        }else if("des".equalsIgnoreCase(type)){
            return DESCrypto.getDecoder();
        } else {
            throw new IllegalArgumentException("错误的解码器类型");
        }
    }

}
