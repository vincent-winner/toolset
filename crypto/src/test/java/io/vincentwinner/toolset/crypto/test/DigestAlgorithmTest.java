package io.vincentwinner.toolset.crypto.test;

import io.vincentwinner.toolset.crypto.factory.impl.SingletonEncoderFactory;
import io.vincentwinner.toolset.crypto.strategy.MD5Crypto;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 测试摘要算法
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DigestAlgorithmTest {

    private SingletonEncoderFactory encoderFactory = new SingletonEncoderFactory();
    private String s = "123你好abc!@#";

    /**
     * 测试 32位 MD5 摘要
     */
    @Test
    public void _01_testMD5(){
        MD5Crypto.MD5Encoder encoder = (MD5Crypto.MD5Encoder)encoderFactory.getEncoder("md5");
        String msg = encoder.encode(s);
        Assert.assertEquals(32,msg.length());
        System.out.println(msg);
    }

    /**
     * 测试 16 位 MD5 摘要
     */
    @Test
    public void _02_testMD516(){
        MD5Crypto.MD5Encoder encoder = (MD5Crypto.MD5Encoder)encoderFactory.getEncoder("md5");
        String msg = encoder.encode16(s);
        Assert.assertEquals(16,msg.length());
        System.out.println(msg);
    }

}
