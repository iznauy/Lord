package top.iznauy.framework.aop;

import top.iznauy.framework.annotation.Aspect;
import top.iznauy.framework.core.bean.BeanProxy;
import top.iznauy.framework.core.scanner.DefaultScanner;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class AspectScanner extends DefaultScanner {

    @Override
    protected boolean addClass(Class<?> cls) {
        return cls.isAnnotationPresent(Aspect.class) && BeanProxy.class.isAssignableFrom(cls);
    }
}
