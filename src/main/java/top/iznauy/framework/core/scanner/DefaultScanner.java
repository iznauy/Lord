package top.iznauy.framework.core.scanner;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class DefaultScanner implements Scanner {

    public DefaultScanner() {

    }

    private Class<?> loadClass(String className, boolean initialize, ClassLoader classLoader) {
        Class<?> cls;
        try {
            cls = Class.forName(className, initialize, classLoader);
        } catch (ClassNotFoundException e) {
            log.error("class not find", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public Set<Class<?>> scanPackage(String basePackage, ClassLoader loader) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = loader.getResources(basePackage.replace('.', '/'));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    String packagePath = url.getPath();
                    addClass(classSet, packagePath, basePackage, loader);
                }
            }
        } catch (IOException e) {
            log.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private void addClass(Set<Class<?>> classSet, String packagePath, String packageName, ClassLoader classLoader) {
        File[] files = new File(packagePath).listFiles(
                e -> (e.isFile() && e.getName().endsWith(".class")) || e.isDirectory());
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (packageName.length() > 0)
                    className = packageName + "." + className;
                addClass(classSet, className, classLoader);
            } else {
                String subPackagePath = fileName;
                if (packageName.length() > 0)
                    subPackagePath = packagePath + "/" + subPackagePath;
                String subPackageName = fileName;
                if (packageName.length() > 0)
                    subPackageName = packageName + "." + subPackageName;
                addClass(classSet, subPackagePath, subPackageName, classLoader);
            }
        }
    }

    protected boolean addClass(Class<?> cls) {
        return true;
    }

    private void addClass(Set<Class<?>> classSet, String className, ClassLoader classLoader) {
        Class<?> cls = loadClass(className, false, classLoader);
        if (addClass(cls))
            classSet.add(cls);
    }

}
