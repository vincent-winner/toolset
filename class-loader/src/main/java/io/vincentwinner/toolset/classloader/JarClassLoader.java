package io.vincentwinner.toolset.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public class JarClassLoader extends URLClassLoader{

    private Set<String> installedJarsAbsolutePath;

    /**
     * 构造
     */
    public JarClassLoader() {
        this(new URL[]{});
        installedJarsAbsolutePath = new HashSet<>();
    }

    /**
     * 构造
     *
     * @param urls 被加载的URL
     */
    public JarClassLoader(URL[] urls) {
        super(urls, getClassloader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    /**
     * 如果参数为 <b>Jar文件</b> 则直接将其加载
     * 如果参数为 <b>目录</b> 则加载目录中的所有jar文件
     * @param dir 目录或 jar 文件
     */
    public void loadJars(File dir) {
        if (dir == null) {
            throw new RuntimeException("无法在空的目录或 jar 中读取 class文件");
        }
        try {
            if(isJarFile(dir)){
                addURL(dir.toURI().toURL());
            }else {
                final File[] jars = dir.listFiles(JarClassLoader::isJarFile);
                if(jars != null && jars.length > 0){
                    for(File jar : jars){
                        addURL(jar.toURI().toURL());
                    }
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("无法使用给出的URL加载class文件，提供了错误的URL");
        }
    }

    /**
     * 是否为jar文件
     *
     * @param file 文件
     * @return 是否为jar文件
     * @since 4.4.2
     */
    private static boolean isJarFile(File file) {
        if (!file.isFile()) {
            return false;
        }
        return file.getPath().toLowerCase().endsWith(".jar");
    }

    private static ClassLoader getClassloader(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = JarClassLoader.class.getClassLoader();
            if (null == classLoader) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

}
