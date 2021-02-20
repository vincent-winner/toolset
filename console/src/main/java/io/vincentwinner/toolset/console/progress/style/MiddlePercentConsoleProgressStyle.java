package io.vincentwinner.toolset.console.progress.style;

public class MiddlePercentConsoleProgressStyle extends DefaultConsoleProgressStyle{

    @Override
    public String progressMiddleContent(Double rate) {
        return percent(rate);
    }

    @Override
    public String progressRightContent(Double rate) {
        return "";
    }

}
