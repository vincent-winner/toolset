package io.vincentwinner.toolset.crypto.test;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;
import io.vincentwinner.toolset.crypto.factory.impl.MultiAlgorithmDecoderFactory;
import io.vincentwinner.toolset.crypto.factory.impl.MultiAlgorithmEncoderFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.crypto.spec.IvParameterSpec;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MultipleAlgorithmTest {

    @Test
    public void _01_testMultipleEncrypt(){
        String data = "123你好abc!@#";
        String key = "123你好abc!@#";
        String iv = "1234567890abcdef";
        CryptoData d = new CryptoData(data.getBytes(), key.getBytes(),new IvParameterSpec(iv.getBytes()));
        MultiAlgorithmEncoderFactory factory = new MultiAlgorithmEncoderFactory("base64,aes,base64,xor,xor",",");
        MultiAlgorithmDecoderFactory decoderFactory = new MultiAlgorithmDecoderFactory("base64,aes,base64,xor,xor",",");
        Encoder encoder = factory.getEncoder();
        Decoder decoder = decoderFactory.getDecoder();
        d.setData(encoder.encode(d));
        byte[] plain = decoder.decode(d);
        Assert.assertEquals(new String(data.getBytes()),new String(plain));
    }

}
