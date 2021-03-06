package io.vincentwinner.toolset.random.test;

import io.vincentwinner.toolset.core.domain.unary.UnaryDomain;
import io.vincentwinner.toolset.random.Random;
import io.vincentwinner.toolset.random.RandomDouble;
import io.vincentwinner.toolset.random.RandomInt;
import io.vincentwinner.toolset.random.RandomLong;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 测试随机数的生成
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandomNumberTest {

    /**
     * 测试数字生成范围限制在 [10.0 ~ 20.0 , 3.0 ~ 6.0] 之间
     */
    private final UnaryDomain domain = new UnaryDomain(
            new UnaryDomain.UnaryBaseDomain(10d,20d),
            new UnaryDomain.UnaryBaseDomain(3d,6d)
    );

    /**
     * 用于测试指定随机数生成器
     * 随机 20 次，测试是否全部命中在给定范围之内
     * @param r 随机数生成器
     * @param type 随机数类型
     * @param <T> 随机数类型
     */
    private <T extends Number> void assertNumber(Random<? extends Number> r, Class<T> type){
        for(int i = 0 ; i < 20 ; i++){
            double num = ((T)(r.next())).doubleValue();
            if(!((num >= 10d && num <=20d) || (num >= 3d && num <=6d))){
                System.out.println(num);
            }
            Assert.assertTrue("数据未命中在指定范围内",(num >= 10d && num <=20d) || (num >= 3d && num <=6d) );
        }
    }

    /**
     * 测试随机整数
     */
    @Test
    public void _01_testRandomInt(){
        RandomInt random = new RandomInt(domain);
        assertNumber(random,Integer.class);
        System.out.println("测试随机整数（Integer）完成，测试范围[ (10 ~ 20) , (3 ~ 6) ]");
    }

    /**
     * 测试随机长整型数字
     */
    @Test
    public void _02_testRandomLong(){
        RandomLong random = new RandomLong(domain);
        assertNumber(random,Long.class);
        System.out.println("测试随机长整数（Long）完成，测试范围[ (10L ~ 20L) , (3L ~ 6L) ]");
    }

    /**
     * 测试随机双精度浮点数
     */
    @Test
    public void _03_testRandomDouble(){
        RandomDouble random = new RandomDouble(domain);
        assertNumber(random,Double.class);
        System.out.println("测试随机双精度浮点数（Double）完成，测试范围[ (10.0 ~ 20.0) , (3.0 ~ 6.0) ]");
    }
}
