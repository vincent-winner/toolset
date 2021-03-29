package io.vincentwinner.toolset.core.util.test;

import io.vincentwinner.toolset.core.util.StringUtil;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.vincentwinner.toolset.core.util.StringUtil.requireNoneNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringUtilTest {

    @Test
    public void _01_testRequireNoneNull(){
        String s1 = "123";
        String s2 = new String("456");
        Assert.assertNotNull(requireNoneNull(s1));
        Assert.assertNotNull(requireNoneNull(s2));
        s1 = null;
        s2 = null;
        Assert.assertNotNull(requireNoneNull(s1));
        Assert.assertNotNull(requireNoneNull(s2));
    }

    @Test
    public void _02_testIsBlank(){
        String s1 = "\t\r\n";
        String s2 = " ";
        String s3 = "";
        String s4 = "\u3000";
        String s5 = null;
        Assert.assertTrue(StringUtil.isBlank(s1));
        Assert.assertTrue(StringUtil.isBlank(s2));
        Assert.assertTrue(StringUtil.isBlank(s3));
        Assert.assertTrue(StringUtil.isBlank(s4));
        Assert.assertTrue(StringUtil.isBlank(s5));
    }

}
