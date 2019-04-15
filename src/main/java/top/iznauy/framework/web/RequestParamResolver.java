package top.iznauy.framework.web;

import top.iznauy.framework.web.entity.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class RequestParamResolver {

    private RequestParamResolver() {

    }

    private static RequestParamResolver instance;

    public static RequestParamResolver getInstance() {
        if (instance == null) {
            instance = new RequestParamResolver();
        }
        return instance;
    }

    public Param resolveParams(HttpServletRequest req) {
        return null;
    }

}
