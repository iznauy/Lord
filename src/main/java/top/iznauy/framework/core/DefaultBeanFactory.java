package top.iznauy.framework.core;

import com.sun.corba.se.spi.ior.ObjectKey;
import top.iznauy.framework.core.bean.BeanDefinition;
import top.iznauy.framework.core.bean.BeanDefinitionProcessor;
import top.iznauy.framework.core.bean.DefaultBeanDefinition;
import top.iznauy.framework.core.scanner.ComponentScanner;
import top.iznauy.framework.core.scanner.Scanner;

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

    private Map<Class<?>, Object> beanMap = new HashMap<>();

    public DefaultBeanFactory(String basePackage) {
        this.basePackage = basePackage;
        init();
    }

    private void init() {
        // 设置 类加载器
        this.classLoader = getClassLoader();
        // 查找有哪些 bean
        scanBeans();
        // 构造 bean definitions
        generateBeanDefinitions();
    }

    protected ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    protected void scanBeans() {
        Scanner scanner = new ComponentScanner();
        this.beanClasses = scanner.scanPackage(this.basePackage, this.classLoader);
    }

    protected void generateBeanDefinitions() {
        for (Class<?> beanClass: this.beanClasses) {
            this.beanDefinitions.put(beanClass, new DefaultBeanDefinition(beanClass));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> cls) {
        return (T) beanMap.get(cls);
    }

    @Override
    public void process(BeanDefinitionProcessor beanDefinitionProcessor) {
        for (Map.Entry<Class<?>, BeanDefinition> entry: beanDefinitions.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            beanDefinitionProcessor.process(beanDefinition);
        }
    }

    @Override
    public void destroy() {

    }
}
