package io.vincentwinner.toolset.console.progress.style;

/**
 * 等号进度条样式
 * [==================  ]90.0%
 */
public class EqualsSignConsoleProgressStyle extends DefaultConsoleProgressStyle{

    @Override
    public String processChar() {
        return "=";
    }

}
