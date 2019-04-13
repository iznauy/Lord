package top.iznauy.framework.context.resource;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class StringResource implements Resource {

    private String content;

    public StringResource(String content) {
        this.content = content;
    }

    @Override
    public byte[] getBinaryContext() {
        return content.getBytes();
    }

    @Override
    public String getStringContext() {
        return content;
    }
}
