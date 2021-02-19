package io.vincentwinner.toolset.crypto.strategy;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * AES 加密解密算法
 */
public class AESCrypto {

    /**
     * AES 编码器
     */
    public static class AESEncoder extends Encoder{
        private static final AESEncoder instance = new AESEncoder();
        @Override
        public byte[] encode(CryptoData data) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom random = new SecureRandom(data.getKey());
                keyGenerator.init(random);
                SecretKey secretKey = keyGenerator.generateKey();
                Cipher cipher;
                if(data.getIv() != null){
                    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey, data.getIv());
                }else {
                    cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                }
                return cipher.doFinal(data.getData());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * AES 解码器
     */
    public static class AESDecoder extends Decoder{
        private static final AESDecoder instance = new AESDecoder();
        @Override
        public byte[] decode(CryptoData cipherData) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                SecureRandom random = new SecureRandom(cipherData.getKey());
                keyGenerator.init(random);
                SecretKey secretKey = keyGenerator.generateKey();
                Cipher cipher;
                if(cipherData.getIv() != null){
                    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey, cipherData.getIv());
                }else {
                    cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                }
                return cipher.doFinal(cipherData.getData());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static AESEncoder getEncoder() {
        return AESEncoder.instance;
    }

    public static AESDecoder getDecoder() {
        return AESDecoder.instance;
    }

}
