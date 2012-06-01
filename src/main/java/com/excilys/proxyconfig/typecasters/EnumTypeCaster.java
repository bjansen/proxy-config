package com.excilys.proxyconfig.typecasters;

/**
 * A type caster that allows casting a value to an enumeration element.
 *
 * @author bjansen
 * @since 1.0
 */
public class EnumTypeCaster<E extends Enum> implements TypeCaster<E> {

    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        return targetType.isEnum();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E cast(Object obj, Class<E> targetType) {
        Class<? extends Enum> enumType = targetType.asSubclass(Enum.class);

        for (Enum constant : enumType.getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(obj.toString())) {
                return (E) constant;
            }
        }

        return null;
    }
}
