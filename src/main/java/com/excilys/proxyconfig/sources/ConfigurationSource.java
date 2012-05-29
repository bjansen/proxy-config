package com.excilys.proxyconfig.sources;

/**
 * Describes a source of configuration objects which should be represented as a set of key/value pairs.
 *
 * @author bjansen
 * @since 1.0
 */
public interface ConfigurationSource {

    public boolean containsKey(String key);

    public Object getValue(String key);
}
