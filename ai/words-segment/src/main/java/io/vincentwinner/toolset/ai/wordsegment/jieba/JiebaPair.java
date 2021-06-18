package io.vincentwinner.toolset.ai.wordsegment.jieba;

public class JiebaPair<K> {

    protected K key;
    protected Double freq = 0.0;

    protected JiebaPair(K key, double freq) {
        this.key = key;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "Candidate [key=" + key + ", freq=" + freq + "]";
    }

}
