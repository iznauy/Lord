package top.iznauy.framework.context;

import top.iznauy.framework.config.AppConfigConstant;
import top.iznauy.framework.config.AppConfigResolver;
import top.iznauy.framework.config.PropertiesWrapper;
import top.iznauy.framework.context.resource.FileResourceAccessor;
import top.iznauy.framework.context.resource.Resource;
import top.iznauy.framework.context.resource.ResourceAccessor;
import top.iznauy.framework.core.BeanFactory;

import javax.servlet.ServletContext;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public interface ApplicationContext {

    String ROOT_WEB_APP_CONTEXT_ATTRIBUTE = ApplicationContext.class.getName() + ".ROOT";

    String CONFIG_FILE_ATTRIBUTE = "contextConfigLocation";

    String DEFAULT_CONFIG_PATH = "file:WEB-INF/lord.property";

    String getProperty(String key);

    void destroy();

    BeanFactory getBeanFactory();

}
