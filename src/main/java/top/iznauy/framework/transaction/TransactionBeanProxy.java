package top.iznauy.framework.transaction;

import lombok.extern.slf4j.Slf4j;
import top.iznauy.framework.annotation.Transaction;
import top.iznauy.framework.core.bean.BeanProxy;
import top.iznauy.framework.core.bean.BeanProxyChain;

import java.lang.reflect.Method;

/**
 * Created on 15/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class TransactionBeanProxy implements BeanProxy {

    // 是否已经开启 transaction
    // 相当于 Spring 中的 PROPAGATION_REQUIRED 这种传播级别
    private final ThreadLocal<Boolean> flagHolder = ThreadLocal.withInitial(() -> false);
    private TransactionManager transactionManager;

    public TransactionBeanProxy(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public Object doProxy(BeanProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = flagHolder.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            flagHolder.set(true);
            try {
                // start transaction
                transactionManager.beginTransaction();
                log.debug("begin transaction");
                result = proxyChain.doProxyChain();
                // close transaction
                transactionManager.commitTransaction();
                log.debug("commit transaction");
            } catch (Exception e) {
                // roll back
                transactionManager.rollbackTransaction();
                log.debug("rollback transaction");
                throw e;
            } finally {
                flagHolder.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
