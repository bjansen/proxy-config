package com.excilys.proxyconfig.internal;

import com.excilys.proxyconfig.exceptions.TypeException;
import com.excilys.proxyconfig.typecasters.TypeCaster;

import java.lang.reflect.Method;
import java.util.List;

/**
 * A context holding information related to the invocation of a method.
 *
 * @author bjansen
 * @since 1.0
 */
public class InvocationContext {

    private Method method;
    private final String keyName;
    private final Object value;
    private Object[] arguments;
    private List<TypeCaster> typeCasters;

    public InvocationContext(String keyName, Object value, Method method, Object[] arguments, List<TypeCaster> typeCasters) {
        this.keyName = keyName;
        this.value = value;
        this.arguments = arguments;
        this.method = method;
        this.typeCasters = typeCasters;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public String getKeyName() {
        return keyName;
    }

    public Method getMethod() {
        return method;
    }

    public Object getValue() {
        return value;
    }

    public Object cast(Object value, Class<?> targetClass) {
        for (TypeCaster typeCaster : typeCasters) {
            if (typeCaster.accepts(value, targetClass)) {
                if (typeCaster instanceof InvocationContextAware) {
                    value = new InvocationContext(keyName, value, method, arguments, typeCasters);
                }
                return typeCaster.cast(value, targetClass);
            }
        }

        String error = String.format("Return type of method %s cannot be casted from configuration %s (%s)",
                method.toGenericString(), keyName, value.getClass().getName());
        throw new TypeException(error);
    }
}
