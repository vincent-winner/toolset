package io.vincentwinner.toolset.console.color.factory;

import io.vincentwinner.toolset.console.color.ConsoleColor;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.Serializable;

public abstract class ColorTextFactory extends Ansi implements ColorfulConsole, Serializable {

    private static final long serialVersionUID = -3340165443249468186L;

    private ConsoleColor foregroundColor;
    private ConsoleColor backgroundColor;
    private boolean fgBright;
    private boolean bgBright;
    boolean colorful = false;

    public ColorTextFactory(){
        this.foregroundColor = ConsoleColor.DEFAULT;
        this.backgroundColor = ConsoleColor.DEFAULT;
        fgBright = false;
        bgBright = false;
        installColor();
    }

    /**
     * @param foreground 文字颜色
     * @param background 背景色
     * @param fgBright 前景色是否加亮
     * @param bgBright 背景色时候加亮
     */
    public ColorTextFactory(ConsoleColor foreground, ConsoleColor background, boolean fgBright, boolean bgBright){
        this.foregroundColor = foreground;
        this.backgroundColor = background;
        this.fgBright = fgBright;
        this.bgBright = bgBright;
        installColor();
    }

    /**
     * 默认色彩不加亮
     * @param foreground 文字颜色
     * @param background 背景色
     */
    public ColorTextFactory(ConsoleColor foreground, ConsoleColor background){
        this(foreground,background,false,false);
    }

    /**
     * 获取前景色（文字颜色）
     */
    public ConsoleColor getForegroundColor() {
        return foregroundColor;
    }

    /**
     * 获取背景色
     */
    public ConsoleColor getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * 设置文字颜色
     */
    public void setForegroundColor(ConsoleColor foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    /**
     * 设置背景色
     */
    public void setBackgroundColor(ConsoleColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 前景色是否为亮色
     */
    public boolean isFgBright() {
        return fgBright;
    }

    /**
     * 设置前景色是否为亮色
     */
    public void setFgBright(boolean fgBright) {
        this.fgBright = fgBright;
    }

    /**
     * 背景色是否为亮色
     */
    public boolean isBgBright() {
        return bgBright;
    }

    /**
     * 设置背景色是否为亮色
     */
    public void setBgBright(boolean bgBright) {
        this.bgBright = bgBright;
    }

    /**
     * 获取彩色字体内容
     * 在使用installColor()方法之后，直接用 System.out.print*(..) 输出文字即可变色
     * 下边的例子将前景色设置为青色，背景色为系统默认颜色，并输出了彩色的 123 和 abc
     * {@code <pre>
     *     public void test(){
     *         ColorTextFactory factory = new DefaultColorTextFactory(DefaultConsoleColor.CYAN,DefaultConsoleColor.DEFAULT);
     *         System.out.println(factory.colorText(123));// 彩色的123
     *         System.out.println(factory.colorText("abc"));// 彩色的abc
     *         factory.uninstallColor();
     *         System.out.println("abc");//没有颜色的 abc
     *         factory.installColor();
     *         System.out.println("abc");// 彩色的 abc
     *     }
     * </pre>}
     * @param text 需要上色的文字
     * @return 可以直接被输出的内容
     *         在 installColor()方法执行之后返回彩色内容
     *         在 uninstallColor() 方法执行之后返回默认颜色内容
     */
    public Object colorText(Object text){
        if(!colorful) return text;
        Ansi ansi = ansi();
        ansi = fgBright ? ansi.fgBright(foregroundColor.getColor()) : ansi.fg(foregroundColor.getColor());
        ansi = bgBright ? ansi.bgBright(backgroundColor.getColor()) : ansi.bg(backgroundColor.getColor());
        return ansi.a(text).reset();
    }

    /**
     * 使色彩生效
     */
    @Override
    public void installColor() {
        AnsiConsole.systemInstall();
        colorful = true;
    }

    /**
     * 使色彩失效
     */
    @Override
    public void uninstallColor() {
        AnsiConsole.systemUninstall();
        colorful = false;
    }

}
