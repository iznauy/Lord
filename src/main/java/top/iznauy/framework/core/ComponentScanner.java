package top.iznauy.framework.core;

import java.util.Set;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class ComponentScanner {

    private static ComponentScanner instance;

    private ComponentScanner() {

    }

    public static ComponentScanner getInstance() {
        if (instance == null)
            instance = new ComponentScanner();
        return instance;
    }

    public Set<Class<?>> scanPackage(String basePackage) {
        return null;
    }

}
