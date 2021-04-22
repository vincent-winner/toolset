package io.vincentwinner.toolset.console.color;

import org.fusesource.jansi.Ansi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 可以用于控制台背景色和前景色的颜色
 */
public enum ConsoleColor {

    /**
     * 黑色
     */
    BLACK("BLACK",Ansi.Color.BLACK),

    /**
     * 红色
     */
    RED("RED",Ansi.Color.RED),

    /**
     * 绿色
     */
    GREEN("GREEN",Ansi.Color.GREEN),

    /**
     * 黄色
     */
    YELLOW("YELLOW",Ansi.Color.YELLOW),

    /**
     * 蓝色
     */
    BLUE("BLUE",Ansi.Color.BLUE),

    /**
     * 洋红
     */
    MAGENTA("MAGENTA",Ansi.Color.MAGENTA),

    /**
     * 青色
     */
    CYAN("CYAN",Ansi.Color.CYAN),

    /**
     * 白色
     */
    WHITE("WHITE",Ansi.Color.WHITE),

    /**
     * 系统默认颜色
     */
    DEFAULT("DEFAULT",Ansi.Color.DEFAULT)
    ;

    private final Ansi.Color color;
    private final String colorName;

    private static final Map<String,ConsoleColor> MAP = new HashMap<>(9);

    ConsoleColor(String colorName, Ansi.Color color){
        this.colorName = colorName;
        this.color = color;
    }

    public Ansi.Color getColor(){
        return this.color;
    }

    public ConsoleColor get(String colorName){
        return MAP.get(colorName);
    }

    static {
        Arrays.asList(ConsoleColor.values()).forEach(consoleColor -> {
            MAP.put(consoleColor.colorName,consoleColor);
        });
    }

}
