package top.iznauy.framework.web.entity;

/**
 * Created on 14/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class Request {

    private RequestMethod requestMethod;

    private String requestPath;

    public Request(RequestMethod requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (requestMethod != request.requestMethod) return false;
        return requestPath != null ? requestPath.equals(request.requestPath) : request.requestPath == null;
    }

    @Override
    public int hashCode() {
        int result = requestMethod != null ? requestMethod.hashCode() : 0;
        result = 31 * result + (requestPath != null ? requestPath.hashCode() : 0);
        return result;
    }
}
