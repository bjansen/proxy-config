package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.internal.InvocationContext;
import com.excilys.proxyconfig.internal.InvocationContextAware;

import java.lang.reflect.Array;

/**
 * A type caster that supports one-dimension arrays of any type that can be casted by the current InvocationContext.
 * If the component type of this array is not supported, a standard {@link com.excilys.proxyconfig.exceptions.TypeException} will be thrown.
 *
 * @author bjansen
 * @since 1.0
 */
public class ArrayTypeCaster implements TypeCaster<Object[]>, InvocationContextAware {
    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        return targetType.isArray() && !targetType.getComponentType().isArray();
    }

    @Override
    public Object[] cast(Object obj, Class<Object[]> targetType) {
        InvocationContext context = (InvocationContext) obj;

        String[] parts = context.getValue().toString().split(",");
        Object result = Array.newInstance(targetType.getComponentType(), parts.length);

        for (int i = 0; i < parts.length; i++) {
            Object arrayValue;

            if (targetType.getComponentType().equals(String.class)) {
                arrayValue = parts[i].toString();
            } else {
                arrayValue = context.cast(parts[i], targetType.getComponentType());
            }
            Array.set(result, i, arrayValue);
        }

        return (Object[]) result;
    }
}