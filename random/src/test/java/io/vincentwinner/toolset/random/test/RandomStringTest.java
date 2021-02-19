package io.vincentwinner.toolset.random.test;

import io.vincentwinner.toolset.domain.StringDomain;
import io.vincentwinner.toolset.random.RandomString;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 测试随机字符串的生成
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandomStringTest {

    /**
     * 将随机字符串的生成范围限制在大写字母和二进制数字范围
     */
    private StringDomain[] domain = new StringDomain[]{
            StringDomain.UPPERCASE_LETTER,
            StringDomain.BIN_CHARS
    };

    /**
     * 随机生成长度为 16 的字符串
     * 总共生成 20 次
     */
    @Test
    public void _01_testRandomString(){
        RandomString random = new RandomString(16,domain);
        for(int i = 0 ; i < 20 ; i++){
            String s = random.next().toString();
            char[] charArray = s.toCharArray();
            Assert.assertEquals(16,s.length());
            for(char c : charArray){
                Assert.assertTrue(((int)c >= 65 && (int)c <= 90) || ((int)c >= 48 && (int)c <= 49));
            }
        }
        System.out.println("测试随机字符串生成完成，测试范围[ 大写字母 , 二进制字符(0,1) ]");
    }

}
