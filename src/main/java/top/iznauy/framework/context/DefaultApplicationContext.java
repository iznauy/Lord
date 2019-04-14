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
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class DefaultApplicationContext implements ApplicationContext {

    private PropertiesWrapper properties = null;

    private ServletContext servletContext;

    private ResourceAccessor resourceAccessor;

    private BeanFactory beanFactory;

    public DefaultApplicationContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.resourceAccessor = new FileResourceAccessor(servletContext);
        init();
    }

    private void init() {
        // get config path
        String configPath = servletContext.getInitParameter(CONFIG_FILE_ATTRIBUTE);
        if (configPath == null)
            configPath = ApplicationContext.DEFAULT_CONFIG_PATH;

        // get resource from config path
        Resource resource = resourceAccessor.loadResource(configPath);
        String appConfigContext = resource.getStringContext();

        AppConfigResolver resolver = AppConfigResolver.getInstance();
        properties = resolver.resolve(appConfigContext); // 配置文件读取完毕

        String appBasePackagePath = properties.getString(AppConfigConstant.APP_BASE_PACKAGE, "");


    }

    @Override
    public String getProperty(String key) {
        return properties.getString(key);
    }

    public void destroy() {
        this.beanFactory.destroy();
    }

    @Override
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
