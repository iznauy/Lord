package top.iznauy.framework.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class ResponseWriter {

    private static ResponseWriter instance;

    private ResponseWriter() {

    }

    public static ResponseWriter getInstance() {
        if (instance == null) {
            instance = new ResponseWriter();
        }
        return instance;
    }

    public void writeResponse(HttpServletResponse resp, Object result) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        if (result instanceof String) {
            String res = (String) result;
            writer.write(res);
        } else {
            writer.write(JsonUtil.toJson(result));
        }
        writer.flush();
        writer.close();
    }

}
