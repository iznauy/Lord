package top.iznauy.framework.core.bean;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface BeanProxy {

    Object doProxy(BeanProxyChain proxyChain) throws Throwable;

}
