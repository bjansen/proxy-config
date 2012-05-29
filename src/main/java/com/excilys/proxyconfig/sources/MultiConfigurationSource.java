package com.excilys.proxyconfig.sources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MultiConfigurationSource implements ConfigurationSource {

    public List<ConfigurationSource> sources = new ArrayList<ConfigurationSource>();

    public MultiConfigurationSource(ConfigurationSource... sources) {
        if (sources != null && sources.length > 0) {
            this.sources.addAll(Arrays.asList(sources));
        }
    }

    public void addSource(ConfigurationSource source) {
        sources.add(source);
    }

    public List<ConfigurationSource> getSources() {
        return Collections.unmodifiableList(sources);
    }


    @Override
    public boolean containsKey(String key) {
        for (ConfigurationSource source : sources) {
            if (source.containsKey(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object getValue(String key) {
        for (ConfigurationSource source : sources) {
            if (source.containsKey(key)) {
                return source.getValue(key);
            }
        }

        return null;
    }
}
