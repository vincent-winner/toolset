package io.vincentwinner.toolset.console.test;

import io.vincentwinner.toolset.console.progress.ConsoleProgress;
import io.vincentwinner.toolset.console.progress.style.DefaultConsoleProgressStyle;
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

}
