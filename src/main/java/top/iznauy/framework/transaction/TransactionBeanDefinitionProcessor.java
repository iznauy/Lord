package top.iznauy.framework.transaction;

import top.iznauy.framework.core.bean.BeanDefinition;
import top.iznauy.framework.core.bean.BeanDefinitionProcessor;

import java.util.Set;

/**
 * Created on 15/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class TransactionBeanDefinitionProcessor implements BeanDefinitionProcessor {

    private TransactionBeanProxy beanProxy;

    private Set<Class<?>> targets;

    public TransactionBeanDefinitionProcessor(TransactionBeanProxy beanProxy, Set<Class<?>> targets) {
        this.beanProxy = beanProxy;
        this.targets = targets;
    }

    @Override
    public void process(BeanDefinition beanDefinition) {
        Class<?> cls = beanDefinition.getBeanClass();
        if (targets.contains(cls))
            beanDefinition.addBeanProxy(beanProxy);
    }
}
