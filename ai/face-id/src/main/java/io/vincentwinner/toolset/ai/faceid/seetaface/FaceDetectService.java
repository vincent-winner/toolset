package io.vincentwinner.toolset.ai.faceid.seetaface;

import com.seetaface2.model.SeetaImageData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FaceDetectService {

    private ExecutorService executorService;
    private static final FaceDetectModel model = FaceDetectModel.getInstance();
    private static final SeetaProperty property = SeetaProperty.getInstance();;

    private FaceDetectService(){
        executorService = Executors.newFixedThreadPool(property.getThreadMax());
    }

    private static final class Instance {
        private static final FaceDetectService instance = new FaceDetectService();
    }

    private static final class FaceDetectTask implements Callable<FaceDetectResult>{

        private final InputStream pic1;
        private final InputStream pic2;

        FaceDetectTask(InputStream pic1, InputStream pic2){
            this.pic1 = pic1;
            this.pic2 = pic2;
        }

        @Override
        public FaceDetectResult call() throws Exception {
            BufferedImage pic1Buf = ImageIO.read(pic1);
            BufferedImage pic2Buf = ImageIO.read(pic2);
            SeetaImageData pic1 = new SeetaImageData(pic1Buf.getWidth(),pic1Buf.getHeight());
            pic1.data = getMatrixBGR(pic1Buf);
            SeetaImageData pic2 = new SeetaImageData(pic2Buf.getWidth(),pic2Buf.getHeight());
            pic2.data = getMatrixBGR(pic2Buf);
            float rate = compare(pic1,pic2);
            return new FaceDetectResult(rate / property.getFactor().floatValue(),rate >= property.getSimilarRate());
        }

    }

    public static FaceDetectService getInstance() {
        return Instance.instance;
    }

    public Future<FaceDetectResult> submit(InputStream pic1,InputStream pic2){
        return executorService.submit(new FaceDetectTask(pic1,pic2));
    }

    public void shutdown(){
        executorService.shutdown();
    }

    private static strictfp synchronized float compare(SeetaImageData pic1, SeetaImageData pic2){
        return model.compare(pic1,pic2);
    }

    @Override
    public void finalize() throws Throwable {
        executorService.shutdown();
        super.finalize();
    }

    private static byte[] getMatrixBGR(BufferedImage image) {
        byte[] matrixBGR;
        if (isBGR3Byte(image)) {
            matrixBGR = (byte[]) image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), null);
        } else {
            // ARGB格式图像数据
            int[] intrgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
            matrixBGR = new byte[image.getWidth() * image.getHeight() * 3];
            // ARGB转BGR格式
            for (int i = 0, j = 0; i < intrgb.length; ++i, j += 3) {
                matrixBGR[j] = (byte) (intrgb[i] & 0xff);
                matrixBGR[j + 1] = (byte) ((intrgb[i] >> 8) & 0xff);
                matrixBGR[j + 2] = (byte) ((intrgb[i] >> 16) & 0xff);
            }
        }
        return matrixBGR;
    }

    private static boolean isBGR3Byte(BufferedImage image) {
        return equalBandOffsetWith3Byte(image, new int[]{0, 1, 2});
    }

    private static boolean equalBandOffsetWith3Byte(BufferedImage image, int[] bandOffset) {
        if (image.getType() == BufferedImage.TYPE_3BYTE_BGR) {
            if (image.getData().getSampleModel() instanceof ComponentSampleModel) {
                ComponentSampleModel sampleModel = (ComponentSampleModel) image.getData().getSampleModel();
                return Arrays.equals(sampleModel.getBandOffsets(), bandOffset);
            }
        }
        return false;
    }

}
