## Random 随机模块

---
#### 目录
> #### [1. 基本使用方法](#all) 
> #### [2. 随机双精度浮点数（Double）](#double) 
> #### [3. 随机整数（Integer）](#int)
> #### [4. 随机长整数（Long）](#long)
> #### [5. 随机字符串（String）](#string)
---

### 注意：
```
本篇文章中用类似于 [ 1 - 3 ] 的形式表示范围
以随机整数为例
[ 1 - 3 ]                 代表 1,2,3
[ 1 - 3 ] , [ 12 - 14 ]   代表 1,2,3,12,13,14

如果只需要在单个区间内（指定最大值最小值）生成随机数，可以直接在构造函数内填写最小值和最大值
形如 Random<Integer> random = new RandomInt(最小值,最大值);

如果给定的范围包含给定的小范围，不会出现任何错误
如：[ 3 - 6 ] , [ 0 - 10 ]
则 0 - 10 范围内的数字都会被随机到
```

#### <span id="all">1.生成各种随机内容</span>

```java
import io.vincentwinner.toolset.random.*;

public class RandomTest {
    public static void main(String[] args) {
        // XXX 为要随机的类型
        Random<?> random = new RandomXXX();
        System.out.println(random.next());
    }
}
```

#### <span id="double">2.在指定范围内生成随机双精度浮点数（Double）</span>

```java
import io.vincentwinner.toolset.domain.unary.UnaryDomain;
import io.vincentwinner.toolset.random.*;

public class RandomDoubleTest {

    /**
     * 在指定范围 [ 1 - 4 ] , [ 16 - 28 ] 内生成随机数
     */
    public static void main(String[] args) {
        Random<Double> random = new RandomDouble(
                new UnaryDomain(1d,4d),
                new UnaryDomain(16d,28d));
        for (int i = 0; i < 10; i++) {
            System.out.println(random.next());
        }
    }
}
```
#### <span id="int">3.在指定范围内生成整数（Integer）</span>
```java
import io.vincentwinner.toolset.domain.unary.UnaryDomain;
import io.vincentwinner.toolset.random.*;

public class RandomIntTest {

    /**
     * 在指定范围 [ 1 - 5 ] , [ 2 - 8 ] 内生成随机数
     */
    public static void main(String[] args) {
        Random<Integer> random = new RandomInt(
                new UnaryDomain(1d,5d),
                new UnaryDomain(2d,8d));
        for (int i = 0; i < 10; i++) {
            System.out.println(random.next());
        }
    }
}
```

#### <span id="long">4.在指定范围内生成长整数（Long）</span>
```java
/**
 * 参考 3.在指定范围内生成整数（Integer） 的方法
 * 将 new RandomInt 改为 new RandomLong 即可
 */
```

#### <span id="string">5.在指定范围内生成字符串（String）</span>

```java
import io.vincentwinner.toolset.domain.StringDomain;
import io.vincentwinner.toolset.domain.unary.UnaryDomain;
import io.vincentwinner.toolset.random.*;

public class RandomStringTest {

    /**
     * 在 [ 数字 ] , [ 日文平假名 ] 范围内生成随机字符串
     * 注意：为了避免字符串频繁拼接生成大量无用的String对象，泛型参数应为 StringBuffer
     */
    public static void main(String[] args) {
        Random<StringBuffer> random = new RandomString(
                StringDomain.NUMBER,
                StringDomain.JAPANESE_CHARS_HIRAGANA
        );
        for (int i = 0; i < 10; i++) {
            System.out.println(random.next());
        }
    }
}
```