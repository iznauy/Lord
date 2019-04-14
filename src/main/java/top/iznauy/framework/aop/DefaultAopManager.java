package top.iznauy.framework.aop;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultAopManager implements AopManager {

    public DefaultAopManager(String basePackage) {
    }

    @Override
    public AopBeanDefinitionProcessor getAopBeanDefinitionProcessor() {
        return null;
    }
}
