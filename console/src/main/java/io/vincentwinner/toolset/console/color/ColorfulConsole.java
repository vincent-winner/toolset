package io.vincentwinner.toolset.console.color;

import org.fusesource.jansi.AnsiConsole;

/**
 * 实现了此接口的类可以利用以下方法决定是否输出颜色
 */
public interface ColorfulConsole {

    /**
     * 使色彩生效
     */
    default void installColor(){
        AnsiConsole.systemInstall();
    }

    /**
     * 使色彩失效并重置到默认状态
     */
    default void uninstallColor(){
        AnsiConsole.systemInstall();
    }

}
