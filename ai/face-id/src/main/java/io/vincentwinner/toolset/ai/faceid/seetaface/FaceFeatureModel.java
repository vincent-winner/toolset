package io.vincentwinner.toolset.ai.faceid.seetaface;

import com.seetaface2.SeetaFace2JNI;
import com.seetaface2.model.SeetaImageData;
import com.seetaface2.model.SeetaPointF;
import com.seetaface2.model.SeetaRect;
import io.vincentwinner.toolset.os.Computer;
import io.vincentwinner.toolset.os.OperatingSystem;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 人脸特征模型
 */
public final class FaceFeatureModel {

    private final SeetaFace2JNI jni;

    private FaceFeatureModel(){
        SeetaProperty property = SeetaProperty.getInstance();
        OperatingSystem system = Computer.getOperatingSystem();
        if(system.isTargetSystem(OperatingSystem.SystemType.WINDOWS)){
            List<String> winLib = Arrays.asList("libgcc_s_sjlj-1,libeay32,libquadmath-0,ssleay32,libgfortran-3,libopenblas,holiday,SeetaFaceDetector200,SeetaPointDetector200,SeetaFaceRecognizer200,SeetaFaceCropper200,SeetaFace2JNI".split(","));
            winLib.forEach(lib -> { System.load(new File(property.getLibraryPath(),lib + ".dll").getAbsolutePath()); });
        }else if (system.isTargetSystem(OperatingSystem.SystemType.Linux)){
            List<String> linuxLib = Arrays.asList("libholiday,libSeetaFaceDetector200,libSeetaPointDetector200,libSeetaFaceRecognizer200,libSeetaFaceCropper200,libSeetaFace2JNI".split(","));
            linuxLib.forEach(lib -> { System.load(new File(property.getLibraryPath(),lib + ".so").getAbsolutePath()); });
        }else {
            throw new RuntimeException("模型不支持此系统");
        }
        jni = new SeetaFace2JNI();
        jni.initModel(property.getModelPath());
    }

    private static final class Instance {
        private static final FaceFeatureModel INSTANCE = new FaceFeatureModel();
    }

    protected static FaceFeatureModel getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * 比较两个图片中的人脸相似率
     * @param img1 图片1
     * @param img2 图片2
     * @return 人脸相似率
     */
    protected float compare(SeetaImageData img1, SeetaImageData img2) {
        return jni.compare(img1, img2);
    }

    /**
     * 检测图片中的人脸位置
     * @param img 被检测的图片
     * @return 人脸位置
     */
    protected SeetaRect[] detect(SeetaImageData img){
        return jni.detect(img);
    }

    /**
     * 检测人脸五个关键点
     * 双眼 （2）
     * 鼻子 （1）
     * 嘴角 （2）
     * 下边的图是人脸
     * ╭------------╮   ╭------------╮
     * │   ○    ○  │   │   1    2   ┃
     * │      |     │   │     3      ┃
     * │   ▁▁▁▁▁▁   │   │   4    5   ┃
     * ╰------------╯   ╰------------╯
     * @param img 图片
     * @return 关键点 x5
     */
    protected SeetaPointF[] detectPoints(SeetaImageData img){
        return jni.detect(img,detect(img));
    }

}
