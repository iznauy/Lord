package top.iznauy.framework.core;

import top.iznauy.framework.annotation.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultBeanFactory implements BeanFactory {

    private String basePackage;

    private ClassLoader classLoader;

    private Set<Class<?>> beanClasses = new HashSet<>();

    private Map<Class<?>, BeanDefinition> beanDefinitions = new HashMap<>();

    public DefaultBeanFactory(String basePackage) {
        this.basePackage = basePackage;
        init();
    }

    private void init() {
        this.classLoader = getClassLoader();
        // 查找有哪些 bean
        scanBeans();
        //
    }

    protected ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    protected void scanBeans() {
        ComponentScanner componentScanner = new ComponentScanner();
        beanClasses = componentScanner.scanPackage(this.basePackage, this.classLoader);
    }

    protected void generateBeanDefinitions() {
        
    }

    @Override
    public <T> T getBean(Class<T> cls) {
        return null;
    }

    @Override
    public <T> T getBeanByInterface(Class<? super T> cls) {
        return null;
    }

    @Override
    public void destroy() {

    }
}
