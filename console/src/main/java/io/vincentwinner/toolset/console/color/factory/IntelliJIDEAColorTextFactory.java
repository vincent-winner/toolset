package io.vincentwinner.toolset.console.color.factory;

import io.vincentwinner.toolset.console.color.ConsoleColor;

/**
 * IntelliJ IDEA 专用彩色字体生成器
 */
public class IntelliJIDEAColorTextFactory extends ColorTextFactory{
    private static final long serialVersionUID = 1739768271165600776L;

    public IntelliJIDEAColorTextFactory() {
        super();
    }

    public IntelliJIDEAColorTextFactory(ConsoleColor foreground, ConsoleColor background, boolean fgBright, boolean bgBright) {
        super(foreground, background, fgBright, bgBright);
    }

    public IntelliJIDEAColorTextFactory(ConsoleColor foreground, ConsoleColor background) {
        super(foreground, background);
    }

    /**
     * @see super#installColor()
     */
    @Override
    public void installColor() {
        super.colorful = true;
    }

    /**
     * @see super#uninstallColor()
     */
    @Override
    public void uninstallColor() {
        super.colorful = false;
    }

}
