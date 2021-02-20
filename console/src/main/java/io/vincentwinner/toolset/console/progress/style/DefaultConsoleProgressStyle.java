package io.vincentwinner.toolset.console.progress.style;

/**
 * 默认进度条样式
 * [██████████████████  ]90.0%
 */
public class DefaultConsoleProgressStyle extends AbstractConsoleProgressStyle{

    @Override
    public int barLen(){
        return 20;
    }

    @Override
    public String processChar(){
        return "\u2588";
    }

    @Override
    public char space() {
        return '\u3000';
    }

    @Override
    public String processLeftBorder(){
        return "[";
    }

    @Override
    public String processRightBorder(){
        return "]";
    }

    @Override
    public String progressLeftContent(Double rate) {
        return "";
    }

    @Override
    public String progressMiddleContent(Double rate) {
        return "";
    }

    @Override
    public String progressRightContent(Double rate) {
        return percent(rate);
    }

}
