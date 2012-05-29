package com.excilys.proxyconfig.sources;

import java.util.ResourceBundle;

/**
 * A {@link ConfigurationSource} based on a {@link java.util.ResourceBundle} object.
 * 
 * @author bjansen
 * @since 1.0
 */
public class ResourceBundleConfigurationSource implements ConfigurationSource {

    private ResourceBundle resourceBundle;

    public ResourceBundleConfigurationSource(String bundlePath) {
        resourceBundle = ResourceBundle.getBundle(bundlePath);
    }

    @Override
    public boolean containsKey(String key) {
        return resourceBundle.containsKey(key);
    }

    @Override
    public Object getValue(String key) {
        return resourceBundle.getObject(key);
    }

    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
