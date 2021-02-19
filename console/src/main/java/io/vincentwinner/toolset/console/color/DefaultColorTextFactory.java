package io.vincentwinner.toolset.console.color;

/**
 * @see ColorTextFactory
 */
public class DefaultColorTextFactory extends ColorTextFactory{

    private static final long serialVersionUID = -1950821584230914253L;

    public DefaultColorTextFactory() {
        super();
    }

    public DefaultColorTextFactory(ConsoleColor foreground, ConsoleColor background, boolean fgBright, boolean bgBright){
        super(foreground,background,fgBright,bgBright);
    }

    public DefaultColorTextFactory(ConsoleColor foreground, ConsoleColor background){
        super(foreground,background);
    }

}
