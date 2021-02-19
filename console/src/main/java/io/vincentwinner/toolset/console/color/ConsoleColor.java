package io.vincentwinner.toolset.console.color;

import org.fusesource.jansi.Ansi;

/**
 * 可以用于控制台背景色和前景色的颜色
 */
public enum ConsoleColor {

    /**
     * 黑色
     */
    BLACK(Ansi.Color.BLACK),

    /**
     * 红色
     */
    RED(Ansi.Color.RED),

    /**
     * 绿色
     */
    GREEN(Ansi.Color.GREEN),

    /**
     * 黄色
     */
    YELLOW(Ansi.Color.YELLOW),

    /**
     * 蓝色
     */
    BLUE(Ansi.Color.BLUE),

    /**
     * 洋红
     */
    MAGENTA(Ansi.Color.MAGENTA),

    /**
     * 青色
     */
    CYAN(Ansi.Color.CYAN),

    /**
     * 白色
     */
    WHITE(Ansi.Color.WHITE),

    /**
     * 系统默认颜色
     */
    DEFAULT(Ansi.Color.DEFAULT)
    ;

    private final Ansi.Color color;

    ConsoleColor(Ansi.Color color){
        this.color = color;
    }

    public Ansi.Color getColor(){
        return this.color;
    }

}
