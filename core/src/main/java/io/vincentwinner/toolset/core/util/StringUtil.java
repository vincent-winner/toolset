package io.vincentwinner.toolset.core.util;

/**
 * 操作字符串工具
 */
public class StringUtil {

    /**
     * 调用永不为 null 的 String
     * 字符串为 null 返回 空字符串 ""
     * 字符串不为 null 返回源字符串
     * @param string 需要处理的字符串
     * @return ""或源字符串
     */
    public static CharSequence requireNoneNull(CharSequence string){
        return defaultIfNull(string,"");
    }

    /**
     * 如果字符序列为 null 则使用默认值
     * @param string 字符串
     * @param defaultValue 默认值
     * @return 默认值或源字符串
     */
    public static CharSequence defaultIfNull(CharSequence string,CharSequence defaultValue){
        return string == null ? defaultValue : string;
    }

}
