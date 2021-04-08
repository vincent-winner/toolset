package io.vincentwinner.toolset.image.filter.stylize;

import org.bytedeco.opencv.global.opencv_xphoto;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * 油画效果
 * 比较吃计算性能
 */
public class OilPainting {

    /**
     * 油画效果滤镜
     * @param src 源图像矩阵
     * @param size 油画处理半径
     * @param dynRatio dyn比率
     * @return 油画效果矩阵
     */
    public static Mat oilPainting(Mat src,int size,int dynRatio){
        Mat dst = new Mat();
        opencv_xphoto.oilPainting(src,dst,size,dynRatio);
        src.release();
        return dst;
    }

    /**
     * 油画效果滤镜
     * @param src 源图像矩阵
     * @param size 油画处理半径
     * @return 油画效果矩阵
     */
    public static Mat oilPainting(Mat src,int size){
        return oilPainting(src,size,1);
    }

    /**
     * 油画效果滤镜
     * @param src 源图像矩阵
     * @return 油画效果矩阵
     */
    public static Mat oilPainting(Mat src){
        return oilPainting(src,3);
    }

}
