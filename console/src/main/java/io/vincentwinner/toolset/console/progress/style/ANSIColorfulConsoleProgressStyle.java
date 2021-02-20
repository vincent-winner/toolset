package io.vincentwinner.toolset.console.progress.style;

import io.vincentwinner.toolset.console.color.ColorTextFactory;
import io.vincentwinner.toolset.console.color.ConsoleColor;
import io.vincentwinner.toolset.console.color.DefaultColorTextFactory;

/**
 * 普通彩色进度条样式
 * [██████████████████  ]90.0%
 * 进度条为彩色
 */
public class ANSIColorfulConsoleProgressStyle extends DefaultConsoleProgressStyle{

    protected ColorTextFactory factory;

    public ANSIColorfulConsoleProgressStyle(){
        factory = new DefaultColorTextFactory();
    }

    @Override
    public String processChar() {
        ConsoleColor fgColor = factory.getForegroundColor();
        factory.setForegroundColor(ConsoleColor.GREEN);
        String colorText = factory.colorText(super.processChar()).toString();
        factory.setForegroundColor(fgColor);
        return colorText;
    }

    @Override
    public String processLeftBorder() {
        ConsoleColor fgColor = factory.getForegroundColor();
        factory.setForegroundColor(ConsoleColor.YELLOW);
        String colorText = factory.colorText(super.processLeftBorder()).toString();
        factory.setForegroundColor(fgColor);
        return colorText;
    }

    @Override
    public String processRightBorder() {
        ConsoleColor fgColor = factory.getForegroundColor();
        factory.setForegroundColor(ConsoleColor.YELLOW);
        String colorText = factory.colorText(super.processRightBorder()).toString();
        factory.setForegroundColor(fgColor);
        return colorText;
    }

    @Override
    public String progressRightContent(Double rate) {
        ConsoleColor fgColor = factory.getForegroundColor();
        factory.setForegroundColor(ConsoleColor.YELLOW);
        String colorText = factory.colorText(super.progressRightContent(rate)).toString();
        factory.setForegroundColor(fgColor);
        return colorText;
    }

}
