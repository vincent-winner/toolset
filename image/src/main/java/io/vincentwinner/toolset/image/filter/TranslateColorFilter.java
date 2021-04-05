package io.vincentwinner.toolset.image.filter;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

public class TranslateColorFilter {

    public static Mat inverseColor(Mat src){
        Mat dst = new Mat();
        opencv_core.bitwise_not(src,dst);
        src.release();
        return dst;
    }

}
