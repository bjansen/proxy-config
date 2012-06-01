package com.excilys.proxyconfig.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the separator that should be use to convert a single value to a multi-values type, such as an array or a List.
 *
 * @author bjansen
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Separator {
    public static final String DEFAULT_SEPARATOR = "\\s*,\\s*";

    public String regexp() default DEFAULT_SEPARATOR;
}
