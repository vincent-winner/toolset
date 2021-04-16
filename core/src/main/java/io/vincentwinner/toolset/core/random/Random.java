package io.vincentwinner.toolset.core.random;

import io.vincentwinner.toolset.core.domain.unary.UnaryDomain;

import java.io.Serializable;

/**
 * 随机基类
 * 默认构造方法随机值为 0 ~ 1亿
 * @param <T> Integer、Long、Double
 *           随机字符串泛型参数为 StringBuffer
 */
public abstract class Random<T> implements Serializable {

    private static final long serialVersionUID = -1711558235441925518L;

    //随机值
    protected T value;

    //随机范围
    protected UnaryDomain domains;

    /**
     * 设置随机域，并在域中随机取值
     * 默认域 0 ~ 1亿
     */
    public Random(){
        this(new UnaryDomain(new UnaryDomain.UnaryBaseDomain(0x0.0p0, 0x1.dcd65p29)));
    }
    public Random(UnaryDomain domains){
        this.domains = domains;
        next();
    }

    /**
     * 在随机域中随机取值，并立刻返回随机到的值
     * @return 随机到的值
     */
    public abstract T next();

    /**
     * 返回本类对象中保存的随机值
     * @return 本类对象中保存的随机值
     */
    public T value(){
        return this.value;
    }

    /**
     * 获取随机域
     * @return 随机域
     */
    public UnaryDomain getDomains() {
        return domains;
    }

    /**
     * 设置随机域
     * @param domains 随机域
     */
    public void setDomains(UnaryDomain domains) {
        this.domains = domains;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
