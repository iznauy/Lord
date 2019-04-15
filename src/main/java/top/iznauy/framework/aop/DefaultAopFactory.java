package top.iznauy.framework.aop;

import top.iznauy.framework.annotation.Aspect;
import top.iznauy.framework.core.scanner.Scanner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultAopFactory implements AopFactory {

    private String basePackage;

    private ClassLoader classLoader;

    private Set<Class<?>> aspectClasses = new HashSet<>();

    private Map<Class<?>, Class<?>[]> targetMap = new HashMap<>();

    public DefaultAopFactory(String basePackage) {
        this.basePackage = basePackage;
        this.init();
    }

    private void init() {
        this.classLoader = getClassLoader();
        // 扫描哪些切面
        this.scanAspects();
        // 构造目标 map
        constructTargetMap();
    }

    protected void scanAspects() {
        Scanner scanner = new AspectScanner();
        this.aspectClasses = scanner.scanPackage(this.basePackage, this.classLoader);
    }

    protected void constructTargetMap() {
        for (Class<?> aspectClass: aspectClasses) {
            Aspect aspect = aspectClass.getAnnotation(Aspect.class);
            Class<?>[] targets = aspect.value();
            targetMap.put(aspectClass, targets);
        }
    }

    protected ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public AopBeanDefinitionProcessor getAopBeanDefinitionProcessor() {
        return new AopBeanDefinitionProcessor(this.targetMap);
    }
}
