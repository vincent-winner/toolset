package io.vincentwinner.toolset.crypto.test;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.factory.impl.SingletonDecoderFactory;
import io.vincentwinner.toolset.crypto.factory.impl.SingletonEncoderFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.crypto.spec.IvParameterSpec;

/**
 * 测试加密算法
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncryptAlgorithmTest {

    /**
     * 测试 Base64 编码
     */
    @Test
    public void _01_testBase64(){
        testAlgorithm("base64");
    }

    /**
     * 测试 URLBase64 编码
     */
    @Test
    public void _02_testURLBase64(){
        testAlgorithm("urlbase64");
    }

    /**
     * 测试异或加密
     */
    @Test
    public void _03_testXOR(){
        testAlgorithm("xor");
    }

    /**
     * 测试 AES 加密
     */
    @Test
    public void _04_testAES(){
        testAlgorithm("aes");
    }

    /**
     * 对相应的加密算法进行测试
     * @param algorithmName 加密算法名称（不区分大小写）
     */
    private void testAlgorithm(String algorithmName){
        String data = "123你好abc!@#";
        String key = "123你好abc!@#";
        String iv = "1234567890abcdef";
        CryptoData d = new CryptoData(data.getBytes(), key.getBytes(),new IvParameterSpec(iv.getBytes()));
        d.setData(SingletonEncoderFactory.getEncoder(algorithmName).encode(d));
        Assert.assertEquals(new String(SingletonDecoderFactory.getDecoder(algorithmName).decode(d)),new String(data.getBytes()));
    }

}
