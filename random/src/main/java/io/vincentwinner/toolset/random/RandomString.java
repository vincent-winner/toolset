package io.vincentwinner.toolset.random;

import io.vincentwinner.toolset.core.domain.StringDomain;
import io.vincentwinner.toolset.core.domain.unary.UnaryDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 随机字符串
 * 默认生成字符串域：   大写字母 + 小写字母 + 数字
 * 默认字符串长度：     8
 */
public class RandomString extends Random<String>{

    private static final long serialVersionUID = -8517171265288367526L;

    private int stringSize;

    public RandomString(){
        this(8,StringDomain.LETTER_NUMBER);
    }

    public RandomString(UnaryDomain domains){
        super(domains);
        stringSize = 8;
    }

    public RandomString(int stringSize,UnaryDomain domains){
        super(domains);
        this.stringSize = stringSize;
    }

    public RandomString(int stringSize,StringDomain... stringDomains){
        List<UnaryDomain.UnaryBaseDomain> baseDomains = new ArrayList<>(stringDomains.length << 1);
        this.stringSize = stringSize;
        for(StringDomain domain : stringDomains){
            baseDomains.addAll(Arrays.asList(domain.getSelectedDomains()));
        }
        super.domains = new UnaryDomain(baseDomains);
        next();
    }

    /**
     * @see super#next()
     */
    @Override
    public String next() {
        return randomString(stringSize,domains);
    }

    /**
     * 获取随机字符串长度
     * @return 随机字符串长度
     */
    public int getStringSize() {
        return stringSize;
    }

    /**
     * 设置随机字符串长度
     * @param stringSize 随机字符串长度
     */
    public void setStringSize(int stringSize) {
        this.stringSize = stringSize;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * 生成随机字符串
     * @param size 字符串长度
     * @param domains 字符串随机范围
     * @return 随机生成的字符串
     */
    public static String randomString(int size, UnaryDomain... domains){
        StringBuilder sb = new StringBuilder(size);
        if(domains == null || domains.length == 0){
            domains = new UnaryDomain[]{
                    new UnaryDomain(new UnaryDomain.UnaryBaseDomain(48.,57.),
                    new UnaryDomain.UnaryBaseDomain(65.,90.),
                    new UnaryDomain.UnaryBaseDomain(97.,122.))
            };
        }
        for(int i = 0 ; i < size ; i++){
            UnaryDomain unaryDomain = domains[RandomInt.randomInt(0,domains.length - 1)];
            UnaryDomain.UnaryBaseDomain domain = unaryDomain.getDomainList().get(RandomInt.randomInt(0, unaryDomain.getDomainList().size() - 1));
            sb.append((char)RandomLong.randomLong(domain.getMin().floor(),domain.getMax().floor()));
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串
     * @param size 字符串长度
     * @param domains 字符串随机范围
     * @return 随机生成的字符串
     */
    public static String randomString(int size, StringDomain... domains){
        StringBuilder sb = new StringBuilder(size);
        if(domains == null || domains.length == 0){
            domains = new StringDomain[]{StringDomain.LETTER_NUMBER};
        }
        for(int i = 0 ; i < size ; i++){
            UnaryDomain.UnaryBaseDomain[] unaryDomains = domains[RandomInt.randomInt(0,domains.length - 1)].getSelectedDomains();
            UnaryDomain.UnaryBaseDomain domain = unaryDomains[RandomInt.randomInt(0,unaryDomains.length - 1)];
            sb.append((char)RandomLong.randomLong(domain.getMin().floor(),domain.getMax().floor()));
        }
        return sb.toString();
    }

}
