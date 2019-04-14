package top.iznauy.framework.core.bean;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class BeanProxyChain {

    private final Class<?> targetClass;

    private final Object targetObject;

    private final Method targetMethod;

    private final MethodProxy methodProxy;

    private final Object[] methodParams;

    private List<BeanProxy> beanProxies;

    private int proxyIndex = 0;

    public BeanProxyChain(Class<?> targetClass, Object targetObject,
                          Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<BeanProxy> beanProxies) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.beanProxies = beanProxies;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public List<BeanProxy> getBeanProxies() {
        return beanProxies;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < beanProxies.size()) {
            methodResult = beanProxies.get(proxyIndex).doProxy(this);
            proxyIndex++;
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }

}
