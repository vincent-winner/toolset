package io.vincentwinner.toolset.random;

import io.vincentwinner.toolset.domain.unary.UnaryDomain;

/**
 * 随机长整数
 */
public class RandomLong extends Random<Long>{

    private static final long serialVersionUID = -5394644206686320639L;

    public RandomLong(){
        super();
    }

    public RandomLong(long min,long max){
        this(new UnaryDomain(new UnaryDomain.UnaryBaseDomain((double)min,(double)max)));
    }

    public RandomLong(UnaryDomain domains){
        super(domains);
    }

    /**
     * @see super#next()
     */
    @Override
    public Long next() {
        UnaryDomain.UnaryBaseDomain domain =  domains.getDomainList().get(RandomInt.randomInt(0,domains.getDomainList().size() - 1));
        value = randomLong(domain.getMin().floor(),domain.getMax().floor());
        return value;
    }

    /**
     * 生成随机长整数
     * @param min 最小值
     * @param max 最大值
     */
    public static long randomLong(long min,long max){
        return (long)(Math.random() * (max - min + 1) + min);
    }

}
