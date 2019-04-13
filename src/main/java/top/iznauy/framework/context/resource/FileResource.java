package top.iznauy.framework.context.resource;

import java.io.File;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class FileResource implements Resource {

    private File file;

    public FileResource(File file) {
        this.file = file;
    }

    @Override
    public char[] getBinaryContext() {
        return new char[0];
    }

    @Override
    public CharSequence getCharSequenceContext() {
        return null;
    }
}
