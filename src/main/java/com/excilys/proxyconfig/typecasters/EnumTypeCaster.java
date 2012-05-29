package com.excilys.proxyconfig.typecasters;

/**
 * A type caster that allows casting a value to an enumeration element.
 *
 * @author bjansen
 * @since 1.0
 */
public class EnumTypeCaster implements TypeCaster {

    @Override
    public <T> boolean accepts(Object obj, Class<T> targetType) {
        return targetType.isEnum();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T cast(Object obj, Class<T> targetType) {
        Class<? extends Enum> enumType = targetType.asSubclass(Enum.class);

        for (Enum constant : enumType.getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(obj.toString())) {
                return (T) constant;
            }
        }

        return null;
    }
}
