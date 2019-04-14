package top.iznauy.framework.core;

import top.iznauy.framework.annotation.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultBeanFactory implements BeanFactory {

    private static String basePackage;

    private static ComponentScanner scanner;

    private static Set<Class<?>> beanDefinitions = new HashSet<>();

    public DefaultBeanFactory(String basePackage) {
        DefaultBeanFactory.basePackage = basePackage;
        init();
    }

    private void init() {
        // 加载 bean
        loadBeanDefinitions();
        //
    }

    private void loadBeanDefinitions() {
        scanner = ComponentScanner.getInstance();
        beanDefinitions = scanner.scanPackage(basePackage);
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
