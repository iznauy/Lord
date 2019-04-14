package top.iznauy.framework.core.bean;

import java.util.List;

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

    List<Class<?>> getDependence();

}
