package io.vincentwinner.toolset.crypto.strategy;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * DES 加密解密
 */
public class DESCrypto {

    /**
     * DES 编码器
     */
    public static class DESEncoder extends Encoder {
        private static final DESEncoder instance = new DESEncoder();
        @Override
        public byte[] encode(CryptoData data) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
                SecureRandom random = new SecureRandom(data.getKey());
                keyGenerator.init(random);
                SecretKey secretKey = keyGenerator.generateKey();
                Cipher cipher;
                if(data.getIv() != null){
                    cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey, data.getIv());
                }else {
                    cipher = Cipher.getInstance("DES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                }
                return cipher.doFinal(data.getData());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * DES 解码器
     */
    public static class DESDecoder extends Decoder {
        private static final DESDecoder instance = new DESDecoder();
        @Override
        public byte[] decode(CryptoData cipherData) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
                SecureRandom random = new SecureRandom(cipherData.getKey());
                keyGenerator.init(random);
                SecretKey secretKey = keyGenerator.generateKey();
                Cipher cipher;
                if(cipherData.getIv() != null){
                    cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey, cipherData.getIv());
                }else {
                    cipher = Cipher.getInstance("DES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                }
                return cipher.doFinal(cipherData.getData());
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static DESEncoder getEncoder() {
        return DESEncoder.instance;
    }

    public static DESDecoder getDecoder() {
        return DESDecoder.instance;
    }

}
