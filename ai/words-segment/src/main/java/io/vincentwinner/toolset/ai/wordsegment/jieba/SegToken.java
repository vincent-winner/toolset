package io.vincentwinner.toolset.ai.wordsegment.jieba;

public class SegToken {

    private final String word;

    private final int startOffset;

    private final int endOffset;

    protected SegToken(String word, int startOffset, int endOffset) {
        this.word = word;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    /**
     * 获取被分解的单词
     * @return 被拆解的单词
     */
    public String getWord() {
        return word;
    }

    /**
     * 获取被分解的单词的起始位置（位置从 0 开始）
     * @return 被分解的单词的起始位置
     */
    public int getStartOffset() {
        return startOffset;
    }

    /**
     * 获取被分解的单词的结束位置（第一个字的位置为 0）
     * @return 被分解的单词的结束位置
     */
    public int getEndOffset() {
        return endOffset;
    }

    @Override
    public String toString() {
        return "[" + word +
                "," + startOffset +
                "," + endOffset +
                ']';
    }
}
