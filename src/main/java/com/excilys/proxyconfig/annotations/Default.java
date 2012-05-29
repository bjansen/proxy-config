package com.excilys.proxyconfig.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {

    public String value() default NULL;

    // Makes sure we won't ever find the same String in a ConfigurationSource :)
    public static final String NULL = "dzoeifoe-zq%ngozengeo&qzingo*zqiegno^izeubgizeubg";
}
