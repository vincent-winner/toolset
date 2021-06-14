package io.vincentwinner.toolset.ai.seeta6jni.util;

import java.io.*;

public class FaceFeatureSerializer {

    /**
     * 将人脸特征序列化进 ByteArrayOutputStream
     * @param features 人脸特征
     * @return 包含人脸特征序列化数据的 ByteArrayOutputStream
     */
    public static ByteArrayOutputStream serializeFaceFeature(float[] features){
        if(features == null || features.length == 0) return null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeInt(features.length);
            for (int i = 0; i < features.length; i++) {
                dataOutputStream.writeFloat(features[i]);
            }
        }catch (Exception e){
            throw new RuntimeException("序列化人脸特征失败",e);
        }
        return outputStream;
    }

    /**
     * 将人脸特征序列化为 byte 数组
     * @param features 人脸特征
     * @return 人脸特征序列化的数据
     */
    public static byte[] serializeFaceFeatureBytes(float[] features){
        ByteArrayOutputStream bos = serializeFaceFeature(features);
        return bos.toByteArray();
    }

    /**
     * 反序列化人脸特征信息
     * @param inputStream 包含人脸序列化信息的输入流
     * @return 人脸特征数组
     */
    public static float[] deserializeFaceFeature(InputStream inputStream){
        float[] features = null;
        try(DataInputStream dataInputStream = new DataInputStream(inputStream)){
            int featureSize = dataInputStream.readInt();
            features = new float[featureSize];
            for (int i = 0; i < featureSize; i++) {
                features[i] = dataInputStream.readFloat();
            }
        }catch (Exception e){
            throw new RuntimeException("反序列化人脸特征失败",e);
        }
        return features;
    }

    /**
     * 反序列化人脸特征信息
     * @param bytes 人脸特征序列化数据
     * @return 人脸特征数组
     */
    public static float[] deserializeFeatureBytes(byte[] bytes){
        return deserializeFaceFeature(new ByteArrayInputStream(bytes));
    }

}
