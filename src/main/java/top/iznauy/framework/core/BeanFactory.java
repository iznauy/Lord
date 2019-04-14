package top.iznauy.framework.core;


import top.iznauy.framework.core.bean.BeanDefinitionProcessor;

/**
 * Created on 12/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface BeanFactory {

    <T> T getBean(Class<T> cls);

    void process(BeanDefinitionProcessor beanDefinitionProcessor);

    void constructBeans();

    void destroy();

}
