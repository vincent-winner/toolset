package io.vincentwinner.toolset.console.progress.style;

import io.vincentwinner.toolset.console.color.IntelliJIDEAColorTextFactory;

/**
 * IntelliJ IDEA 彩色进度条样式
 * [██████████████████  ]90.0%
 * 在IntelliJ IDEA中进度条为彩色，在普通控制台中将输出乱码
 */
public class IntelliJIDEAColorfulConsoleProgressStyle extends ANSIColorfulConsoleProgressStyle{

    public IntelliJIDEAColorfulConsoleProgressStyle(){
        super();
        factory.uninstallColor();
        this.factory = new IntelliJIDEAColorTextFactory();
    }

    @Override
    public char space() {
        return '\u0020';
    }

}
