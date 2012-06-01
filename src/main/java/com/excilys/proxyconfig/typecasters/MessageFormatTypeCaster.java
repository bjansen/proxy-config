package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.internal.InvocationContext;
import com.excilys.proxyconfig.internal.InvocationContextAware;

import java.text.MessageFormat;

/**
 * A type caster that allows formatting the value using the method's parameters.
 * This implementation relies on {@link MessageFormat} so values can use its patterns.
 *
 * @author bjansen
 * @since 1.0
 * @see MessageFormat
 */
public class MessageFormatTypeCaster implements TypeCaster<String>, InvocationContextAware {
    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        return targetType.equals(String.class);
    }

    @Override
    public String cast(Object obj, Class<String> targetType) {
        InvocationContext context = (InvocationContext) obj;

        if (context.getArguments() == null) {
            return context.getValue().toString();
        } else {
            return MessageFormat.format(context.getValue().toString(), context.getArguments());
        }

    }
}
