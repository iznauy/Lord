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
    public char[] getBinaryContext() {
        return content.toCharArray();
    }

    @Override
    public CharSequence getCharSequenceContext() {
        return content;
    }
}
