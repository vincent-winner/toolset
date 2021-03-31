package io.vincentwinner.toolset.console.test;

import io.vincentwinner.toolset.console.banner.ConsoleBanner;
import io.vincentwinner.toolset.console.banner.style.BannerFont;
import io.vincentwinner.toolset.console.progress.ConsoleProgress;
import io.vincentwinner.toolset.console.progress.style.DefaultConsoleProgressStyle;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OtherConsoleTest {

    @Test
    public void _01_testProgress() {
        ConsoleProgress progress = new ConsoleProgress(new DefaultConsoleProgressStyle());
        for (int i = 0; i < 10; i++) {
            try {
                progress.printProgress(i * 0.1);
            }catch (Exception e){ }
        }
    }

    @Test
    public void _02_testBanner(){
        String three_d_ascii = " ________  ________  ________     \n" +
                "|\\   __  \\|\\   __  \\|\\   ____\\    \n" +
                "\\ \\  \\|\\  \\ \\  \\|\\ /\\ \\  \\___|    \n" +
                " \\ \\   __  \\ \\   __  \\ \\  \\       \n" +
                "  \\ \\  \\ \\  \\ \\  \\|\\  \\ \\  \\____  \n" +
                "   \\ \\__\\ \\__\\ \\_______\\ \\_______\\\n" +
                "    \\|__|\\|__|\\|_______|\\|_______|\n" +
                "                                  \n" +
                "                                  \n" +
                "                                  ";
        Assert.assertEquals(three_d_ascii,ConsoleBanner.create("ABC", BannerFont.THREE_D_ASCII));
    }

}
