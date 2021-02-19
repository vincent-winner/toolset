package io.vincentwinner.toolset.crypto.factory.impl;

import io.vincentwinner.toolset.crypto.CryptoData;
import io.vincentwinner.toolset.crypto.Decoder;
import io.vincentwinner.toolset.crypto.factory.DecoderFactory;

import javax.crypto.spec.IvParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多算法解码器工厂
 * 该类提供了多算法解码器，可以对一个数据进行多算法解码
 * 本类所有构造方法填写与{@link MultiAlgorithmEncoderFactory}相同的构造参数，即可构造对应的多算法解码器
 */
public class MultiAlgorithmDecoderFactory implements DecoderFactory {

    private final Decoder decoder;

    public MultiAlgorithmDecoderFactory(List<String> encoderTypes){
        List<Decoder> decoders = new ArrayList<>(encoderTypes.size());
        encoderTypes.forEach(type -> {decoders.add(SingletonDecoderFactory.getDecoder(type));});
        this.decoder = new MultipleDecoder(decoders);
    }
    public MultiAlgorithmDecoderFactory(String... encoderTypes){
        this(Arrays.asList(encoderTypes));
    }
    public MultiAlgorithmDecoderFactory(String expression,String separator){
        this(Arrays.asList(expression.split(separator)));
    }

    private static class MultipleDecoder extends Decoder{
        private final List<Decoder> decoders;
        public MultipleDecoder(List<Decoder> decoders){this.decoders = decoders;}
        @Override
        public byte[] decode(CryptoData cipherData) {
            byte[] result = cipherData.getData();
            byte[] key = cipherData.getKey();
            IvParameterSpec iv = cipherData.getIv();
            for (int i = decoders.size() - 1; i >= 0 ; i-- ){
                result = decoders.get(i).decode(new CryptoData(result,key,iv));
            }
            return result;
        }
    }

    @Override
    public Decoder getDecoder() {
        return this.decoder;
    }

}
