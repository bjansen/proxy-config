package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.internal.InvocationContext;
import com.excilys.proxyconfig.internal.InvocationContextAware;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ListTypeCaster implements TypeCaster<List>, InvocationContextAware {

    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        return targetType.equals(List.class);
    }

    @Override
    public List cast(Object obj, Class<List> targetType) {
        InvocationContext context = (InvocationContext) obj;

        String[] parts = context.getValue().toString().split("\\s*,\\s*");
        List<Object> result = new ArrayList<Object>();

        for (int i = 0; i < parts.length; i++) {
            Class<?> parameterType = findParameterType(context.getMethod());

            result.add(context.cast(parts[i], parameterType));
        }

        return result;
    }

    private Class<?> findParameterType(Method method) {
        ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();

        return (Class<?>) returnType.getActualTypeArguments()[0];
    }
}
