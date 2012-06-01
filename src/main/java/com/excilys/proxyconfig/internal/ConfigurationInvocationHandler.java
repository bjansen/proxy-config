package com.excilys.proxyconfig.internal;

import com.excilys.proxyconfig.annotations.ConfigurationKey;
import com.excilys.proxyconfig.annotations.Default;
import com.excilys.proxyconfig.annotations.Prefix;
import com.excilys.proxyconfig.exceptions.TypeException;
import com.excilys.proxyconfig.sources.ConfigurationSource;
import com.excilys.proxyconfig.transformers.DefaultMethodNameTransformers;
import com.excilys.proxyconfig.transformers.MethodNameTransformer;
import com.excilys.proxyconfig.typecasters.CompositeTypeCaster;
import com.excilys.proxyconfig.typecasters.PrimitiveTypesCaster;
import com.excilys.proxyconfig.typecasters.TypeCaster;

import java.lang.reflect.*;
import java.text.MessageFormat;
import java.util.*;

public class ConfigurationInvocationHandler implements InvocationHandler {

    private static final String METHOD_PREFIX = "get";
    private final ConfigurationSource configurationSource;
    private final List<TypeCaster> typeCasters = new ArrayList<TypeCaster>();
    private List<MethodNameTransformer> transformers;

    public ConfigurationInvocationHandler(ConfigurationSource configurationSource) {
        this(configurationSource, null);
    }

    public ConfigurationInvocationHandler(ConfigurationSource configurationSource, List<TypeCaster> typeCasters) {
        this.configurationSource = configurationSource;

        if (typeCasters == null || typeCasters.isEmpty()) {
            this.typeCasters.addAll(Arrays.asList(PrimitiveTypesCaster.values()));
        } else {
            this.typeCasters.addAll(typeCasters);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getReturnType().equals(Void.TYPE)) {
            return null;
        }

        String methodName = method.getName();

        String prefix = findKeyPrefix(method);
        String configurationKey = findConfigurationKey(method, prefix);

        if (configurationKey != null) {
            if (configurationSource.containsKey(configurationKey)) {
                return castReturnValue(configurationKey, configurationSource.getValue(configurationKey), method, args);
            }
        } else if (methodName.startsWith(METHOD_PREFIX)) {
            String rawKey = methodName.substring(METHOD_PREFIX.length());
            for (MethodNameTransformer transformer : getTransformers()) {
                String candidateKey = prefix + transformer.transform(rawKey);

                if (configurationSource.containsKey(candidateKey)) {
                    return castReturnValue(candidateKey, configurationSource.getValue(candidateKey), method, args);
                }
            }
        } else {
            // We don't fail since we assume this method is not meant to retrieve any configuration (not a getter, no annotation).
            return null;
        }

        String defaultValue = findDefaultValue(method);

        if (defaultValue != null) {
            return castDefaultValue(defaultValue, method, args);
        }

        throw new IllegalStateException("No configuration matches getter " + method.toString() + ". Please check your configuration");
    }

    private String findKeyPrefix(Method method) {
        Prefix prefixAnnotation = method.getAnnotation(Prefix.class);
        String prefix = "";

        if (prefixAnnotation == null) {
            prefixAnnotation = method.getDeclaringClass().getAnnotation(Prefix.class);
        }

        if (prefixAnnotation != null) {
            prefix = prefixAnnotation.value();
        }

        return prefix;
    }

    private String findConfigurationKey(Method method, String prefix) {
        ConfigurationKey configurationKey = method.getAnnotation(ConfigurationKey.class);

        if (configurationKey != null) {
            return configurationKey.fullyQualified() ? configurationKey.value() : prefix + configurationKey.value();
        }

        return null;
    }

    private Object castReturnValue(String keyName, Object value, Method method, Object[] args) {
        InvocationContext context = new InvocationContext(keyName, value, method, args, typeCasters);

        return context.cast(value, method.getReturnType());
    }

    private String findDefaultValue(Method method) {
        if (method.isAnnotationPresent(Default.class)) {
            return method.getAnnotation(Default.class).value();
        }

        return null;
    }

    private Object castDefaultValue(String defaultValue, Method method, Object[] args) {
        if (Default.NULL.equals(defaultValue)) {
            if (method.getReturnType().isPrimitive()) {
                throw new IllegalStateException("@Default(Default.NULL) is not allowed for primitive return type of method " + method.toString());
            }
            return null;
        } else {
            return castReturnValue(null, defaultValue, method, args);
        }
    }

    public void setTransformers(List<MethodNameTransformer> transformers) {
        this.transformers = transformers;
    }

    private List<MethodNameTransformer> getTransformers() {
        if (transformers == null) {
            transformers = new ArrayList<MethodNameTransformer>();
            transformers.add(DefaultMethodNameTransformers.NO_TRANSFORM);
        }
        return transformers;
    }
}
