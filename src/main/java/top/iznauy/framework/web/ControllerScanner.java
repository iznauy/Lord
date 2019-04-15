package top.iznauy.framework.web;

import top.iznauy.framework.annotation.Action;
import top.iznauy.framework.annotation.Component;
import top.iznauy.framework.core.scanner.DefaultScanner;

import java.lang.reflect.Method;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class ControllerScanner extends DefaultScanner {

    @Override
    protected boolean addClass(Class<?> cls) {
        if (!cls.isAnnotationPresent(Component.class))
            return false;
        Method[] methods = cls.getMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(Action.class))
                return true;
        }
        return false;
    }
}
