package top.iznauy.framework.core.scanner;

import java.util.Set;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface Scanner {

    Set<Class<?>> scanPackage(String basePackage, ClassLoader loader);

}
