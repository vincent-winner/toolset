package io.vincentwinner.toolset.ai.faceid.seetaface;

import com.seetaface2.SeetaFace2JNI;
import com.seetaface2.model.SeetaImageData;
import io.vincentwinner.toolset.os.Computer;
import io.vincentwinner.toolset.os.OperatingSystem;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FaceDetectModel {

    private final SeetaFace2JNI jni;

    private FaceDetectModel(){
        SeetaProperty property = SeetaProperty.getInstance();
        OperatingSystem system = Computer.getOperatingSystem();
        if(system.isTargetSystem(OperatingSystem.SystemType.WINDOWS)){
            List<String> winLib = Arrays.asList("libgcc_s_sjlj-1,libeay32,libquadmath-0,ssleay32,libgfortran-3,libopenblas,holiday,SeetaFaceDetector200,SeetaPointDetector200,SeetaFaceRecognizer200,SeetaFaceCropper200,SeetaFace2JNI".split(","));
            winLib.forEach(lib -> { System.load(new File(property.getLibraryPath(),lib + ".dll").getAbsolutePath()); });
        }else if (system.isTargetSystem(OperatingSystem.SystemType.Linux)){
            List<String> linuxLib = Arrays.asList("holiday,SeetaFaceDetector200,SeetaPointDetector200,SeetaFaceRecognizer200,SeetaFaceCropper200,SeetaFace2JNI".split(","));
            linuxLib.forEach(lib -> { System.load(new File(property.getLibraryPath(),lib + ".so").getAbsolutePath()); });
        }else {
            throw new RuntimeException("模型不支持此系统");
        }
        jni = new SeetaFace2JNI();
        jni.initModel(property.getModelPath());
    }

    private static final class Instance {
        private static final FaceDetectModel INSTANCE = new FaceDetectModel();
    }

    public static FaceDetectModel getInstance() {
        return Instance.INSTANCE;
    }

    public float compare(SeetaImageData img1, SeetaImageData img2) {
        return jni.compare(img1, img2);
    }
}
