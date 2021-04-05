package io.vincentwinner.toolset.image.util;

import io.vincentwinner.toolset.image.exception.ConvolutionIllegalArgumentException;

import java.util.function.Predicate;

/**
 * 卷积核参数检查
 * 本类方法只对相应的图像处理方法起作用
 * 直接使用卷积函数可以越过该类中方法检查
 */
public class ConvolutionKernelCheckUtil {

    /**
     * 检查传入的参数是否有小于等于0的
     * @param kernelArgs 参数
     * @return 参数是否含有小于等于0的
     */
    private static boolean greaterThanZero(int... kernelArgs){
        return testKernel(i -> i > 0,kernelArgs);
    }

    private static boolean greaterThanZeroAndIsOdd(int... kernelArgs){
        return testKernel(i -> i > 0 && ((i & 1) == 1),kernelArgs);
    }

    private static boolean greaterThanZeroAndIsOddWithMaxRange(final int maxRange,int... kernelArgs){
        return testKernel(i -> i > 0 && ((i & 1) == 1) && i <= maxRange,kernelArgs);
    }

    private static boolean greaterThanZeroWithMaxRange(final int maxRange,int... kernelArgs){
        return testKernel(i -> i > 0 && i <= maxRange,kernelArgs);
    }

    private static boolean testKernel(Predicate<Integer> predicate,int... kernelArgs){
        for(int kernelArg : kernelArgs){
            if(!predicate.test(kernelArg)) return false;
        }
        return true;
    }

    /**
     * 检查高斯模糊卷积核是否符合规范
     * 卷积核宽高应全部为大于 0 的奇数
     * 卷积核宽高应小于79,过大的值对图像变化几乎无影响，但对性能的影响成倍增长
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @throws ConvolutionIllegalArgumentException 当核心参数不符合规范时抛出此异常
     */
    public static void checkGaussianKernel(int kernelWidth,int kernelHeight){
        if( !greaterThanZeroAndIsOddWithMaxRange(79,kernelWidth,kernelHeight) ){
            throw new ConvolutionIllegalArgumentException(
                    "高斯卷积核",
                    kernelWidth,
                    kernelHeight
            );
        }
    }

    /**
     * 检查均值卷积核参数
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @throws ConvolutionIllegalArgumentException 当核心参数不符合规范时抛出此异常
     */
    public static void checkAverageKernel(int kernelWidth,int kernelHeight){
        if( !greaterThanZeroWithMaxRange(79,kernelWidth,kernelHeight) ){
            throw new ConvolutionIllegalArgumentException(
                    "均值卷积核",
                    kernelWidth,
                    kernelHeight
            );
        }
    }

    /**
     * 检查中值卷积核参数
     * @param kernelSize 中值卷积核大小
     * @throws ConvolutionIllegalArgumentException 当核心参数不符合规范时抛出此异常
     */
    public static void checkMedianKernel(int kernelSize){
        if( !greaterThanZeroAndIsOddWithMaxRange(79,kernelSize) ){
            throw new ConvolutionIllegalArgumentException(
                    "中值卷积核",
                    kernelSize
            );
        }
    }

    /**
     * 检查方框模糊卷积核参数
     * @param kernelWidth 卷积核宽
     * @param kernelHeight 卷积核高
     * @throws ConvolutionIllegalArgumentException 当核心参数不符合规范时抛出此异常
     */
    public static void checkBoxKernel(int kernelWidth,int kernelHeight){
        if( !greaterThanZeroWithMaxRange(79,kernelWidth,kernelHeight) ){
            throw new ConvolutionIllegalArgumentException(
                    "方框卷积核",
                    kernelWidth,
                    kernelHeight
            );
        }
    }

}