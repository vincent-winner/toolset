package io.vincentwinner.toolset.ai.wordsegment.test;

import io.vincentwinner.toolset.ai.wordsegment.WordsHandler;
import io.vincentwinner.toolset.ai.wordsegment.jieba.Keyword;
import io.vincentwinner.toolset.ai.wordsegment.jieba.SegToken;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WordHandlerTest {

    private final String text = "五月十八号上午，富柯慕慈太太到我们学校来演讲，她站在台上，举着一张纸，" +
            "上面写着西门十基督=彼得自己十基督=?我看见了之后，脑中忽然起了无数的感想。她的演讲，我几乎听不见了。" +
            "以西门的勇敢，渗在基督的爱里，便化合成了彼得，成了基督教的柱石。我要是渗在基督的爱里，又可得怎样的效果呢?" +
            "春天来了，花儿都开了，叶儿都舒展了，浅绿深红，争妍斗艳的，各自发扬他的鲜明。─然而假若世界上没有光明来照耀他" +
            "，反映到世人的眼里;任他怎样的鲜明，也看不出了，和枯花败叶，也没有分别了。世界上有了光明了，玫瑰和蒲公英" +
            "，一同受了光的照耀，反映到世人眼里;然而他们所贡献的颜色，是迥然不同的。慰悦感情的程度，也是有深浅的。" +
            "因为玫瑰自有他特具的丰神，和草地上的蒲公英自是云泥悬隔呵。" +
            "基督说：我是世界的光。又说：你们当趁着有光，信从这光，使你们成为光明之子。使徒约翰说，那是真光，照亮凡生在世上的人。" +
            "世人也各有他特具的才能，发挥了出来，也是花卉般争妍斗艳，然而假如他的天才，不笼盖在基督的真光之下，然后再反映出来;" +
            "结果只是枯寂，黯淡，不精神，无生意。也和走肉行尸没有分别。光是普照大千世界的，只在乎谁肯跟从他，谁愿做光明之子。" +
            "公英也愿意做玫瑰，然而他却不能就是玫瑰。─何曾是光明有偏向呢?只是玫瑰自己有他特具的丰神，因此笼盖在光明底下的时候，" +
            "他所贡献的，是别的花卉所不能贡献的。谁愿笼盖在真光之下?谁愿渗在基督的爱里?谁愿藉着光明的反映，发扬他特具的天才，" +
            "贡献人类以伟大的效果?请铭刻这个方程在你的脑中，时时要推求这方程的答案，就是。";

    @After
    public void afterAddSplitLine(){
        System.out.println("\n---------------------------分割线---------------------------");
    }

    @Test
    public void _01_testAnalyzeParagraph_IndexMode(){
        List<SegToken> words = WordsHandler.analyzeParagraph(text,false);
        words.forEach(System.out::println);
    }

    @Test
    public void _02_testAnalyzeParagraph_SearchMode(){
        List<SegToken> words = WordsHandler.analyzeParagraph(text,true);
        words.forEach(System.out::println);
    }

    @Test
    public void _03_testAnalyzeSentence(){
        AtomicInteger count = new AtomicInteger(0);
        List<String> words = WordsHandler.analyzeSentence(text);
        words.forEach(w -> {
            System.out.printf("|%s",w);
            if(count.get() == 25){
                System.out.println();
                count.set(0);
            }
            count.incrementAndGet();
        });
    }

    @Test
    public void _04_testAnalyzeWordsFreq(){
        List<Keyword> keywords = WordsHandler.analyzeWordsFreq(text, 10);
        keywords.forEach(System.out::println);
    }

}
