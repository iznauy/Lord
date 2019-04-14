package top.iznauy.framework.aop;

import lombok.extern.slf4j.Slf4j;
import top.iznauy.framework.core.bean.BeanProxy;
import top.iznauy.framework.core.bean.BeanProxyChain;

import java.lang.reflect.Method;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public abstract class AbstractAspectBeanProxy implements BeanProxy {

    @Override
    public final Object doProxy(BeanProxyChain proxyChain) throws Throwable {
        Object result;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        Object targetObject = proxyChain.getTargetObject();

        begin();

        try {
            if (intercept(cls, method, params, targetObject)) {
                before(cls, method, params, targetObject);
                result = proxyChain.doProxyChain();
                after(cls, method, params, targetObject, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            log.error("proxy failure", e);
            error(cls, method, params, targetObject, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    private void begin() {

    }

    public boolean intercept(Class<?> cls, Method method, Object[] params, Object targetObject) throws Throwable {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params, Object targetObject) throws Throwable {

    }

    public void after(Class<?> cls, Method method, Object[] params, Object result, Object targetObject) throws Throwable {

    }

    public void error(Class<?> cls, Method method, Object[] params, Object targetObject, Throwable e) {

    }

    public void end() {

    }

}
