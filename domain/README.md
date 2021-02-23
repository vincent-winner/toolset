## Domain 域模块
本模块用来配合其他模块使用，单独引用可以用来判断范围

---
#### 目录
> #### [1. 判断一元数是否在一元范围之内](#unary)
> #### [2. 判断字符串是否属于指定范围](#string)
> #### [3. 巧妙利用该包中的内容过滤数据](#string_array)

---

#### <span id="unary">1.判断一元数是否在一元范围之内</span>
```java
import io.vincentwinner.toolset.domain.unary.Unary;
import io.vincentwinner.toolset.domain.unary.UnaryDomain;

public class Test {
    public static void main(String[] args) {
        UnaryDomain unaryDomain = new UnaryDomain(1d, 10d);
        Unary num = new Unary(5d);
        
        // true
        System.out.println(unaryDomain.isInDomain(num));
        
        // false
        num = new Unary(11d);
        System.out.println(unaryDomain.isInDomain(num));
    }
}
```

#### <span id="string">2.判断字符串是否属于指定范围</span>
```java
import io.vincentwinner.toolset.domain.StringDomain;

public class Test {
    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "123Abc";
        String str3 = "你好";
        
        // "123" 属于 "数字" 范围，返回true
        StringDomain.NUMBER.isInDomain(str1);
        
        // "123Abc" 含有大写字母A，所以不属于 "小写16进制字符串" 范围，返回false
        StringDomain.LOWERCASE_HEX_CHARS.isInDomain(str2);
        
        // "你好" 属于 "汉字（包括简体和繁体）" 范围，返回true 
        StringDomain.CHINESE_CHARS.isInDomain(str3);
    }
}
```

#### <span id="string_array">3.巧妙利用该包中的内容过滤数据</span>

```java
import io.vincentwinner.toolset.domain.StringDomain;

import java.util.ArrayList;
import java.util.function.Predicate;

class CheckableArray extends ArrayList<String> {
    /**
     * 检查数组中的每一项是否都符合给定条件，有一项不符合即为不符合
     * @param condition 条件
     * @return 数组的每一项是否都满足条件
     */
    public boolean check(Predicate<String> condition) {
        for (String s : this) {
            if (!condition.test(s)) return false;
        }
        return true;
    }
}

public class Test {
    public static void main(String[] args) {
        CheckableArray array = new CheckableArray() {{
            add("10");
            add("2424241616");
            add("17474150154020abc");
        }};

        // 数组中的每一项都属于 "小写16进制字符串" 范围，返回 true 
        array.check(StringDomain.LOWERCASE_HEX_CHARS::isInDomain);
        
        // 数组中的每一项都属于 "字母和数字" 范围，返回true
        array.check(StringDomain.LETTER_NUMBER::isInDomain);
        
        // 数组中的数据不是简体中文或繁体中文，返回false
        array.check(StringDomain.CHINESE_CHARS::isInDomain);
    }
}
```