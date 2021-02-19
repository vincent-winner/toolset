package io.vincentwinner.toolset.crypto;

/**
 * 编码器
 */
public abstract class Encoder implements Cloneable {

    /**
     * 对二进制数据进行编码
     * @param data 二进制数据
     * @return 编码之后的数据
     */
    public abstract byte[] encode(CryptoData data);

    @Override
    public Encoder clone() throws CloneNotSupportedException {
        return (Encoder) super.clone();
    }

}
