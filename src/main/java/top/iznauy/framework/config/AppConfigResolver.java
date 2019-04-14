package top.iznauy.framework.config;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
@Slf4j
public class AppConfigResolver {

    private static volatile AppConfigResolver INSTANCE;

    private AppConfigResolver() {

    }

    public static AppConfigResolver getInstance() {
        if (INSTANCE == null) {
            synchronized (AppConfigResolver.class) {
                if (INSTANCE == null)
                    INSTANCE = new AppConfigResolver();
            }
        }
        return INSTANCE;
    }

    public PropertiesWrapper resolve(String config) {
        InputStream in = new ByteArrayInputStream(config.getBytes());
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            log.error("resolve config error", e);
            throw new RuntimeException(e);
        }
        return new PropertiesWrapper(properties);
    }

}
