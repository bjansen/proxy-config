package com.excilys.proxyconfig.internal;

import com.excilys.proxyconfig.typecasters.TypeCaster;

/**
 * A marker interface that indicates the implementing class can accept an instance of an InvocationContext as the
 * first parameter of the {@link TypeCaster#cast(Object, Class)} method rather than the plain value returned by the ConfigurationSource.
 *
 * @author bjansen
 * @since 1.0
 */
public interface InvocationContextAware {

}
