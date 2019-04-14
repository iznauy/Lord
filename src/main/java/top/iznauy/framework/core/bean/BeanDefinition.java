package top.iznauy.framework.core.bean;

import java.util.Set;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface BeanDefinition {

    Class<?> getBeanClass();

    void setBeanClass(Class<?> cls);

    Object getNewInstance();

    void process(BeanDefinitionProcessor processor);

    Set<Class<?>> getDependencies();

    void addBeanProxy(BeanProxy beanProxy);

}
