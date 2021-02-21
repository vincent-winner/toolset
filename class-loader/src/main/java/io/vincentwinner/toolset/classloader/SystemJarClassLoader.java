package io.vincentwinner.toolset.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 该类加载器会利用系统默认类加载器 AppClassLoader 加载 jar 包
 * 由该类加载器加载的 jar 包与 java -classpath 或 java -cp 加载 jar 包的形式相同
 * 该类加载的 jar 包中的所有内容会被直接添加到 ClassPath 中
 * 由该类加载的 jar 包中的所有类可以同系统类加载器加载的类同样使用
 */
public class SystemJarClassLoader extends JarClassLoader {

    public SystemJarClassLoader(){ }

    public SystemJarClassLoader(String jarPath){
        loadJars(new File(jarPath));
    }

    @Override
    public void addURL(URL url) {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        try {
            final Method addURL = URLClassLoader.class.getDeclaredMethod("addURL",URL.class);
            addURL.setAccessible(true);
            addURL.invoke(appClassLoader,url);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        super.addURL(url);
    }


}
