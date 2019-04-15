package top.iznauy.framework.web;

import lombok.extern.slf4j.Slf4j;
import top.iznauy.framework.annotation.Action;
import top.iznauy.framework.config.AppConfigConstant;
import top.iznauy.framework.context.ApplicationContext;
import top.iznauy.framework.core.scanner.Scanner;
import top.iznauy.framework.web.entity.Handler;
import top.iznauy.framework.web.entity.Param;
import top.iznauy.framework.web.entity.Request;
import top.iznauy.framework.web.entity.RequestMethod;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    private ApplicationContext applicationContext;

    private String basePackage;

    private ClassLoader classLoader;

    private RequestParamResolver requestParamResolver;

    private ResponseWriter responseWriter;

    private Set<Class<?>> controllerClasses = new HashSet<>();

    private Map<Request, Handler> handlerMap = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 将 Web 应用上下文存储在 dispatcherServlet 之中
        this.applicationContext = (ApplicationContext) config.getServletContext()
                .getAttribute(ApplicationContext.ROOT_WEB_APP_CONTEXT_ATTRIBUTE);

        // 获取 base package
        this.basePackage = applicationContext.getProperty(AppConfigConstant.APP_BASE_PACKAGE);

        // 设置类加载器
        this.classLoader = getClassLoader();

        // 设置参数解析器
        this.requestParamResolver = RequestParamResolver.getInstance();

        // 设置结果回写期
        this.responseWriter = ResponseWriter.getInstance();

        // 查找 controller
        scanControllers();

        // 构造 handlerMapping
        constructHandlerMapping();
    }

    protected void scanControllers() {
        Scanner scanner = new ControllerScanner();
        this.controllerClasses = scanner.scanPackage(this.basePackage, this.classLoader);
    }

    protected void constructHandlerMapping() {
        for (Class<?> cls : controllerClasses) {
            Object controller = this.applicationContext.getBean(cls);
            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Action.class)) {
                    Action action = method.getAnnotation(Action.class);
                    RequestMethod requestMethod = action.method();
                    String requestUrl = action.url();
                    Request request = new Request(requestMethod, requestUrl);
                    Handler handler = new Handler(controller, method);
                    handlerMap.put(request, handler);
                }
            }
        }
    }

    protected ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMethod requestMethod = RequestMethod.resolve(req.getMethod());
        String requestUrl = req.getPathInfo();
        Request request = new Request(requestMethod, requestUrl);
        Handler handler = handlerMap.get(request);
        if (handler != null) {
            // 获取处理的 bean 以及对应的方法
            Object controller = handler.getController();
            Method handlerMethod = handler.getHandlerMethod();

            // 准备参数
            Param param = this.requestParamResolver.resolveParams(req);

            // 执行
            Object result;

            try {
                handlerMethod.setAccessible(true);
                if (param.isEmpty())
                    result = handlerMethod.invoke(controller);
                else
                    result = handlerMethod.invoke(controller, param);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("incorrect method invoke", e);
                e.printStackTrace();
                resp.setStatus(400);
                return;
            }
            responseWriter.writeResponse(resp, result);

        } else {
            resp.setStatus(404);
        }
    }
}
