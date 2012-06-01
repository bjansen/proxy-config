package com.excilys.proxyconfig;

import com.excilys.proxyconfig.internal.ConfigurationInvocationHandler;
import com.excilys.proxyconfig.sources.ConfigurationSource;
import com.excilys.proxyconfig.transformers.MethodNameTransformer;
import com.excilys.proxyconfig.typecasters.CompositeTypeCaster;
import com.excilys.proxyconfig.typecasters.ListTypeCaster;
import com.excilys.proxyconfig.typecasters.PrimitiveTypesCaster;
import com.excilys.proxyconfig.typecasters.TypeCaster;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ConfigurationFactory {

    INSTANCE;

    private ConfigurationSource configurationSource;
    private List<MethodNameTransformer> transformers;
    private List<TypeCaster> typeCasters;

    public static <T> T getConfiguration(Class<T> configClass) {
        return INSTANCE.proxify(configClass);
    }

    public static void setMethodNameTransformers(MethodNameTransformer... transformers) {
        if (transformers == null || transformers.length == 0) {
            INSTANCE.transformers = null;
        } else {
            INSTANCE.transformers = Arrays.asList(transformers);
        }
    }

    public static void setConfigurationSource(ConfigurationSource configurationSource) {
        INSTANCE.configurationSource = configurationSource;
    }

    public static void setTypeCasters(TypeCaster... casters) {
        if (casters == null || casters.length == 0) {
            return;
        }

        INSTANCE.getTypeCasters().clear();
        INSTANCE.getTypeCasters().addAll(Arrays.asList(casters));
    }

    public static void addTypeCaster(TypeCaster listTypeCaster) {
        INSTANCE.getTypeCasters().add(listTypeCaster);
    }

    private List<TypeCaster> getTypeCasters() {
        if (typeCasters == null) {
            typeCasters = new ArrayList<TypeCaster>();
        }

        return typeCasters;
    }

    @SuppressWarnings("unchecked")
    private <T> T proxify(Class<T> configClass) {
        ConfigurationInvocationHandler configurationInvocationHandler = new ConfigurationInvocationHandler(configurationSource, typeCasters);
        configurationInvocationHandler.setTransformers(transformers);

        Object proxy = Proxy.newProxyInstance(configClass.getClassLoader(), new Class[]{configClass}, configurationInvocationHandler);

        return (T) proxy;
    }
}
