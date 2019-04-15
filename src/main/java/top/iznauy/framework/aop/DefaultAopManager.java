package top.iznauy.framework.aop;

import top.iznauy.framework.core.bean.BeanProxy;
import top.iznauy.framework.core.scanner.Scanner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultAopManager implements AopManager {

    private String basePackage;

    private ClassLoader classLoader;

    private Set<Class<?>> aspectClasses = new HashSet<>();

    public DefaultAopManager(String basePackage) {
        this.basePackage = basePackage;
    }

    private void init() {
        this.classLoader = getClassLoader();
    }

    protected void scanAspects() {
        Scanner scanner = new AspectScanner();

    }

    protected ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public AopBeanDefinitionProcessor getAopBeanDefinitionProcessor() {
        return null;
    }
}
