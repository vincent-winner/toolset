package io.vincentwinner.toolset.ai.faceid.seetaface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class SeetaProperty implements Serializable {

    private static final long serialVersionUID = -5885230438008961276L;

    private final String libraryPath;
    private final String modelPath;
    private final Double similarRate;
    private final Double factor;
    private final Integer threadMax;

    private SeetaProperty(String libraryPath, String modelPath, Double similarRate, Double factor, Integer threadMax) {
        this.libraryPath = libraryPath;
        this.modelPath = modelPath;
        this.similarRate = similarRate;
        this.factor = factor;
        this.threadMax = threadMax;
    }

    public String getLibraryPath() {
        return libraryPath;
    }

    public String getModelPath() {
        return modelPath;
    }

    public Double getSimilarRate() {
        return similarRate;
    }

    public Double getFactor() {
        return factor;
    }

    public Integer getThreadMax() {
        return threadMax;
    }

    private static SeetaProperty fromStream(InputStream source){
        Properties properties = new Properties();
        try {
            if(source == null){
                properties.load(SeetaProperty.class.getClassLoader().getResourceAsStream("ai-faceid-seeta.properties"));
            }
            properties.load(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String libraryPath = new File(properties.getProperty("library.path")).getAbsolutePath();
        String modelPath = new File(properties.getProperty("model.path")).getAbsolutePath();
        Double similarRate = Double.parseDouble(properties.getProperty("similar.rate"));
        Double factor = Double.parseDouble(properties.getProperty("factor"));
        Integer threadMax = Integer.parseInt(properties.getProperty("thread.max"));
        return new SeetaProperty(
                libraryPath,
                modelPath,
                similarRate,
                factor,
                threadMax
        );
    }

    public static SeetaProperty changeProperty(InputStream source){
        synchronized (SeetaProperty.class){
            Instance.INSTANCE = fromStream(source);
        }
        return Instance.INSTANCE;
    }

    private static final class Instance{
        private static SeetaProperty INSTANCE = null;
    }
    public static SeetaProperty getInstance() {
        if(Instance.INSTANCE == null){
            synchronized (SeetaProperty.class){
                if(Instance.INSTANCE == null){
                    Instance.INSTANCE = fromStream(SeetaProperty.class.getClassLoader().getResourceAsStream("ai-faceid-seeta.properties"));
                }
            }
        }
        return Instance.INSTANCE;
    }
}
