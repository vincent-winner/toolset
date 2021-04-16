package io.vincentwinner.toolset.random.weight;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 权重随机数
 *
 * 如有4个元素A、B、C、D，权重分别为1、2、3、4，随机结果中A:B:C:D的比例要为1:2:3:4。
 * 总体思路：累加每个元素的权重A(1)-B(3)-C(6)-D(10)，则4个元素的的权重管辖区间分别为[0,1)、[1,3)、[3,6)、[6,10)。
 * 然后随机出一个[0,10)之间的随机数。落在哪个区间，则该区间之后的元素即为按权重命中的元素。
 *
 * @param <T> 随机数据类型
 */
public abstract class WeightRandom<T> implements Serializable {

    private static final long serialVersionUID = -2530606928826485750L;

    private final TreeMap<Double,T> weightMap;

    public WeightRandom(){
        weightMap = new TreeMap<>();
    }

    public WeightRandom(List<WeightObject<T>> weightObjects){
        this();
        if(!weightObjects.isEmpty()) weightObjects.forEach(this::add);
    }

    /**
     * 增加对象与权重
     * @param value 对象值
     * @param weight 权重
     * @return this
     */
    public WeightRandom<T> add(T value, double weight){
        return add(new WeightObject<>(value,weight));
    }

    /**
     * 增加对象权重
     * @param weightObject 权重对象
     * @return this
     */
    public WeightRandom<T> add(WeightObject<T> weightObject) {
        if(null != weightObject) {
            final double weight = weightObject.getWeight();
            if(weightObject.getWeight() > 0) {
                double lastWeight = (this.weightMap.size() == 0) ? 0 : this.weightMap.lastKey();
                this.weightMap.put(weight + lastWeight, weightObject.getValue());// 权重累加
            }
        }
        return this;
    }

    public T next() {
        if(this.weightMap.isEmpty()) return null;
        final Random random = ThreadLocalRandom.current();
        final double randomWeight = this.weightMap.lastKey() * random.nextDouble();
        final SortedMap<Double, T> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }

    /**
     * 权重对象
     * @param <T> 随机对象类型
     */
    public static class WeightObject<T> implements Serializable{

        private static final long serialVersionUID = 5506463557541092406L;

        private T value;
        private final double weight;

        public WeightObject(T value, double weight) {
            this.value = value;
            this.weight = weight;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            long temp;
            temp = Double.doubleToLongBits(weight);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            WeightObject<?> other = (WeightObject<?>) obj;
            if (this.value == null) {
                if (other.value != null) {
                    return false;
                }
            } else if (!this.value.equals(other.value)) {
                return false;
            }
            return Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
        }
    }

}
