package top.iznauy.framework.aop;

import lombok.extern.slf4j.Slf4j;
import top.iznauy.framework.annotation.Component;
import top.iznauy.framework.core.bean.BeanDefinition;
import top.iznauy.framework.core.bean.BeanDefinitionProcessor;
import top.iznauy.framework.core.bean.BeanProxy;

import java.util.*;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class AopBeanDefinitionProcessor implements BeanDefinitionProcessor {

    private Map<Class<?>, List<BeanProxy>> targetProxyMap = new HashMap<>();

    // aspect to target class
    public AopBeanDefinitionProcessor(Map<Class<?>, Class<?>[]> rawMap) {
        for (Map.Entry<Class<?>, Class<?>[]> entry : rawMap.entrySet()) {
            Class<?> proxyClass = entry.getKey();
            Class<?>[] targetClasses = entry.getValue();
            try {
                BeanProxy beanProxy = (BeanProxy) proxyClass.newInstance();
                for (Class<?> targetClass : targetClasses) {
                    if (targetClass.isAnnotationPresent(Component.class)) {
                        List<BeanProxy> proxyList = this.targetProxyMap.get(targetClass);
                        if (proxyList == null)
                            proxyList = new ArrayList<>();
                        proxyList.add(beanProxy);
                        this.targetProxyMap.put(targetClass, proxyList);
                    }
                }
            } catch (Exception e) {
                log.error("init bean proxy error", e);
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void process(BeanDefinition beanDefinition) {
        Class<?> cls = beanDefinition.getBeanClass();
        List<BeanProxy> proxyList = targetProxyMap.getOrDefault(cls, Collections.emptyList());
        for (BeanProxy beanProxy : proxyList) {
            beanDefinition.addBeanProxy(beanProxy);
        }
    }
}
