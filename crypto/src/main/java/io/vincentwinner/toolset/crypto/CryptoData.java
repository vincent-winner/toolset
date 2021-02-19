package io.vincentwinner.toolset.crypto;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.crypto.spec.IvParameterSpec;
import java.io.Serializable;

/**
 * 加密数据
 */
public class CryptoData implements Serializable,Cloneable {

    private static final long serialVersionUID = 1383436587267060424L;

    @NotNull private byte[] data;// 加密数据
    @Nullable private byte[] key;// 密钥
    @Nullable private IvParameterSpec iv;// 初始化向量（长度不为 16bit 则抛出异常）

    public CryptoData() {
    }

    public CryptoData(@NotNull byte[] data, @Nullable byte[] key, @Nullable IvParameterSpec iv) {
        this.data = data;
        this.key = key;
        if(iv != null && iv.getIV().length != 16) throw new IllegalArgumentException("初始化向量长度必须为 16 byte");
        this.iv = iv;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public IvParameterSpec getIv() {
        return iv;
    }

    public void setIv(IvParameterSpec iv) {
        if(iv != null && iv.getIV().length != 16) throw new IllegalArgumentException("初始化向量长度必须为 16 byte");
        this.iv = iv;
    }

    @Override
    protected CryptoData clone() throws CloneNotSupportedException {
        return (CryptoData) super.clone();
    }

}
