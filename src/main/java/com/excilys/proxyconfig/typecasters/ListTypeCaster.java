package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.internal.InvocationContext;
import com.excilys.proxyconfig.internal.InvocationContextAware;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * A type caster for {@link List}s of arbitrary objects that are supported by the InvocationContext.
 *
 * @author bjansen
 * @since 1.0
 */
public class ListTypeCaster extends AbstractCaster<List> implements InvocationContextAware {

    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        return targetType.equals(List.class);
    }

    @Override
    public List cast(Object obj, Class<List> targetType) {
        InvocationContext context = (InvocationContext) obj;

        Method method = context.getMethod();
        String[] parts = context.getValue().toString().split(getSeparator(method));
        List<Object> result = new ArrayList<Object>();

        for (int i = 0; i < parts.length; i++) {
            Class<?> parameterType = findParameterType(method);

            result.add(context.cast(parts[i], parameterType));
        }

        return result;
    }

    private Class<?> findParameterType(Method method) {
        ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();

        return (Class<?>) returnType.getActualTypeArguments()[0];
    }
}
