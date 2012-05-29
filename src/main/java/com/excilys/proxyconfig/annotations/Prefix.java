package com.excilys.proxyconfig.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A String prefix that will be added to each key name before looking it up in the configuration.
 * If used on both an interface and a method, the prefix at method-level overrides the one at class-level.
 *
 * @author bjansen
 * @since 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {
    public String value();
}
