package com.excilys.proxyconfig.sources;

import java.io.IOException;
import java.util.Properties;

/**
 * A {@link ConfigurationSource} based on a {@link Properties} object.
 *
 * @author bjansen
 * @since 1.0
 */
public class PropertiesConfigurationSource implements ConfigurationSource {

    private Properties properties;

    public PropertiesConfigurationSource(String propertiesPath) throws IOException {
        properties = new Properties();

        if (propertiesPath.endsWith(".xml")) {
            properties.loadFromXML(getClass().getClassLoader().getResourceAsStream(propertiesPath));
        } else {
            properties.load(getClass().getClassLoader().getResourceAsStream(propertiesPath));
        }
    }

    @Override
    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    @Override
    public Object getValue(String key) {
        return properties.get(key);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
