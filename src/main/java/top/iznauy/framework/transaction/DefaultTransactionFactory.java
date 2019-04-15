package top.iznauy.framework.transaction;

/**
 * Created on 15/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultTransactionFactory implements TransactionFactory {

    public DefaultTransactionFactory(String basePackage) {
    }

    @Override
    public TransactionBeanDefinitionProcessor getTransactionBeanDefinitionProcessor() {
        return null;
    }
}
