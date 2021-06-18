## 单词分割、词频分析模块

---

- 根据此项目进行修改：https://github.com/huaban/jieba-analysis

---
#### 目录
> #### [1.分析文本中的单词及出现位置](#analyzeParagraphSearchMode)
> #### [2.分析文本中的单词及出现位置(索引模式)](#analyzeParagraphIndexMode)
> #### [3.分析文本中的单词](#analyzeSentence)
> #### [4.分析词频](#analyzeWordsFreq)
---

#### <span id="analyzeParagraphSearchMode">1.分析文本中的单词及出现位置</span>

```java
import io.vincentwinner.toolset.ai.wordsegment.WordsHandler;
import io.vincentwinner.toolset.ai.wordsegment.jieba.SegToken;

import java.util.List;

public class Test {
    
    /**
     * [五月,0,2]
     * [十八号,2,5]
     * [上午,5,7]
     * [，,7,8]
     * [富柯慕慈,8,12]
     * [太太,12,14]
     * [到,14,15]
     * [我们,15,17]
     * [学校,17,19]
     * [来,19,20]
     * [演讲,20,22]
     */
    public static void main(String[] args) {
        String text = "五月十八号上午，富柯慕慈太太到我们学校来演讲";
        List<SegToken> words = WordsHandler.analyzeParagraph(text, false);
        words.forEach(System.out::println);
    }
}
```

#### <span id="analyzeParagraphIndexMode">2.分析文本中的单词及出现位置(索引模式)</span>

```java
import io.vincentwinner.toolset.ai.wordsegment.WordsHandler;

public class Test {

    /**
     * [五月,0,2]
     * [十八,2,4]
     * [八号,3,5]
     * [十八号,2,5]
     * [上午,5,7]
     * [，,7,8]
     * [富柯慕慈,8,12]
     * [太太,12,14]
     * [到,14,15]
     * [我们,15,17]
     * [学校,17,19]
     * [来,19,20]
     * [演讲,20,22]
     */
    public static void main(String[] args) {
        String text = "五月十八号上午，富柯慕慈太太到我们学校来演讲";
        List<SegToken> words = WordsHandler.analyzeParagraph(text,true);
        words.forEach(System.out::println);
    }
}
```

#### <span id="analyzeSentence">3.分析文本中的单词</span>

```java
import io.vincentwinner.toolset.ai.wordsegment.WordsHandler;

public class Test {

    /**
     * |五月|十八号|上午|，|富柯慕慈|太太|到|我们|学校|来|演讲
     */
    public static void main(String[] args) {
        String text = "五月十八号上午，富柯慕慈太太到我们学校来演讲";
        List<String> words = WordsHandler.analyzeSentence(text);
        words.forEach(w -> System.out.printf("|%s",w));
    }
}
```

#### <span id="analyzeWordsFreq">4.分析词频</span>

```java
import io.vincentwinner.toolset.ai.wordsegment.WordsHandler;
import io.vincentwinner.toolset.ai.wordsegment.jieba.Keyword;

public class Test {

    /**
     * [十八号,0.1986]
     * [富柯慕慈,0.1708]
     * [演讲,0.1143]
     * [五月,0.1077]
     * [太太,0.0975]
     * [上午,0.0892]
     * [学校,0.0813]
     */
    public static void main(String[] args) {
        String text = "五月十八号上午，富柯慕慈太太到我们学校来演讲";
        List<Keyword> words = WordsHandler.analyzeWordsFreq(text, 10);
        words.forEach(System.out::println);
    }
}
```

