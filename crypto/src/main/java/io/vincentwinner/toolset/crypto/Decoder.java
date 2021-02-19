package io.vincentwinner.toolset.crypto;

/**
 * 解码器
 */
public abstract class Decoder implements Cloneable{

    /**
     * 将加密的数据解码，并返回解密之后的数据
     * @param cipherData 被加密的数据
     * @return 解密之后的数据
     */
    public abstract byte[] decode(CryptoData cipherData);

    @Override
    public Decoder clone() throws CloneNotSupportedException{
        return (Decoder) super.clone();
    }

}
