package io.vincentwinner.toolset.crypto.strategy;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 利用 MD5 算法对目标数据进行摘要
 */
public class MD5Crypto {

    public static class MD5Encoder extends Encoder{
        private static final MD5Encoder instance = new MD5Encoder();

        /**
         * MD5 摘要
         * @param data 二进制数据
         * @return 摘要结果（32位）
         */
        @Override
        public byte[] encode(CryptoData data) {
            return encode(data.getData()).getBytes();
        }

        /**
         * MD5 摘要
         * @param data 被摘要的数据
         * @return 摘要结果（32位）
         */
        public String encode(byte[] data){
            try {
                MessageDigest digest = MessageDigest.getInstance("md5");
                byte[] result = digest.digest(data);
                StringBuilder buffer = new StringBuilder();
                for (byte b : result) {
                    int number = b & 0xff;
                    String str = Integer.toHexString(number);
                    if (str.length() == 1) {
                        buffer.append("0");
                    }
                    buffer.append(str);
                }
                return buffer.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return "";
            }
        }

        /**
         * MD5 摘要
         * @param data 被摘要的字符串
         * @return 摘要结果（32位）
         */
        public String encode(String data){
            return encode(data.getBytes());
        }

        /**
         * 16位 MD5 摘要
         * @param data 被摘要的数据
         * @return 照耀结果（16位）
         */
        public String encode16(byte[] data) {
            return encode(data).substring(8,24);
        }

        /**
         * 16位 MD5 摘要
         * @param data 被摘要的字符串
         * @return 摘要结果（16位）
         */
        public String encode16(String data) {
            return encode16(data.getBytes());
        }
    }

    public static MD5Encoder getEncoder() {
        return MD5Encoder.instance;
    }

}
