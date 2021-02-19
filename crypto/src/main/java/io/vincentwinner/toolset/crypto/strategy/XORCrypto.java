package io.vincentwinner.toolset.crypto.strategy;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;

/**
 * 异或加密，解密
 * 此加密方法没有用到 CryptoData 中的 iv(初始化向量) 属性，该值填写为任何值都无作用，建议填写为 null
 */
public class XORCrypto {

    /**
     * 异或加密编码器
     */
    public static class XOREncoder extends Encoder{
        private static final XOREncoder instance = new XOREncoder();

        /**
         * 利用密码将数据进行异或加密
         * @param plain 明文数据，密钥，初始化向量（不需要）
         * @return 被加密的数据
         */
        @Override
        public byte[] encode(CryptoData plain) {
            byte[] data = plain.getData();
            byte[] keyBytes = plain.getKey();
            byte[] encryptBytes = new byte[data.length];
            for (int i = 0 ; i < data.length ; i++) {
                encryptBytes[i] = (byte) (data[i] ^ keyBytes[i % keyBytes.length]);
            }
            return encryptBytes;
        }

        /**
         * 利用密码将数据进行异或加密
         * @param plainData 明文数据
         * @param key 密钥
         * @return 被加密的数据
         */
        public byte[] encode(byte[] plainData,byte[] key){
            return encode(new CryptoData(plainData,key,null));
        }

        /**
         * 利用密码将数据进行异或加密
         * @param plainData 明文数据
         * @param key 密钥
         * @return 被加密的数据
         */
        public byte[] encode(byte[] plainData,String key){
            return encode(new CryptoData(plainData,key.getBytes(),null));
        }

        /**
         * 更为安全的异或加密，普通异或加密函数传入的参数为全0 byte数组时，得到的加密结果将是密钥
         * 此方法加密将上述方法获取的密钥屏蔽，更加安全，速度也稍稍降低
         * @param plain 明文数据，密钥
         * @return 加密后的数据
         */
        public byte[] safeEncode(CryptoData plain){
            byte[] data = plain.getData();
            byte[] keyBytes0 = plain.getKey();
            byte[] keyBytes = new byte[256];
            for (int i = 0; i < keyBytes0.length; i++) {
                keyBytes[i] = keyBytes0[i % keyBytes0.length];
            }
            byte[] encodeBytes = new byte[data.length];
            int j = 0;
            int k = 0;
            for (int i = 0; i < encodeBytes.length; i++) {
                k = 0xff & k + 1;
                j = 0xff & j + keyBytes[k];
                int m = keyBytes[k];
                keyBytes[k] = keyBytes[j];
                keyBytes[j] = (byte) m;
                int n = 0xff & keyBytes[j] + keyBytes[k];
                encodeBytes[i] = (byte) (data[i] ^ keyBytes[n]);
            }
            return encodeBytes;
        }

        /**
         * 更为安全的异或加密，普通异或加密函数传入的参数为全0 byte数组时，得到的加密结果将是密钥
         * 此方法加密将上述方法获取的密钥屏蔽，更加安全，速度也稍稍降低
         * @param plainData 明文数据
         * @param key 密钥
         * @return 加密后的数据
         */
        public byte[] safeEncode(byte[] plainData,byte[] key){
            return safeEncode(new CryptoData(plainData,key,null));
        }

        /**
         * 更为安全的异或加密，普通异或加密函数传入的参数为全0 byte数组时，得到的加密结果将是密钥
         * 此方法加密将上述方法获取的密钥屏蔽，更加安全，速度也稍稍降低
         * @param plainData 明文数据
         * @param key 密钥
         * @return 加密后的数据
         */
        public byte[] safeEncode(byte[] plainData,String key){
            return safeEncode(new CryptoData(plainData,key.getBytes(),null));
        }
    }

    /**
     * 异或加密解码器
     */
    public static class XORDecoder extends Decoder{
        private static final XORDecoder instance = new XORDecoder();

        /**
         * 解码被异或加密的数据
         * @param cipher 加密的数据，密钥
         * @return 解密后的数据
         */
        @Override
        public byte[] decode(CryptoData cipher) {
            return XOREncoder.instance.encode(cipher);
        }

        /**
         * 解码被异或加密的数据
         * @param cipherData 加密的数据
         * @param key 密钥
         * @return 解密后的数据
         */
        public byte[] decode(byte[] cipherData,byte[] key){
            return decode(new CryptoData(cipherData,key,null));
        }

        /**
         * 解码被异或加密的数据
         * @param cipherData 加密的数据
         * @param key 密钥
         * @return 解密后的数据
         */
        public byte[] decode(byte[] cipherData,String key){
            return decode(cipherData,key.getBytes());
        }

        /**
         * 解密由安全异或加密方法加密的数据
         * @param cipherData 密文数据，密钥
         * @return 解密后的数据
         */
        public byte[] safeDecode(CryptoData cipherData){
            return XOREncoder.instance.safeEncode(cipherData);
        }

        /**
         * 解密由安全异或加密方法加密的数据
         * @param cipherData 密文数据
         * @param key 密钥
         * @return 解密后的数据
         */
        public byte[] safeDecode(byte[] cipherData,byte[] key){
            return safeDecode(new CryptoData(cipherData,key,null));
        }

        /**
         * 解密由安全异或加密方法加密的数据
         * @param cipherData 密文数据
         * @param key 密钥
         * @return 解密后的数据
         */
        public byte[] safeDecode(byte[] cipherData,String key){
            return safeDecode(cipherData,key.getBytes());
        }

    }

    /**
     * 获取异或加密编码器
     */
    public static XOREncoder getEncoder() {
        return XOREncoder.instance;
    }

    /**
     * 获取异或解密解码器
     */
    public static XORDecoder getDecoder() {
        return XORDecoder.instance;
    }

}
