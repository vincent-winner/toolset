package io.vincentwinner.toolset.core.lang;

/**
 * 继承此类使用默认克隆方法
 * @param <T> 克隆类型
 */
public abstract class CloneSupport<T> implements Cloneable {

    @Override
    public T clone(){
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
