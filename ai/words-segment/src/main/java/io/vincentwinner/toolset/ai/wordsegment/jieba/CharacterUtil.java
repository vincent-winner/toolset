package io.vincentwinner.toolset.ai.wordsegment.jieba;

import java.util.regex.Pattern;

public class CharacterUtil {

    protected static Pattern reSkip = Pattern.compile("(\\d+\\.\\d+|[a-zA-Z0-9]+)");
    private static final char[] connectors = new char[] { '+', '#', '&', '.', '_', '-' };

    protected static boolean isChineseLetter(char ch) {
        return ch >= 0x4E00 && ch <= 0x9FA5;
    }

    protected static boolean isEnglishLetter(char ch) {
        return (ch >= 0x0041 && ch <= 0x005A) || (ch >= 0x0061 && ch <= 0x007A);
    }

    protected static boolean isDigit(char ch) {
        return ch >= 0x0030 && ch <= 0x0039;
    }

    protected static boolean isConnector(char ch) {
        for (char connector : connectors)
            if (ch == connector)
                return true;
        return false;
    }

    protected static boolean ccFind(char ch) {
        if (isChineseLetter(ch))
            return true;
        if (isEnglishLetter(ch))
            return true;
        if (isDigit(ch))
            return true;
        return isConnector(ch);
    }

    protected static char regularize(char input) {
        if (input == 12288) {
            return 32;
        }
        else if (input > 65280 && input < 65375) {
            return (char) (input - 65248);
        }
        else if (input >= 'A' && input <= 'Z') {
            return (input += 32);
        }
        return input;
    }

}
