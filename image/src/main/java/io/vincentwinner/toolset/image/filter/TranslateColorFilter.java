package io.vincentwinner.toolset.image.filter;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;

public class TranslateColorFilter {

    public static Mat inverseColor(Mat src){
        Mat dst = new Mat();
        opencv_core.bitwise_not(src,dst);
        src.release();
        return dst;
    }

    public static Mat deleteColor(Mat src){
        Mat dst = new Mat();
        Mat dst$ = new Mat();
        opencv_imgproc.cvtColor(src,dst,opencv_imgproc.COLOR_BGR2GRAY);
        src.release();
        opencv_imgproc.cvtColor(dst,dst$,opencv_imgproc.COLOR_GRAY2BGR);
        dst.release();
        return dst$;
    }

}
