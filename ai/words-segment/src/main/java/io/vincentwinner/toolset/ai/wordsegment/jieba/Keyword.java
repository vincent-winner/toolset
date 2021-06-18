package io.vincentwinner.toolset.ai.wordsegment.jieba;

/**
 * TF-IDF 算法
 */
public class Keyword implements Comparable<Keyword> {

    private double freq;
    private String keyword;

    /**
     * 获取词频
     * @return 词频
     */
    public double getFreq() {
        return freq;
    }

    protected void setFreq(double freq) {
        this.freq = freq;
    }

    /**
     * 获取关键词
     * @return 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    protected void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    protected Keyword(String keyword, double freq) {
        this.keyword = keyword;
        this.freq = (double) Math.round(freq * 10000) / 10000;
    }

    @Override
    public int compareTo(Keyword o) {
        return this.freq - o.freq > 0 ? -1 : 1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
        long temp;
        temp = Double.doubleToLongBits(freq);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Keyword other = (Keyword) obj;
        if (keyword == null) {
            return other.keyword == null;
        } else {
            return keyword.equals(other.keyword);
        }
    }

    @Override
    public String toString() {
        return "[" + keyword + "," + freq + "]";
    }

}
