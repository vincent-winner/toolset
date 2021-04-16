package io.vincentwinner.toolset.core.random.test;

import io.vincentwinner.toolset.core.random.WeightRandom;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeightRandomNumberTest {

    @Test
    public void _01_testWeightRandom(){
        int count_1,count_5,count_14,count_1764;
        count_1 = count_5 = count_14 = count_1764 = 0;
        WeightRandom<Integer> random = new WeightRandom<>();
        random
            .add(1,0.1)
            .add(5,0.2)
            .add(14,0.3)
            .add(1764,0.4);
        for (int i = 0; i < 1E5; i++) {
            switch (random.next()){
                case 1 : count_1++;break;
                case 5 : count_5++;break;
                case 14 : count_14++;break;
                case 1764 : count_1764++;break;
            }
        }
        System.out.println("1 出现 " + count_1 + "次");
        System.out.println("5 出现 " + count_5 + "次");
        System.out.println("14 出现 " + count_14 + "次");
        System.out.println("1764 出现 " + count_1764 + "次");
        double sum = count_1 + count_5 + count_14 + count_1764;
        System.out.println(
                "比例: " +
                Math.round(count_1 / sum * 10) + ":" +
                Math.round(count_5 / sum * 10) + ":" +
                Math.round(count_14 / sum * 10) + ":" +
                Math.round(count_1764 / sum * 10)
        );
        Assert.assertEquals(1, Math.round(count_1 / sum * 10));
        Assert.assertEquals(2, Math.round(count_5 / sum * 10));
        Assert.assertEquals(3, Math.round(count_14 / sum * 10));
        Assert.assertEquals(4, Math.round(count_1764 / sum * 10));
    }

}
