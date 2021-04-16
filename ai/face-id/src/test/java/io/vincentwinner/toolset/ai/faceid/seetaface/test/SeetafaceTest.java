package io.vincentwinner.toolset.ai.faceid.seetaface.test;

import com.seetaface2.model.SeetaPointF;
import com.seetaface2.model.SeetaRect;
import io.vincentwinner.toolset.ai.faceid.seetaface.FaceCompareResult;
import io.vincentwinner.toolset.ai.faceid.seetaface.FaceHandleService;
import io.vincentwinner.toolset.ai.faceid.seetaface.awt.ImageFrame;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.InputStream;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeetafaceTest {

    private final FaceHandleService service = FaceHandleService.getInstance();

    /**
     * 人脸检测
     */
    @Test
    public void _01_testFaceDetect() throws Exception{
        InputStream picStream = SeetafaceTest.class.getClassLoader().getResourceAsStream("001.jpg");
        Future<SeetaRect[]> resultFuture = service.submitDetect(picStream);
        picStream = SeetafaceTest.class.getClassLoader().getResourceAsStream("001.jpg");
        new ImageFrame(picStream,resultFuture,SeetaRect[].class);
        TimeUnit.MILLISECONDS.sleep(3000);
    }

    /**
     * 人脸比较
     */
    @Test
    public void _02_testFaceCompare() throws Exception{
        InputStream picStream1 = SeetafaceTest.class.getClassLoader().getResourceAsStream("001.jpg");
        InputStream picStream2 = SeetafaceTest.class.getClassLoader().getResourceAsStream("002.jpg");
        Future<FaceCompareResult> resultFuture = service.submitCompare(picStream1,picStream2);
        FaceCompareResult result = resultFuture.get();
        Assert.assertFalse(result.isSamePerson());
        Assert.assertTrue( result.getSimilarRate() >= 0 && result.getSimilarRate() <= 1);
        System.out.printf("相似度：%f\n",result.getSimilarRate());
        System.out.printf("同一人：%s\n",result.isSamePerson() ? "是" : "否");
    }

    /**
     * 人脸关键点识别
     */
    @Test
    public void _03_testFaceDetectPoint() throws Exception{
        InputStream picStream = SeetafaceTest.class.getClassLoader().getResourceAsStream("001.jpg");
        Future<SeetaPointF[]> resultFuture = service.submitDetectPoint(picStream);
        picStream = SeetafaceTest.class.getClassLoader().getResourceAsStream("001.jpg");
        new ImageFrame(picStream,resultFuture,SeetaPointF[].class);
        TimeUnit.MILLISECONDS.sleep(3000);
    }

}
