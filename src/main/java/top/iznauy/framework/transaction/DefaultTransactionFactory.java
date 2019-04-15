package top.iznauy.framework.transaction;

import top.iznauy.framework.core.scanner.Scanner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 15/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultTransactionFactory implements TransactionFactory {

    private String basePackage;

    private ClassLoader classLoader;

    private Set<Class<?>> transactionClasses = new HashSet<>();

    private TransactionManager transactionManager;

    public DefaultTransactionFactory(String basePackage) {
        this.basePackage = basePackage;
        this.init();
    }

    private void init() {
        this.classLoader = getClassLoader();
        // 扫描需要进行事务管理的类
        scanTransactions();
        // 加载事务管理器
        loadTransactionManager();
    }

    protected void scanTransactions() {
        Scanner scanner = new TransactionScanner();
        this.transactionClasses = scanner.scanPackage(this.basePackage, this.classLoader);
    }

    protected void loadTransactionManager() {
        this.transactionManager = null;
    }

    protected ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public TransactionBeanDefinitionProcessor getTransactionBeanDefinitionProcessor() {
        TransactionBeanProxy beanProxy = new TransactionBeanProxy(this.transactionManager);
        return new TransactionBeanDefinitionProcessor(beanProxy, transactionClasses);
    }


}
