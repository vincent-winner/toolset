package io.vincentwinner.toolset.image.exception;

/**
 * 卷积函数参数异常
 */
public class ConvolutionIllegalArgumentException extends IllegalArgumentException{

    private static final long serialVersionUID = 8048817893679729355L;

    /**
     * @param argName 卷积参数名
     * @param kernelWidth 当前卷积核宽
     * @param kernelHeight 当前卷积核高
     */
    public ConvolutionIllegalArgumentException(String argName,int kernelWidth,int kernelHeight) {
        super(argName + "超出规定值范围或小于等于0，图片处理请求被放弃，当前值为：{宽: " + kernelWidth
                + ",高: " + kernelHeight + "}");
    }

    /**
     * @param argName 卷积参数名
     * @param kernelSize 卷积核大小
     */
    public ConvolutionIllegalArgumentException(String argName,int kernelSize){
        super(argName + "超出规定值范围或小于等于0，图片处理请求被放弃，当前值为：" + kernelSize);
    }

}
