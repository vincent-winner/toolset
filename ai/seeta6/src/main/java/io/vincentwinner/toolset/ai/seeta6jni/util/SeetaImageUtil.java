package io.vincentwinner.toolset.ai.seeta6jni.util;

import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaFaceInfo;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaImageData;
import io.vincentwinner.toolset.ai.seeta6jni.structs.SeetaRect;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ComponentSampleModel;
import java.util.Arrays;

public class SeetaImageUtil {

    /**
     * @param image 图像
     * @param bandOffset 用于推断通道顺序
     * @return 通道顺序是否符合预期
     */
    private static boolean equalBandOffsetWith3Byte(BufferedImage image, int[] bandOffset) {
        if (image.getType() == BufferedImage.TYPE_3BYTE_BGR) {
            if (image.getData().getSampleModel() instanceof ComponentSampleModel) {
                ComponentSampleModel sampleModel = (ComponentSampleModel) image.getData().getSampleModel();
                return Arrays.equals(sampleModel.getBandOffsets(), bandOffset);
            }
        }
        return false;
    }

    /**
     * 判断图像是否为 BGR 格式
     * @param image 图像
     * @return 图像是否为 BGR 格式
     */
    public static boolean isBGR3Byte(BufferedImage image) {
        return equalBandOffsetWith3Byte(image, new int[]{0, 1, 2});
    }

    /**
     * 判断图像是否为 RGB 格式
     * @param image 图像
     * @return 图像是否为 RGB 格式
     */
    public static boolean isRGB3Byte(BufferedImage image){
        return equalBandOffsetWith3Byte(image,new int[]{2, 1, 0});
    }

    /**
     * 对图像解码返回BGR格式矩阵数据
     * @param image 图像
     * @return BGR 图像数据
     */
    public static byte[] getMatrixBGR(BufferedImage image) {
        byte[] matrixBGR;
        if (isBGR3Byte(image)) {
            matrixBGR = (byte[]) image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        } else {
            // ARGB格式图像数据
            int[] rgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            matrixBGR = new byte[image.getWidth() * image.getHeight() * 3];
            // ARGB转BGR格式
            for (int i = 0, j = 0; i < rgb.length; ++i, j += 3) {
                matrixBGR[j] = (byte) (rgb[i] & 0xff);
                matrixBGR[j + 1] = (byte) ((rgb[i] >> 8) & 0xff);
                matrixBGR[j + 2] = (byte) ((rgb[i] >> 16) & 0xff);
            }
        }
        return matrixBGR;
    }

    /**
     * 对图像解码返回RGB格式矩阵数据
     * @param image 图像
     * @return RGB 图像数据
     */
    public static byte[] getMatrixRGB(BufferedImage image) {
        if(null==image)
            throw new NullPointerException();
        byte[] matrixRGB;
        if(isRGB3Byte(image)){
            matrixRGB= (byte[]) image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        }else{
            // 转RGB格式
            BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_3BYTE_BGR);
            new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null).filter(image, rgbImage);
            matrixRGB= (byte[]) rgbImage.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        }
        return matrixRGB;
    }

    /**
     * 将 BGR 图像数据转换为 BufferedImage 对象
     * @param data BGR 图像数据
     * @param width 图像宽
     * @param height 图像高
     * @return BufferedImage 对象
     */
    public static BufferedImage bgrToBufferedImage(byte[] data, int width, int height) {
        int type = BufferedImage.TYPE_3BYTE_BGR;
        // bgr to rgb
        byte b;
        for (int i = 0; i < data.length; i = i + 3) {
            b = data[i];
            data[i] = data[i + 2];
            data[i + 2] = b;
        }
        BufferedImage image = new BufferedImage(width, height, type);
        image.getRaster().setDataElements(0, 0, width, height, data);
        return image;
    }

    /**
     * 将 BufferedImage 转换为 SeetaImageData
     * @param image bufferedImage 对象
     * @return SeetaImageData 对象
     */
    public static SeetaImageData toSeetaImageData(BufferedImage image){
        if(image != null) {
            SeetaImageData imageData = new SeetaImageData();
            imageData
                    .setChannels(3)
                    .setWidth(image.getWidth())
                    .setHeight(image.getHeight())
                    .setData(getMatrixBGR(image));
            return imageData;
        }
        return null;
    }

    /**
     * 获取人脸信息数组中评分最高的人脸位置信息
     * @param faceInfos 人脸信息数组
     * @return 最大人脸位置（评分最高的人脸）
     *         如果人脸信息数组为空则返回 null
     */
    @SuppressWarnings("all")
    public static SeetaFaceInfo getMaxFaceInfo(SeetaFaceInfo[] faceInfos){
        if(faceInfos == null) return null;
        return Arrays.stream(faceInfos).max((f1,f2) ->
                Float.compare(f1.getScore(),f2.getScore())
        ).get();
    }

    /**
     * 获取人脸信息中评分最高的人脸位置
     * @param faceInfos 人脸信息数组
     * @return 最大人脸的位置
     */
    public static SeetaRect getMaxFaceRect(SeetaFaceInfo[] faceInfos){
        return new SeetaRect(getMaxFaceInfo(faceInfos));
    }

}
