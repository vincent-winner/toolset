package io.vincentwinner.toolset.image;

/**
 * 位深度
 */
public enum DDepth {

    /**
     * 跟随原图
     */
    ORIGINAL(-1),

    /**
     * 8位无符号整数（0..255）
     */
    CV_8U(0),

    /**
     * 8位有符号整数（-128..127）
     */
    CV_8S(1),

    /**
     * 16位无符号整数（0..65535）
     */
    CV_16U(2),

    /**
     * 16位有符号整数（-32768..32767）
     */
    CV_16S(3),

    /**
     * 32位有符号整数（-2147483648..2147483647）
     */
    CV_32S(4),

    /**
     * 32位浮点数（-FLT_MAX..FLT_MAX，INF，NAN）
     */
    CV_32F(5),

    /**
     * 64位浮点数（-DBL_MAX..DBL_MAX，INF，NAN）
     */
    CV_64F(6),

    /**
     * 16位浮点数
     */
    CV_16F(7)
    ;

    private final int ddepth;

    DDepth(int ddepth){
        this.ddepth = ddepth;
    }

    public int value(){
        return this.ddepth;
    }

}
