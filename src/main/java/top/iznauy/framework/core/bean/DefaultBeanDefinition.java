package top.iznauy.framework.core.bean;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import top.iznauy.framework.annotation.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultBeanDefinition implements BeanDefinition {

    private Class<?> cls;

    private List<BeanProxy> beanProxyList;

    public DefaultBeanDefinition(Class<?> cls) {
        this.cls = cls;
        this.beanProxyList = new LinkedList<>();
    }

    @Override
    public Class<?> getBeanClass() {
        return cls;
    }

    @Override
    public void setBeanClass(Class<?> cls) {
        this.cls = cls;
    }

    @Override
    public void addBeanProxy(BeanProxy beanProxy) {
        this.beanProxyList.add(beanProxy);
    }

    @Override
    public Object getNewInstance() { // 返回一个已经被 CGLIB 代理过的，但是还没有依赖注入的对象
        return Enhancer.create(this.cls, (MethodInterceptor) (o, method, objects, methodProxy) ->
                new BeanProxyChain(this.cls, o, method, methodProxy,
                        objects, this.beanProxyList).doProxyChain());
    }

    @Override
    public void process(BeanDefinitionProcessor processor) {
        processor.process(this);
    }

    @Override
    public Set<Class<?>> getDependencies() {
        Set<Class<?>> classSet = getDependenciesOfFields();
        classSet.addAll(getDependenciesOfMethods());
        return classSet;
    }

    private Set<Class<?>> getDependenciesOfFields() {
        Set<Class<?>> classSet = new HashSet<>();
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Inject inject = field.getAnnotation(Inject.class);
                Class<?> cls = inject.target();
                classSet.add(cls);
            }
        }
        return classSet;
    }

    private Set<Class<?>> getDependenciesOfMethods() {
        Set<Class<?>> classSet = new HashSet<>();
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Inject.class)) {
                Inject inject = method.getAnnotation(Inject.class);
                Class<?> cls = inject.target();
                classSet.add(cls);
            }
        }
        return classSet;
    }

}
