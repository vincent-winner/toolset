package io.vincentwinner.toolset.crypto.factory.impl;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Encoder;
import io.vincentwinner.toolset.crypto.factory.EncoderFactory;

import javax.crypto.spec.IvParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多算法编码器工厂
 * 该类提供了多算法编码器，可以对一个数据进行多算法编码
 */
public class MultiAlgorithmEncoderFactory implements EncoderFactory {

    private final Encoder encoder;

    public MultiAlgorithmEncoderFactory(List<String> encoderTypes){
        List<Encoder> encoders = new ArrayList<>(encoderTypes.size());
        encoderTypes.forEach(type -> {encoders.add(SingletonEncoderFactory.getEncoder(type));});
        this.encoder = new MultipleEncoder(encoders);
    }

    public MultiAlgorithmEncoderFactory(String... encoderTypes){
        this(Arrays.asList(encoderTypes));
    }

    public MultiAlgorithmEncoderFactory(String expression,String separator){
        this(Arrays.asList(expression.split(separator)));
    }

    private static class MultipleEncoder extends Encoder{

        private final List<Encoder> encoders;

        public MultipleEncoder(List<Encoder> encoders){
            this.encoders = encoders;
        }

        @Override
        public byte[] encode(final CryptoData data) {
            byte[] result = data.getData();
            byte[] key = data.getKey();
            IvParameterSpec iv = data.getIv();
            for (Encoder encoder : encoders){
                result = encoder.encode(new CryptoData(result,key,iv));
            }
            return result;
        }

    }

    @Override
    public Encoder getEncoder() {
        return this.encoder;
    }

}
