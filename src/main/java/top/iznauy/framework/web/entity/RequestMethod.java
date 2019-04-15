package top.iznauy.framework.web.entity;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public enum RequestMethod {

    GET,
    POST;

    public static RequestMethod resolve(String str) {
        str = str.toLowerCase();
        switch (str) {
            case "get":
                return GET;
            case "post":
                return POST;
            default:
                return null;
        }
    }

}
