package io.vincentwinner.toolset.core.lang;

import java.io.Serializable;
import java.util.Objects;

/**
 * 可变键值对象，用于一次性保存两个相对应的值
 * 构造成功后值可变，需要不可变类型使用 {@link Pair}
 * 支持克隆和序列化
 * @param <K> 键类型
 * @param <V> 值类型
 */
public class AlterablePair<K,V> extends CloneSupport<AlterablePair<K,V>> implements Serializable {

    private static final long serialVersionUID = -2764411140215086115L;

    private K key;
    private V value;

    /**
     * @param key   键
     * @param value 值
     */
    public static <K, V> Pair<K, V> valueOf(K key, V value) {
        return new Pair<>(key, value);
    }

    /**
     * @param key   键
     * @param value 值
     */
    public AlterablePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取键
     * @return 键
     */
    public K getKey() {
        return this.key;
    }

    /**
     * 获取值
     * @return 值
     */
    public V getValue() {
        return this.value;
    }

    /**
     * 设置键
     * @param key 键
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * 设置值
     * @param value 值
     */
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key.toString() +
                ", value=" + value.toString() +
                '}';
    }

    /**
     * 判断相等的条件是键值都相等
     * 会调用键和值类型的 equals 方法判断
     * 键和值都相等可认为 Pair 对象相等
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Pair) {
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(getKey(), pair.getKey()) &&
                    Objects.equals(getValue(), pair.getValue());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

}
