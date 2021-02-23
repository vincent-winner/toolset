package io.vincentwinner.toolset.ai.faceid.seetaface;

import java.io.Serializable;

/**
 * 人脸比较对比结果
 */
public class FaceCompareResult implements Serializable {

    private static final long serialVersionUID = -6491276254579582357L;

    private final Float similarRate;
    private final Boolean samePerson;

    protected FaceCompareResult(Float similarRate, Boolean samePerson) {
        if(similarRate != null && samePerson != null){
            this.similarRate = similarRate > 1f ? 1f : similarRate;
            this.samePerson = samePerson;
        }else {
            throw new RuntimeException("错误的处理结果");
        }
    }

    /**
     * 获取两张图片中人脸的相似率
     * 相似率为负数时代表没有检测到人脸，检测目标可能非人类
     * @return 人脸相似率
     */
    public strictfp Float getSimilarRate() {
        return similarRate;
    }

    /**
     * 判断两张图片中是否是同一个人
     * @return 两张图片中的人脸是否是同一个人
     */
    public Boolean isSamePerson() {
        return samePerson;
    }


}
