package top.iznauy.framework.core;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface BeanDefinition {

    Class<?> getBeanClass();

    Object getNewInstance();



}
