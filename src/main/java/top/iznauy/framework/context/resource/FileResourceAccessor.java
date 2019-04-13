package top.iznauy.framework.context.resource;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class FileResourceAccessor implements ResourceAccessor {

    private ServletContext servletContext;

    public FileResourceAccessor(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public Resource loadResource(String descriptor) {
        String filePath = getAbsolutePath(descriptor);
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("Cannot find file " + descriptor);
            throw new RuntimeException("Cannot find file " + descriptor);
        }
        return new FileResource(file);
    }

    private String getAbsolutePath(String fileProtocol) {
        if (fileProtocol.startsWith("file:")) {
            String relativePath = fileProtocol.split(":")[1];
            return getWebRootPath() + relativePath;
        }
        return getWebRootPath() + fileProtocol;
    }

    private String getWebRootPath() {
        return servletContext.getRealPath("/");
    }

}
