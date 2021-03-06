package top.iznauy.framework.context;

import top.iznauy.framework.core.BeanFactory;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface ApplicationContext {

    String ROOT_WEB_APP_CONTEXT_ATTRIBUTE = "iznauy";

    String CONFIG_FILE_ATTRIBUTE = "contextConfigLocation";

    String DEFAULT_CONFIG_PATH = "file:WEB-INF/lord.property";

    String getProperty(String key);

    void destroy();

    BeanFactory getBeanFactory();

    <T> T getBean(Class<T> cls);

}
