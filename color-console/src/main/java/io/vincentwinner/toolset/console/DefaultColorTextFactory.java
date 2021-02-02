package io.vincentwinner.toolset.console;

/**
 * @see ColorTextFactory
 */
public class DefaultColorTextFactory extends ColorTextFactory{

    private static final long serialVersionUID = -1950821584230914253L;

    public DefaultColorTextFactory(DefaultConsoleColor foreground,DefaultConsoleColor background,boolean fgBright,boolean bgBright){
        super(foreground,background,fgBright,bgBright);
    }

    public DefaultColorTextFactory(DefaultConsoleColor foreground,DefaultConsoleColor background){
        super(foreground,background);
    }

}
