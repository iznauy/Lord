package top.iznauy.framework.web;

import lombok.extern.slf4j.Slf4j;
import top.iznauy.framework.web.entity.Param;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class RequestParamResolver {

    private static RequestParamResolver instance;

    private RequestParamResolver() {

    }

    public static RequestParamResolver getInstance() {
        if (instance == null) {
            instance = new RequestParamResolver();
        }
        return instance;
    }

    public Param resolveParams(HttpServletRequest req) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        Enumeration<String> paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
        InputStream in = req.getInputStream();
        String body = decodeURL(getString(in));
        String[] params = body.split("&");
        for (String param : params) {
            String[] array = param.split("=");
            if (array.length == 2) {
                String paramName = array[0];
                String paramValue = array[1];
                paramMap.put(paramName, paramValue);
            }
        }
        return new Param(paramMap);
    }

    private String getString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = read.readLine()) != null) {
            sb.append(line);
        }
        inputStream.close();
        return sb.toString();
    }

    private String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            log.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

}
