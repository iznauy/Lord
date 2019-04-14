package top.iznauy.framework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.iznauy.framework.annotation.Inject;
import top.iznauy.framework.core.bean.*;
import top.iznauy.framework.core.scanner.ComponentScanner;
import top.iznauy.framework.core.scanner.Scanner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
        for (Class<?> beanClass : this.beanClasses) {
            this.beanDefinitions.put(beanClass, new DefaultBeanDefinition(beanClass));
        }
    }

    @Override
    public void constructBeans() {
        // 首先先实例化
        instantiationBeans();
        // 然后依赖注入
        injectDependencies();
        // 然后执行初始化方法
        executeInitializingMethod();

    }

    private void instantiationBeans() {
        for (Map.Entry<Class<?>, BeanDefinition> entry : beanDefinitions.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            Class<?> cls = entry.getKey();
            Object bean = beanDefinition.getNewInstance();
            beanMap.put(cls, bean);
        }
    }

    private void injectDependencies() {

        DependenceInjector injector = new DependenceInjector();

        for (Map.Entry<Class<?>, BeanDefinition> entry : beanDefinitions.entrySet()) {
            Class<?> cls = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();

            Set<Class<?>> dependencies = beanDefinition.getDependencies();
            Map<Class<?>, Object> dependenceBeanMap = new HashMap<>();
            for (Class<?> dependence : dependencies) {
                dependenceBeanMap.put(dependence, beanMap.get(dependence));
            }

            Object bean = beanDefinition.getNewInstance();
            injector.inject(cls, bean, dependenceBeanMap);
        }
    }

    private void executeInitializingMethod() {
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof InitializingBean) {
                InitializingBean bean = (InitializingBean) obj;
                bean.afterPropertiesSet();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> cls) {
        return (T) beanMap.get(cls);
    }

    @Override
    public void process(BeanDefinitionProcessor beanDefinitionProcessor) {
        if (beanDefinitionProcessor != null) {
            for (Map.Entry<Class<?>, BeanDefinition> entry : beanDefinitions.entrySet()) {
                BeanDefinition beanDefinition = entry.getValue();
                beanDefinition.process(beanDefinitionProcessor);
            }
        }
    }

    @Override // 析构 bean
    public void destroy() {
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof DisposableBean) {
                DisposableBean bean = (DisposableBean) obj;
                bean.destroy();
            }
        }
    }
}

class DependenceInjector {

    private static final Logger log = LoggerFactory.getLogger(DependenceInjector.class);

    void inject(Class<?> cls, Object object, Map<Class<?>, Object> beanMap) {
        injectFields(cls, object, beanMap);
        injectMethods(cls, object, beanMap);
    }

    private void injectFields(Class<?> cls, Object object, Map<Class<?>, Object> beanMap) {
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Inject inject = field.getAnnotation(Inject.class);
                Class<?> targetClass = inject.target();
                Object bean = beanMap.get(targetClass);
                if (bean != null) {
                    try {
                        field.setAccessible(true);
                        field.set(object, bean);
                    } catch (IllegalAccessException e) {
                        log.error("error in dependency inject to field", e);
                    }
                }
            }
        }
    }

    private void injectMethods(Class<?> cls, Object object, Map<Class<?>, Object> beanMap) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Inject.class)) {
                Inject inject = method.getAnnotation(Inject.class);
                Class<?> targetClass = inject.target();
                Object bean = beanMap.get(targetClass);
                if (bean != null) {
                    try {
                        method.setAccessible(true);
                        method.invoke(object, bean);
                    } catch (Exception e) {
                        log.error("error in dependency inject to method", e);
                    }
                }
            }
        }
    }

}
