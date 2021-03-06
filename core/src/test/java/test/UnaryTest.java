package test;

import io.vincentwinner.toolset.core.domain.StringDomain;
import io.vincentwinner.toolset.core.domain.unary.Unary;
import io.vincentwinner.toolset.core.domain.unary.UnaryDomain;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.function.Predicate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnaryTest {

    @Test
    public void _01_testUnary(){
        Unary unary = new Unary(10d);
        Assert.assertEquals(10d, unary.getX(), 1.0E-16);
        unary = new Unary(10.00000000);
        Assert.assertEquals(10d, unary.getX(), 1.0E-16);
        unary = new Unary(10.0000000000000001);
        Assert.assertEquals(10.0000000000000001, unary.getX(), 1.0E-16);
    }

    @Test
    public void _01_testUnaryDomain(){
        UnaryDomain unaryDomain = new UnaryDomain();
        unaryDomain.addDomain(new UnaryDomain.UnaryBaseDomain(1d,3d));
        unaryDomain.addDomain(new UnaryDomain.UnaryBaseDomain(24d,31d));
        Unary unary = new Unary(14d);
        Assert.assertFalse(unaryDomain.isInDomain(unary));
        unary = new Unary(1d);
        Assert.assertTrue(unaryDomain.isInDomain(unary));
        unary = new Unary(25d);
        Assert.assertTrue(unaryDomain.isInDomain(unary));
    }

    @Test
    public void _03_testStringDomain(){
        class CheckArray extends ArrayList<String>{
            private static final long serialVersionUID = 1L;
            public boolean check(Predicate<String> rule){
                for(String s : this){
                    if(!rule.test(s)) return false;
                }
                return true;
            }
        }
        CheckArray chinese = new CheckArray();
        chinese.add("你好");
        chinese.add("龍龍");
        chinese.add("約束");
        chinese.add("世界");
        CheckArray binary = new CheckArray();
        binary.add("00110001101010110");
        binary.add("00110110101010110110");
        binary.add("0001101010101010101010110");
        binary.add("0010101010101010110");
        CheckArray lowercase_hex = new CheckArray();
        lowercase_hex.add("c4ca4238a0b923820dcc509a6f75849b");
        lowercase_hex.add("c81e728d9d4c2f636f067f89cc14862c");
        lowercase_hex.add("eccbc87e4b5ce2fe28308fd9f2a7baf3");
        lowercase_hex.add("a87ff679a2f3e71d9181a67b7542122c");
        Assert.assertTrue(chinese.check(StringDomain.CHINESE_CHARS::isInDomain));
        chinese.set(0,"$%^&");
        Assert.assertFalse(chinese.check(StringDomain.CHINESE_CHARS::isInDomain));
        Assert.assertTrue(binary.check(StringDomain.BIN_CHARS::isInDomain));
        binary.set(0,"#%$!@#");
        Assert.assertFalse(binary.check(StringDomain.BIN_CHARS::isInDomain));
        Assert.assertTrue(lowercase_hex.check(StringDomain.LOWERCASE_HEX_CHARS::isInDomain));
        lowercase_hex.set(0,"012FF");
        Assert.assertFalse(lowercase_hex.check(StringDomain.LOWERCASE_HEX_CHARS::isInDomain));
    }

}
