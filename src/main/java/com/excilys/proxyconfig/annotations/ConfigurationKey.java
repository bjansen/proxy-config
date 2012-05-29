package com.excilys.proxyconfig.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that force the use of a specific key name instead of deriving it from the method name.
 * This annotation can be used in conjunction with @{@link Prefix} unless <code>fullyQualified</code> is set to true.
 *
 * @author bjansen
 * @since 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurationKey {

    /** The forced key name */
    public String value();

    /** Whether the key name is fully qualified, or can be use along with @{@link Prefix} */
    public boolean fullyQualified() default false;
}
