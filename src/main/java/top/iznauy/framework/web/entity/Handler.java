package top.iznauy.framework.web.entity;

import java.lang.reflect.Method;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class Handler {

    private Object controller;

    private Method handlerMethod;

    public Handler(Object controller, Method handlerMethod) {
        this.controller = controller;
        this.handlerMethod = handlerMethod;
    }

    public Object getController() {
        return controller;
    }

    public Method getHandlerMethod() {
        return handlerMethod;
    }
}
