package top.iznauy.framework.web;

import lombok.extern.slf4j.Slf4j;
import top.iznauy.framework.context.ApplicationContext;
import top.iznauy.framework.context.DefaultApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class ContextLoader {

    private static volatile ApplicationContext applicationContext;

    ContextLoader() {

    }

    void initWebApplicationContext(ServletContext servletContext) {
        servletContext.log("Initializing Lord Application Context");
        applicationContext = new DefaultApplicationContext(servletContext);
        servletContext.setAttribute(ApplicationContext.ROOT_WEB_APP_CONTEXT_ATTRIBUTE, servletContext);
    }

    void destroyWebApplicationContext() {
        applicationContext.destroy();
    }

}
