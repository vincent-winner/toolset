package io.vincentwinner.toolset.ai.wordsegment;

import io.vincentwinner.toolset.ai.wordsegment.jieba.JiebaSegmenter;
import io.vincentwinner.toolset.ai.wordsegment.jieba.Keyword;
import io.vincentwinner.toolset.ai.wordsegment.jieba.SegToken;
import io.vincentwinner.toolset.ai.wordsegment.jieba.TFIDFAnalyzer;

import java.util.List;

public class WordsHandler {

    private static final class Instances {
        private static final JiebaSegmenter SEGMENT = new JiebaSegmenter();
        private static final TFIDFAnalyzer FREQ_ANALYZER = new TFIDFAnalyzer();
    }

    private WordsHandler(){}

    /**
     * 分析文本中的单词并返回每个单词在文本中的位置
     * @param paragraph 需要分析的文本
     * @param useIndexMode 是否使用索引模式（请自行找句子试试选项效果）
     * @return {@link SegToken}                   分析出的单词和位置
     *         {@link SegToken#getWord()}         获取被分析出的单词
     *         {@link SegToken#getStartOffset()}  获取被分析出的单词在文本中的起始位置（从0开始）
     *         {@link SegToken#getEndOffset()}    获取被分析出的单词在文本中的结束位置（位置从0开始）
     */
    public static List<SegToken> analyzeParagraph(String paragraph, boolean useIndexMode){
        return Instances.SEGMENT.process(
                paragraph,
                useIndexMode ?
                        JiebaSegmenter.SegMode.INDEX :
                        JiebaSegmenter.SegMode.SEARCH
        );
    }

    /**
     * 分析文本中的单词
     * @param sentence 需要分析的文本
     * @return 文本中存在的单词
     */
    public static List<String> analyzeSentence(String sentence){
        return Instances.SEGMENT.sentenceProcess(sentence);
    }

    /**
     * 词频分析
     * 如果 topN 大于句子中的最大单词数量，则保留所有分析结果
     * @param text 需要分析的文本
     * @param topN 保留前 topN 个单词的分析结果
     * @return 出现频率排名前 topN 个的单词和单词出现频率
     *         {@link Keyword}              单词和单词的出现频率
     *         {@link Keyword#getKeyword()} 获取单词
     *         {@link Keyword#getFreq()}    获取单词出现频率
     */
    public static List<Keyword> analyzeWordsFreq(String text, int topN){
        return Instances.FREQ_ANALYZER.analyze(text,topN);
    }

}
