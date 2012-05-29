package com.excilys.proxyconfig.typecasters;

/**
 * A collection of type casters for primitive types and their wrapper classes.
 *
 * @author bjansen
 * @since 1.0
 */
public enum PrimitiveTypesCaster implements TypeCaster {

    BYTE(Byte.class, Byte.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Byte.valueOf(obj.toString());
        }
    },
    SHORT(Short.class, Short.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Short.valueOf(obj.toString());
        }
    },
    INT(Integer.class, Integer.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Integer.valueOf(obj.toString());
        }
    },
    LONG(Long.class, Long.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Long.valueOf(obj.toString());
        }
    },
    FLOAT(Float.class, Float.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Float.valueOf(obj.toString());
        }
    },
    DOUBLE(Double.class, Double.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Double.valueOf(obj.toString());
        }
    },
    BOOLEAN(Double.class, Double.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Boolean.valueOf(obj.toString());
        }
    },
    CHAR(Character.class, Character.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T cast(Object obj, Class<T> targetType) {
            return (T) Character.valueOf(obj.toString().charAt(0));
        }
    };

    private final Class[] acceptedTypes;

    PrimitiveTypesCaster(Class... acceptedTypes) {
        this.acceptedTypes = acceptedTypes;
    }

    @Override
    public <T> boolean accepts(Object obj, Class<T> targetType) {
        for (Class acceptedType : acceptedTypes) {
            if (targetType.isAssignableFrom(acceptedType)) {
                return true;
            }
        }
        return false;
    }
}
