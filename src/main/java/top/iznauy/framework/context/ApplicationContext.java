package top.iznauy.framework.context;

import top.iznauy.framework.config.AppConfigConstant;
import top.iznauy.framework.config.AppConfigResolver;
import top.iznauy.framework.config.PropertiesWrapper;
import top.iznauy.framework.context.resource.FileResourceAccessor;
import top.iznauy.framework.context.resource.Resource;
import top.iznauy.framework.context.resource.ResourceAccessor;

import javax.servlet.ServletContext;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class ApplicationContext {

    public static final String ROOT_WEB_APP_CONTEXT_ATTRIBUTE = ApplicationContext.class.getName() + ".ROOT";

    public static final String CONFIG_FILE_ATTRIBUTE = "contextConfigLocation";

    private static final String DEFAULT_CONFIG_PATH = "file:WEB-INF/lord.property";

    private PropertiesWrapper properties = null;

    private ServletContext servletContext;

    private ResourceAccessor resourceAccessor;

    public ApplicationContext(ServletContext servletContext) {
        this.servletContext = servletContext;
        this.resourceAccessor = new FileResourceAccessor(servletContext);
        init();
    }

    private void init() {
        // get config path
        String configPath = servletContext.getInitParameter(CONFIG_FILE_ATTRIBUTE);
        if (configPath == null)
            configPath = DEFAULT_CONFIG_PATH;

        // get resource from config path
        Resource resource = resourceAccessor.loadResource(configPath);
        String appConfigContext = resource.getStringContext();

        AppConfigResolver resolver = AppConfigResolver.getInstance();
        properties = resolver.resolve(appConfigContext); // 配置文件读取完毕

        System.out.println(properties.getString(AppConfigConstant.APP_ASSET_PATH));
        System.out.println(properties.getString(AppConfigConstant.APP_BASE_PACKAGE));
        System.out.println(properties.getString(AppConfigConstant.JDBC_DRIVER));
        System.out.println(properties.getString(AppConfigConstant.JDBC_USERNAME));

    }



}
