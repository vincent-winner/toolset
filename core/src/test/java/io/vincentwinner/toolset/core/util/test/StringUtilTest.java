package io.vincentwinner.toolset.core.util.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.vincentwinner.toolset.core.util.StringUtil.*;

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

}
