## Console 控制台模块
```
特别注意！！！

IntelliJ IDEA 的控制台与系统控制台样式有差别，对颜色和全角半角字符的处理方式有所不同
在打印进度条的方法中，应当考虑使用情况，重写进度条样式的 space() 方法
IDEA中 space() 的返回值为 \u0020 时，显示正常
系统控制台中 space() 的返回值为 \u3000 时，显示正常
```

---
#### 目录
> #### [1.在控制台中打印表格](#table)
> #### [2.在控制台中打印进度条](#progress)
> #### [3.自定义进度条样式](#custom_progress)
> #### [4.自定义控制台文字颜色](#color)
---

#### <span id="table">1.在控制台中打印表格</span>
```java
import io.vincentwinner.toolset.console.table.ConsoleTable;
/**
 +－－－－+－－－－+－－－－+
 |　ｉｄ　|　姓名　|　年龄　|
 +－－－－+－－－－+－－－－+
 |　１　　|　ｚｓ　|　１４　|
 |　２　　|　ｌｓ　|　１５　|
 |　３　　|　ｗｗ　|　１６　|
 +－－－－+－－－－+－－－－+
 */
public class Test{
    public static void main(String[] args) {
        ConsoleTable consoleTable = new ConsoleTable();
        consoleTable.addHeader("id","姓名","年龄");
        consoleTable.addBody("1","zs","14");
        consoleTable.addBody("2","ls","15");
        consoleTable.addBody("3","ww","16");

        /**
         * 以下两种输出方式皆可
         * 调用 ConsoleTable.toString() 方法可以以字符串形式得到表格
         */
        consoleTable.print();
        System.out.println(consoleTable);
    }
}
```

#### <span id="progress">2.在控制台中打印进度条</span>

```java
import io.vincentwinner.toolset.console.progress.ConsoleProgress;
import io.vincentwinner.toolset.console.progress.style.DefaultConsoleProgressStyle;

import java.util.concurrent.TimeUnit;

/**
 * DefaultConsoleProgressStyle
 * [██████████████████　　]90.0%
 *
 * EqualsSignConsoleProgressStyle
 * [==================　　]90.0%
 *
 * MiddlePercentConsoleProgressStyle
 * [████████90.0%█████　　]
 *
 * HashSignConsoleProgressStyle
 * [##################　　]90.0%
 * 
 */
public class Test {
    public static void main(String[] args) {

        /**
         * 构造函数为空时默认使用 DefaultConsoleProgressStyle 样式
         */
        ConsoleProgress progress = new ConsoleProgress(new DefaultConsoleProgressStyle());
        for (int i = 0; i < 10; i++) {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (Exception e) { }
            progress.printProgress(0.1 * i);
        }
    }
}
```

#### <span id="custom_progress">3.自定义进度条样式</span>

```java
import io.vincentwinner.toolset.console.progress.style.DefaultConsoleProgressStyle;

/**
 * 在 IntelliJ IDEA 和 系统控制台 中输出进度条的格式不同
 * 如果样式很奇怪，请根据本文开头和 AbstractConsoleProgressStyle 类中的注释重写相关方法
 */
public class Test {

    /**
     * 自定义样式需要继承 AbstractConsoleProgressStyle 或其子类
     * 之后重写相关的方法，方法作用请查看源码注释
     *
     * [████████████████████████████████████　　　　] 已经下载了90.0%的内容
     */
    private static class CustomConsoleProgressStyle extends DefaultConsoleProgressStyle {
        // 将进度条长度更改为 40 （默认20）
        @Override
        public int barLen() {
            return 40;
        }
        // 如果在 IntelliJ IDEA 中输出进度条，需要重写此方法的返回值为 \u0020
        @Override
        public char space() {
            return '\u0020';
        }
        // 更改进度条右侧文字
        @Override
        public String progressRightContent(Double rate) {
            return " 已经下载了" + percent(rate) + "的内容";
        }
    }

    public static void main(String[] args) {
        ConsoleProgress progress = new ConsoleProgress(new CustomConsoleProgressStyle());
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {
            }
            progress.printProgress(0.1 * i);
        }
    }
}
```

#### <span id="color">4.自定义控制台文字颜色</span>

```java
import io.vincentwinner.toolset.console.color.factory.ColorTextFactory;
import io.vincentwinner.toolset.console.color.ConsoleColor;
import io.vincentwinner.toolset.console.color.factory.IntelliJIDEAColorTextFactory;

public class Test {
    public static void main(String[] args) {
        /**
         * 如果不在 IntelliJ IDEA 中测试，则应实例化 DefaultColorTextFactory 类的对象（注释掉的代码）
         * 由于 IDEA 的控制台样式特殊，需要为其单独创建样式
         */
        ColorTextFactory printer = new IntelliJIDEAColorTextFactory();
        //ColorTextFactory printer = new DefaultColorTextFactory();

        printer.setForegroundColor(ConsoleColor.YELLOW);

        //输出黄色的 123
        System.out.println(printer.colorText("123"));

        //将背景色设置为蓝色，由于之前已经将字体设置为黄色，打印字体的颜色是黄色字蓝色背景
        printer.setBackgroundColor(ConsoleColor.BLUE);
        //输出黄色字体蓝色背景的456
        System.out.println(printer.colorText(456));

        //卸载颜色
        printer.uninstallColor();
        //此时输出将是系统控制台最开始的默认颜色（未更改过是黑底白字）
        System.out.println(printer.colorText(789));

        //继续输出彩色字体，由于未更改过颜色，输出的 000 将依然是黄字蓝底
        printer.installColor();
        System.out.println(printer.colorText("000"));
    }
}
```