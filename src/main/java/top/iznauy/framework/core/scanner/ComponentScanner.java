package top.iznauy.framework.core.scanner;

import top.iznauy.framework.annotation.Component;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class ComponentScanner extends DefaultScanner {

    @Override
    protected boolean addClass(Class<?> cls) {
        return cls.isAnnotationPresent(Component.class);
    }
}
