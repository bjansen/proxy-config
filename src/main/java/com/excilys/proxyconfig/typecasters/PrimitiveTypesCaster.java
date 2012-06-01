package com.excilys.proxyconfig.typecasters;

/**
 * A collection of type casters for primitive types and their wrapper classes.
 *
 * @author bjansen
 * @since 1.0
 */
public enum PrimitiveTypesCaster implements TypeCaster<Object> {

    BYTE(Byte.class, Byte.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Byte.valueOf(obj.toString());
        }
    },
    SHORT(Short.class, Short.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Short.valueOf(obj.toString());
        }
    },
    INT(Integer.class, Integer.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Integer.valueOf(obj.toString());
        }
    },
    LONG(Long.class, Long.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Long.valueOf(obj.toString());
        }
    },
    FLOAT(Float.class, Float.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Float.valueOf(obj.toString());
        }
    },
    DOUBLE(Double.class, Double.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Double.valueOf(obj.toString());
        }
    },
    BOOLEAN(Double.class, Double.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Boolean.valueOf(obj.toString());
        }
    },
    CHAR(Character.class, Character.TYPE) {
        @Override
        @SuppressWarnings("unchecked")
        public Object cast(Object obj, Class<Object> targetType) {
            return Character.valueOf(obj.toString().charAt(0));
        }
    };

    private final Class[] acceptedTypes;

    PrimitiveTypesCaster(Class... acceptedTypes) {
        this.acceptedTypes = acceptedTypes;
    }

    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        for (Class acceptedType : acceptedTypes) {
            if (targetType.isAssignableFrom(acceptedType)) {
                return true;
            }
        }
        return false;
    }
}
