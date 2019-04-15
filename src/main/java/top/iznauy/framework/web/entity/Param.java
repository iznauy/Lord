package top.iznauy.framework.web.entity;

import java.util.Map;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public boolean isEmpty() {
        return paramMap.isEmpty();
    }

}
