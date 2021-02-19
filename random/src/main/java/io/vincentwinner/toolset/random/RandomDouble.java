package io.vincentwinner.toolset.random;

import io.vincentwinner.toolset.domain.UnaryDomain;

/**
 * 随机双精度浮点数
 */
public class RandomDouble extends Random<Double>{

    private static final long serialVersionUID = -766468211338372026L;

    public RandomDouble(){
        super();
    }

    public RandomDouble(double min,double max){
        this(new UnaryDomain(new UnaryDomain.UnaryBaseDomain(min,max)));
    }

    public RandomDouble(UnaryDomain domains){
        super(domains);
    }

    /**
     * @see super#next()
     */
    @Override
    public Double next() {
        UnaryDomain.UnaryBaseDomain domain =  domains.getDomainList().get(RandomInt.randomInt(0,domains.getDomainList().size() - 1));
        value = randomDouble(domain.getMin().getX(),domain.getMax().getX());
        return value;
    }

    /**
     * 生成随机双精度浮点数
     * @param min 最小值
     * @param max 最大值
     */
    public static double randomDouble(double min,double max){
        return Math.random() * (max - min) + min;
    }
}
