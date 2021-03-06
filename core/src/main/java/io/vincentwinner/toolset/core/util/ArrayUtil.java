package io.vincentwinner.toolset.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组工具
 */
public class ArrayUtil {

    /**
     * 调用永不为 null 的 List
     * 数组不为空返回源数组，为空返回初始大小为 1 的 ArrayList
     * @param list 源数组
     * @param <T>  数组中的数据类型
     * @return 源数组或初始大小为 1 的 ArrayList
     */
    public static <T> List<T> requireNoneNull(List<T> list) {
        return defaultIfNull(list,new ArrayList<>(1));
    }

    /**
     * 如果目标列表为 null 则使用默认值
     * @param list 目标列表
     * @param defaultList 默认列表
     * @param <T> 列表中的数据类型
     * @return 目标列表或默认值
     */
    public static <T> List<T> defaultIfNull(List<T> list,List<T> defaultList){
        return list == null ? defaultList : list;
    }

}
