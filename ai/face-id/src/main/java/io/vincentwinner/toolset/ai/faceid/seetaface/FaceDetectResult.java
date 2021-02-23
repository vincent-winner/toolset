package io.vincentwinner.toolset.ai.faceid.seetaface;

import java.io.Serializable;

public class FaceDetectResult implements Serializable {

    private static final long serialVersionUID = -6491276254579582357L;

    private final Float similarRate;
    private final Boolean samePerson;

    protected FaceDetectResult(Float similarRate, Boolean samePerson) {
        if(similarRate != null && samePerson != null){
            this.similarRate = similarRate > 1f ? 1f : similarRate;
            this.samePerson = samePerson;
        }else {
            throw new RuntimeException("错误的处理结果");
        }
    }

    public strictfp Float getSimilarRate() {
        return similarRate;
    }

    public Boolean isSamePerson() {
        return samePerson;
    }

    @Override
    public String toString() {
        return "FaceDetectResult{" +
                "similarRate=" + similarRate +
                ", samePerson=" + samePerson +
                '}';
    }
}
