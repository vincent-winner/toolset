package io.vincentwinner.toolset.ai.faceid.seetaface;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * 本 package 的人脸识别程序配置类
 * 直接调用 {@link #getInstance()} 方法将使用默认配置文件名 ai-faceid-seeta.properties
 *
 * 使用默认配置文件的方法
 * <pre>
 *     1.将本工具类路径下的 ai-faceid-seeta.common.properties 复制到 maven 工程下的 resource 文件夹内
 *     2.将 ai-faceid-seeta.common.properties 更名为 ai-faceid-seeta.properties （将名字中的.common去掉）
 *     3.下载并将 c++ 动态链接库文件放置到工程跟目录下的 lib 文件夹中
 *     4.下载并将深度学习模型放置到工程根目录下的 bindata 文件夹中
 * </pre>
 *
 * 初始化配置文件后使用本工具的方法
 * FaceHandleService service = FaceHandleService.getInstance();
 * 调用 {@link FaceHandleService#submitCompare(InputStream, InputStream)} 方法对两张图片进行人脸比较
 * 调用 {@link FaceHandleService#submitDetect(InputStream)} 方法检测图片中人脸的位置
 */
public class SeetaProperty implements Serializable {

    private static final long serialVersionUID = -5885230438008961276L;

    private final String libraryPath;  // 存放动态链接库的目录
    private final String modelPath;    // 存放深度学习模型的目录
    private final Double similarRate;  // 程序识别人脸相似率高于此值认为两张图片中为同一人
    private final Double factor;       // 识别因数，用于调整误差
    private final Integer threadMax;   // 最大并行初始化处理图片线程数量

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

    /**
     * 从指定的输入流中读取配置文件
     * @param source 配置文件输入流
     * @return 配置类
     */
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

    /**
     * 改变配置文件
     * 配置类采用线程安全单例模式，
     * 如果配置文件已经被某线程实例化，则读取新的配置文件后将实例改变
     * 如果配置文件没有被任何线程实例化，该方法会读取配置文件并生成唯一实例
     *
     * 本方法最大的用处在于采用自定义的配置文件
     * 使用自定义配置文件的方法
     * 1.1 用于读取类路径中的配置文件
     * 1.2 用于读取直接位于文件系统上的配置文件
     * 2.中的方法只需要执行一次，为了实例化唯一配置类
     * <pre>
     *     1.
     *       1.1 InputStream is = getClass().getClassLoader().getResourceAsStream("path/to/custom.properties");
     *       1.2 InputStream is = new FileInputStream("/path/to/custom.properties");
     *     2.SeetaProperty.changeProperty(is);
     *     3.SeetaProperty property = SeetaProperty.getInstance();
     * </pre>
     * @param source 配置文件输入流
     * @return 配置类
     */
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
                    ClassLoader classLoader = SeetaProperty.class.getClassLoader();
                    InputStream propertiesStream = classLoader.getResourceAsStream("ai-faceid-seeta.properties");
                    if(propertiesStream != null){
                        Instance.INSTANCE = fromStream(propertiesStream);
                    }else {
                        Instance.INSTANCE = fromStream(classLoader.getResourceAsStream("ai-faceid-seeta.common.properties"));
                    }
                }
            }
        }
        return Instance.INSTANCE;
    }
}
