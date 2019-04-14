package top.iznauy.framework.core.bean;

import top.iznauy.framework.core.bean.BeanDefinition;
import top.iznauy.framework.core.bean.BeanDefinitionProcessor;

import java.util.List;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultBeanDefinition implements BeanDefinition {

    private Class<?> cls;

    public DefaultBeanDefinition(Class<?> cls) {
        this.cls = cls;
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
    public Object getNewInstance() {
        return null;
    }

    @Override
    public void process(BeanDefinitionProcessor processor) {

    }

    @Override
    public List<Class<?>> getDependence() {
        return null;
    }
}
