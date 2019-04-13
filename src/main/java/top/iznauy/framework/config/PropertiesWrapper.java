package top.iznauy.framework.config;

import java.util.Properties;

/**
 * Created on 13/04/2019.
 * Description:
 *
 * @author iznauy
 */
public class PropertiesWrapper {

    private Properties properties;

    public PropertiesWrapper(Properties properties) {
        this.properties = properties;
    }

    public String getString(String key) {
        return properties.getProperty(key, "");
    }

    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        int value = defaultValue;
        if (properties.contains(key)) {
            String strValue = properties.getProperty(key).trim();
            try {
                value = Integer.valueOf(strValue);
            } catch (NumberFormatException e) {
                value = defaultValue;
            }
        }
        return value;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (properties.contains(key)) {
            String strValue = properties.getProperty(key).trim();
            value = Boolean.parseBoolean(strValue);
        }
        return value;
    }

}
