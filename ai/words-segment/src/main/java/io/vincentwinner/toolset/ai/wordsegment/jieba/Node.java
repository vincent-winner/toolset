package io.vincentwinner.toolset.ai.wordsegment.jieba;

public class Node {

    protected Character value;
    protected Node parent;

    protected Node(Character value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

}
