package io.vincentwinner.toolset.domain;

import io.vincentwinner.toolset.domain.unary.Unary;

import java.util.ArrayList;
import java.util.Collections;

import static io.vincentwinner.toolset.domain.unary.UnaryDomain.UnaryBaseDomain;

/**
 * COMMON_SYMBOL_WITHOUT_SPACE         无空格普通符号
 * COMMON_SYMBOL                       普通符号
 * NUMBER                              阿拉伯数字
 * UPPERCASE_LETTER                    大写字母
 * LOWERCASE_LETTER                    小写字母
 * LETTER                              所有字母（大写和小写）
 * UPPERCASE_LETTER_NUMBER             大写字母和数字
 * LOWERCASE_LETTER_NUMBER             小写字母和数字
 * LETTER_NUMBER                       字母和数字（字母包含大写和小写）
 * ASCII_PRINT_CHARS                   ASCII打印字符（字母、数字、特殊字符、空格）
 * ASCII_PRINT_CHARS_WITHOUT_SPACE     无空格ASCII打印字符（字母、数字、特殊字符）
 * UPPERCASE_ASCII_PRINT_CHARS         大写ASCII打印字符（大写字母、数字、特殊字符）
 * LOWERCASE_ASCII_PRINT_CHARS         小写ASCII打印字符（小写字母、数字、特殊字符
 * UPPERCASE_HEX_CHARS                 大写16进制字符
 * LOWERCASE_HEX_CHARS                 小写16进制字符
 * DEC_CHARS                           10进制字符
 * OCT_CHARS                           8进制字符
 * BIN_CHARS                           2进制字符
 * CHINESE_CHARS                       中文字符
 * JAPANESE_CHARS_HIRAGANA             日文平假名
 * JAPANESE_CHARS_KATAKANA             日文片假名
 */
public enum StringDomain implements Domain<String> {

    /**
     * 无空格普通符号
     */
    COMMON_SYMBOL_WITHOUT_SPACE(new UnaryBaseDomain(33d,47d),new UnaryBaseDomain(58d,64d),new UnaryBaseDomain(91d,96d),new UnaryBaseDomain(123d,126d)),

    /**
     * 普通符号
     */
    COMMON_SYMBOL(new UnaryBaseDomain(32d,47d),new UnaryBaseDomain(58d,64d),new UnaryBaseDomain(91d,96d),new UnaryBaseDomain(123d,126d)),

    /**
     * 阿拉伯数字
     */
    NUMBER(new UnaryBaseDomain(48d,57d)),

    /**
     * 大写字母
     */
    UPPERCASE_LETTER(new UnaryBaseDomain(65d,90d)),

    /**
     * 小写字母
     */
    LOWERCASE_LETTER(new UnaryBaseDomain(97d,122d)),

    /**
     * 大写祖母和小写字母
     */
    LETTER(UPPERCASE_LETTER, LOWERCASE_LETTER),

    /**
     * 大写字母和数字
     */
    UPPERCASE_LETTER_NUMBER(NUMBER, UPPERCASE_LETTER),

    /**
     * 小写字母和数字
     */
    LOWERCASE_LETTER_NUMBER(NUMBER, LOWERCASE_LETTER),

    /**
     * 字母和数字
     */
    LETTER_NUMBER(NUMBER, LETTER),

    /**
     * ASCII打印字符（字母、数字、特殊字符、空格）
     */
    ASCII_PRINT_CHARS(LETTER_NUMBER,COMMON_SYMBOL),

    /**
     * 无空格ASCII打印字符（字母、数字、特殊字符）
     */
    ASCII_PRINT_CHARS_WITHOUT_SPACE(LETTER_NUMBER,COMMON_SYMBOL_WITHOUT_SPACE),

    /**
     * 大写ASCII打印字符（大写字母、数字、特殊字符）
     */
    UPPERCASE_ASCII_PRINT_CHARS(UPPERCASE_LETTER_NUMBER,COMMON_SYMBOL_WITHOUT_SPACE),

    /**
     * 小写ASCII打印字符（大写字母、数字、特殊字符）
     */
    LOWERCASE_ASCII_PRINT_CHARS(LOWERCASE_LETTER_NUMBER,COMMON_SYMBOL_WITHOUT_SPACE),

    /**
     * 大写16进制字符
     */
    UPPERCASE_HEX_CHARS(new UnaryBaseDomain(48d,57d),new UnaryBaseDomain(65d,70d)),

    /**
     * 小写16进制字符
     */
    LOWERCASE_HEX_CHARS(new UnaryBaseDomain(48d,57d),new UnaryBaseDomain(97d,102d)),

    /**
     * 10进制字符
     */
    DEC_CHARS(NUMBER),

    /**
     * 8进制字符
     */
    OCT_CHARS(new UnaryBaseDomain(48d,55d)),

    /**
     * 2进制字符
     */
    BIN_CHARS(new UnaryBaseDomain(48d,49d)),

    /**
     * 中文字符
     */
    CHINESE_CHARS(new UnaryBaseDomain(0x1.3804p14, 0x1.3f4ap15)),

    /**
     * 日文平假名
     */
    JAPANESE_CHARS_HIRAGANA(new UnaryBaseDomain(0x1.8208p13, 0x1.84f8p13)),

    /**
     * 日文片假名
     */
    JAPANESE_CHARS_KATAKANA(new UnaryBaseDomain(0x1.8508p13, 0x1.87f8p13)),
    ;

    private final UnaryBaseDomain[] selectedDomains;
    public final long SIZE;

    StringDomain(UnaryBaseDomain... unaryBaseDomains) {
        this.selectedDomains = unaryBaseDomains;
        long count = 0;
        for(UnaryBaseDomain domain : selectedDomains){
            count += domain.getSpan();
        }
        this.SIZE = count;
    }

    StringDomain(StringDomain... domains) {
        ArrayList<UnaryBaseDomain> resultDomains = new ArrayList<>(domains.length << 1);
        for(StringDomain domain : domains){
            Collections.addAll(resultDomains,domain.getSelectedDomains());
        }
        this.selectedDomains = resultDomains.toArray(new UnaryBaseDomain[0]);
        long count = 0;
        for(UnaryBaseDomain domain : this.selectedDomains){
            count += domain.getSpan();
        }
        this.SIZE = count;
    }

    public UnaryBaseDomain[] getSelectedDomains() {
        return selectedDomains;
    }

    @Override
    public boolean isInDomain(String element) {
        char[] charArray = element.toCharArray();
        for(char c : charArray){
            for(UnaryBaseDomain domain : getSelectedDomains()){
                if(domain.isInDomain(new Unary((double)c))){
                    return true;
                }
            }
        }
        return false;
    }

}
