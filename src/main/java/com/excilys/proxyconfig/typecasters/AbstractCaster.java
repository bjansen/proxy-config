package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.annotations.Separator;

import java.lang.reflect.Method;

/**
 * Provides convenient methods that are shared by multiple type casters.
 *
 * @author bjansen
 * @since 1.0
 */
public abstract class AbstractCaster<T> implements TypeCaster<T> {

    protected String getSeparator(Method method) {
        Separator annotation = method.getAnnotation(Separator.class);

        if (annotation == null) {
            annotation = method.getDeclaringClass().getAnnotation(Separator.class);
        }
        if (annotation == null) {
            return Separator.DEFAULT_SEPARATOR;
        }

        return annotation.regexp();
    }
}
