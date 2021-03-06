package io.vincentwinner.toolset.random;

import io.vincentwinner.toolset.core.domain.unary.UnaryDomain;

/**
 * 随机整数
 */
public class RandomInt extends Random<Integer>{

    private static final long serialVersionUID = 8717057936358267790L;

    public RandomInt(){
        super();
    }

    public RandomInt(int min,int max){
        this(new UnaryDomain(new UnaryDomain.UnaryBaseDomain((double)min,(double)max)));
    }

    public RandomInt(UnaryDomain domains){
        super(domains);
    }

    /**
     * @see super#next()
     */
    @Override
    public Integer next(){
        UnaryDomain.UnaryBaseDomain domain =  domains.getDomainList().get(randomInt(0,domains.getDomainList().size() - 1));
        value = randomInt((int)(domain.getMin().floor() & 0x7fffffff),(int)(domain.getMax().floor() & 0x7fffffff));
        return value;
    }

    /**
     * 生成随机整数
     * @param min 最小值
     * @param max 最大值
     */
    public static int randomInt(int min,int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }

}
