package io.vincentwinner.toolset.console.test;

import io.vincentwinner.toolset.console.color.factory.ColorTextFactory;
import io.vincentwinner.toolset.console.color.ConsoleColor;
import io.vincentwinner.toolset.console.color.factory.DefaultColorTextFactory;
import io.vincentwinner.toolset.console.color.factory.IntelliJIDEAColorTextFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ColorConsoleTest {

    /**
     * 此方法生成的彩色字体在 IntelliJ IDEA 控制台中有效
     * 在 Windows 和 Linux 的控制台中，色彩信息会变为乱码
     */
    @Test
    public void _01_testIntelliJIDEAConsoleColor(){
        ColorTextFactory factory = new IntelliJIDEAColorTextFactory(ConsoleColor.CYAN,ConsoleColor.DEFAULT);
        System.out.println(factory.colorText(123));
        factory.setForegroundColor(ConsoleColor.YELLOW);
        System.out.println(factory.colorText(456));
        factory.setBackgroundColor(ConsoleColor.WHITE);
        factory.setForegroundColor(ConsoleColor.BLACK);
        System.out.println(factory.colorText(789));
    }

    /**
     * 此方法生成的彩色字体在 Windows 和 Linux 的控制台中有效
     * 在 IntelliJ IDEA 的控制台中，此方法生成的彩色文字没有效果
     */
    @Test
    public void _02_testANSIConsoleColor(){
        ColorTextFactory factory = new DefaultColorTextFactory(ConsoleColor.CYAN,ConsoleColor.DEFAULT);
        System.out.println(factory.colorText(123));
        factory.setForegroundColor(ConsoleColor.YELLOW);
        System.out.println(factory.colorText(456));
        factory.setBackgroundColor(ConsoleColor.WHITE);
        factory.setForegroundColor(ConsoleColor.BLACK);
        System.out.println(factory.colorText(789));
    }

}
