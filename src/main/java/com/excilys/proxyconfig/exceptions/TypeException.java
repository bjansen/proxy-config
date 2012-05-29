package com.excilys.proxyconfig.exceptions;

/**
 * An unchecked exception indicating that a configuration could not be casted to the required type.
 * This can happen for example if the value has an invalid format, or if no TypeCaster can handle the required type.
 *
 * @author bjansen
 * @since 1.0
 */
public class TypeException extends RuntimeException {

    public TypeException() {
    }

    public TypeException(Throwable cause) {
        super(cause);
    }

    public TypeException(String message) {
        super(message);
    }

    public TypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
