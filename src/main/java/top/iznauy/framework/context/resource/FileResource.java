package top.iznauy.framework.context.resource;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class FileResource implements Resource {

    private File file;

    public FileResource(File file) {
        this.file = file;
    }

    @Override
    public byte[] getBinaryContext() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = null;
        try  {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = in.read(buf)) > 0)
                out.write(buf, 0, count);
        } catch (IOException e) {
            log.error("load file error", e);
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("close file error", e);
                }
            }
        }
        return out.toByteArray();
    }

    @Override
    public String getStringContext() {
        byte[] bytes = getBinaryContext();
        return new String(bytes);
    }
}
