package io.vincentwinner.toolset.console.progress.style;

/**
 * 井号进度条样式
 * [##################  ]90.0%
 */
public class HashSignConsoleProgressStyle extends DefaultConsoleProgressStyle{

    @Override
    public String processChar() {
        return "#";
    }

}
