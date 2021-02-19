package io.vincentwinner.toolset.crypto.strategy;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.Encoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64 编码、解码
 * 本类中所有 CryptoData 中的 key（密钥） 和 iv（初始化向量） 属性忽略即可(赋值为null)
 */
public class Base64Crypto {

    /**
     * Base64 编码器
     */
    public static class Base64Encoder extends Encoder{
        private static final Base64Encoder instance = new Base64Encoder();
        private final Base64.Encoder encoder = Base64.getEncoder();

        /**
         * 将二进制数据加密
         * @param data 二进制数据
         * @return 加密后的二进制数据
         */
        @Override
        public byte[] encode(CryptoData data) {
            return encoder.encode(data.getData());
        }

        /**
         * 将二进制数据加密
         * @param data 二进制数据
         * @return 加密后的二进制数据
         */
        public byte[] encode(byte[] data){
            return encoder.encode(data);
        }

        /**
         * 将二进制数据加密为字符串
         * @param data 二进制数据
         * @return 加密后的字符串
         */
        public String encodeToString(byte[] data){
            return encoder.encodeToString(data);
        }

        /**
         * 加密字符串为字符串
         * 如果不给定编码，默认认为被加密的字符串为 UTF-8 编码
         * @param str 被加密的字符串
         * @return 加密后的字符串
         */
        public String encodeString(String str){
            return encodeString(StandardCharsets.UTF_8,str);
        }

        /**
         * 加密字符串
         * @param charset 被加密的字符串的字符集
         * @param str 被加密的字符串
         * @return 加密后的字符串
         */
        public String encodeString(Charset charset,String str){
            return encodeToString(str.getBytes(charset));
        }
    }

    /**
     * Base64 解码器
     */
    public static class Base64Decoder extends Decoder{
        private static final Base64Decoder instance = new Base64Decoder();
        private final Base64.Decoder decoder = Base64.getDecoder();

        /**
         * 将被加密的二进制数据解密
         * @param cipherData 被加密的数据
         * @return 解密后的二进制数据
         */
        @Override
        public byte[] decode(CryptoData cipherData) {
            return decoder.decode(cipherData.getData());
        }

        /**
         * 将被加密的二进制数据解密
         * @param cipherData 被加密的数据
         * @return 解密后的二进制数据
         */
        public byte[] decode(byte[] cipherData){
            return decoder.decode(cipherData);
        }

        /**
         * 将被加密的字符串解密为二进制数据
         * 默认字符串字符编码为 UTF-8
         * @param cipherStr 加密后的字符串
         * @return 解密后的二进制数据
         */
        public byte[] decode(String cipherStr){
            return decode(StandardCharsets.UTF_8,cipherStr);
        }

        /**
         * 将被加密的字符串解密为二进制数据
         * @param cipherStr 加密后的字符串
         * @return 解密后的二进制数据
         */
        public byte[] decode(Charset charset,String cipherStr){
            return decode(new CryptoData(cipherStr.getBytes(charset),null,null));
        }

        /**
         * 将被加密的字符串解密
         * 本方法将用传入的字符集解码字符串和生成新的字符串
         * @param charset 字符集
         * @param cipherStr 加密后的字符串
         * @return 解密后的数据
         */
        public String decodeString(Charset charset,String cipherStr){
            return new String(decode(charset,cipherStr),charset);
        }

        /**
         * 将被加密的字符串解密
         * 本方法认为传入字符串的字符集为 UTF-8 并且会构建字符集为 UTF-8 的新字符串
         * @param cipherStr 加密后的字符串
         * @return 解密后的数据
         */
        public String decodeString(String cipherStr){
            return decodeString(StandardCharsets.UTF_8,cipherStr);
        }
    }

    /**
     * 获取 Base64 编码器
     */
    public static Base64Encoder getEncoder() {
        return Base64Encoder.instance;
    }

    /**
     * 获取 Base64 解码器
     */
    public static Base64Decoder getDecoder() {
        return Base64Decoder.instance;
    }

}
