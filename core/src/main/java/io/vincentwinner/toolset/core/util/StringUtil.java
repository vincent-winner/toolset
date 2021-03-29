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

    /**
     * 判断字符串是否为空
     * @param string 字符串
     * @return 字符串是否为空
     */
    public static boolean isEmpty(CharSequence string){
        return string == null || string.length() == 0;
    }

    /**
     * 判断字符串是否全部为空白字符
     * 当字符串为空或null时依然算作空白字符串
     * <pre>
     *      ""          true
     *      " \t\r\n"   true
     *      null        true
     *      " "         true
     *      "  1   "    false
     * </pre>
     * @param string 需要判断的字符串
     * @return 字符串是否全部为空白字符
     */
    public static boolean isBlank(CharSequence string){
        if(isEmpty(string)) return true;
        for(int i = 0 ; i < string.length() ; i++){
            char c = string.charAt(i);
            if(!Character.isWhitespace(c)
                    && !Character.isSpaceChar(c)
                    && c != '\ufeff'
                    && c != '\u202a'){
                return false;
            }
        }
        return true;
    }

}
