package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.internal.InvocationContext;
import com.excilys.proxyconfig.internal.InvocationContextAware;

import java.text.MessageFormat;

/**
 * TODO
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
