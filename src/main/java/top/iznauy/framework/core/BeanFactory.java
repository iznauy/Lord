package top.iznauy.framework.core;


/**
 * Created on 12/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface BeanFactory {

    <T> T getBean(Class<T> cls);

    <T> T getBeanByInterface(Class<? super T> cls);

    void destroy();

}
