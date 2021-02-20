package io.vincentwinner.toolset.console.progress;

import io.vincentwinner.toolset.console.progress.style.AbstractConsoleProgressStyle;
import io.vincentwinner.toolset.console.progress.style.DefaultConsoleProgressStyle;

/**
 * 此类用于向控制台输出进度条
 */
public class ConsoleProgress {

    private final AbstractConsoleProgressStyle style;

    /**
     * @param progressStyle 进度条样式
     */
    public ConsoleProgress(AbstractConsoleProgressStyle progressStyle){
        this.style = progressStyle;
    }

    /**
     * 无参构造采用默认进度条样式
     */
    public ConsoleProgress(){
        this. style = new DefaultConsoleProgressStyle();
    }

    /**
     * 按指定进度输出进度条
     * @param rate 进度条进度（0 ~ 1）
     */
    public void printProgress(double rate){
        System.out.print(getProgress(rate));
    }

    /**
     * 获取进度条字符串，不换行输出此字符串时，新的进度条会将旧进度条覆盖
     *
     * 注意：输出进度条字符串之后，不要换行
     *      所以应使用 System.out.print() 输出进度条内容
     *      输出进度条后换行将导致一行输出一个进度条
     *
     * @param rate 进度条进度（0 ~ 1）
     * @return 进度条字符串
     */
    public String getProgress(double rate){
        String left = style.progressLeftContent(rate);
        String middle = style.progressMiddleContent(rate);
        String right = style.progressRightContent(rate);
        StringBuffer sb = new StringBuffer(left.length() + middle.length() + right.length() + style.barLen() + 2);
        if(!"".equals(left)){ sb.append(left); }
        sb.append(style.processLeftBorder());
        for(int i = 0 ; i < style.barLen() ; i++){
            if(i < progressEndIndex(rate)){
                sb.append(style.processChar());
            }else {
                sb.append(style.space());
            }
        }
        sb.append(style.processRightBorder()).append(right).append('\r');
        return replaceMiddleContent(sb,rate).toString();
    }

    private int progressEndIndex(double rate){
        return (int)(rate * style.barLen());
    }

    private int progressMiddleBeginIndex(double rate){
        int middleContentLength = style.progressMiddleContent(rate).length();
        int progressMaxLength = style.barLen();
        return (progressMaxLength >> 1) - (middleContentLength >> 1);
    }

    private StringBuffer replaceMiddleContent(StringBuffer sb, double rate){
        int index = style.progressLeftContent(rate).length() + progressMiddleBeginIndex(rate) + 1;
        String middleContent = style.progressMiddleContent(rate);
        sb.replace(index,index + middleContent.length(),middleContent);
        return sb;
    }

}
