package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.internal.InvocationContext;
import com.excilys.proxyconfig.internal.InvocationContextAware;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * TODO
 */
public class ArrayTypeCaster implements TypeCaster<Object[]>, InvocationContextAware {
    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        return targetType.isArray();
    }

    @Override
    public Object[] cast(Object obj, Class<Object[]> targetType) {
        InvocationContext context = (InvocationContext) obj;
        Method method = context.getMethod();

        String[] parts = context.getValue().toString().split(",");
        Object result = Array.newInstance(method.getReturnType().getComponentType(), parts.length);

        for (int i = 0; i < parts.length; i++) {
            Object arrayValue;

            if (method.getReturnType().getComponentType().equals(String.class)) {
                arrayValue = parts[i].toString();
            } else {
                arrayValue = context.cast(parts[i], method.getReturnType().getComponentType());
            }
            Array.set(result, i, arrayValue);
        }

        return (Object[]) result;
    }
}