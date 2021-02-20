package io.vincentwinner.toolset.console.progress.style;

import java.text.DecimalFormat;

/**
 * 抽象控制台进度条样式
 */
public abstract class AbstractConsoleProgressStyle {

    private final char PERCENT_CHAR = '%';
    protected final DecimalFormat formatter = new DecimalFormat("00.0%");

    /**
     * 进度条字符长度
     */
    abstract public int barLen();

    /**
     * 进度条进度字符
     * 注意：需返回单个字符，返回值为字符串是为了支持彩色字体
     */
    abstract public String processChar();

    /**
     * 在 IntelliJ IDEA 的控制台中，普通字符将别识别成半角字符，
     * 而在系统自带的控制台中，processChar 有可能会被识别成全角字符
     * 这种设置的不确定性将导致进度条右边界的位置改变，请自行实验并修改使用全角或半角字符
     * 半角空格 \u0020
     * 全角空格 \u3000
     * @return 全角空格或半角空格
     */
    abstract public char space();

    /**
     * 进度条左边界
     * 注意：需返回单个字符，返回值为字符串是为了支持彩色字体
     */
    abstract public String processLeftBorder();

    /**
     * 进度条右边界
     * 注意：需返回单个字符，返回值为字符串是为了支持彩色字体
     */
    abstract public String processRightBorder();

    /**
     * 进度条百分比显示字符串
     * @param rate 百分比（ 0 ~ 1 ）
     * @return 进度条百分比字符串
     */
    public String percent(double rate){
        return formatter.format(rate);
    }

    /**
     * @param rate 进度条百分比（范围 0 ~ 1 可以不使用该参数）
     * @return 进度条左侧内容
     */
    abstract public String progressLeftContent(Double rate);

    /**
     * @param rate 进度条百分比（范围 0 ~ 1 可以不使用该参数）
     * @return 进度条中间内容
     */
    abstract public String progressMiddleContent(Double rate);

    /**
     * @param rate 进度条百分比（范围 0 ~ 1 可以不使用该参数）
     * @return 进度条右侧内容
     */
    abstract public String progressRightContent(Double rate);

}
