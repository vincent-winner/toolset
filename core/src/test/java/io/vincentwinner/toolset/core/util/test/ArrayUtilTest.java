package io.vincentwinner.toolset.core.util.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.vincentwinner.toolset.core.util.ArrayUtil.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArrayUtilTest {

    @Test
    public void _01_testRequireNoneNull(){
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        Assert.assertNotNull(requireNoneNull(list1));
        Assert.assertNotNull(requireNoneNull(list2));
        list1 = null;
        list2 = null;
        Assert.assertNotNull(requireNoneNull(list1));
        Assert.assertNotNull(requireNoneNull(list2));
    }

}
