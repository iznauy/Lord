package top.iznauy.framework.transaction;

import top.iznauy.framework.annotation.Component;
import top.iznauy.framework.annotation.Transaction;
import top.iznauy.framework.core.scanner.DefaultScanner;

import java.lang.reflect.Method;

/**
 * Created on 15/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class TransactionScanner extends DefaultScanner {

    @Override
    protected boolean addClass(Class<?> cls) {
        if (!cls.isAnnotationPresent(Component.class))
            return false;
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Transaction.class))
                return true;
        }
        return false;
    }
}
