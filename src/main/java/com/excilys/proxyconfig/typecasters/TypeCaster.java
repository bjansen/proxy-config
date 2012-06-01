package com.excilys.proxyconfig.typecasters;

public interface TypeCaster<T> {

    /**
     * Checks whether this type caster can cast an object to the type <code>targetType</code>.
     * If this target type is supported, a call to this method will likely be followed by a call to {@link TypeCaster#cast(Object, Class)}.
     *
     * @param obj the value to be casted
     * @param targetType the target type of the cast
     * @return true if the given <code>obj</code> can be casted to the type <code>targetType</code>, false otherwise.
     */
    public boolean accepts(Object obj, Class<?> targetType);

    /**
     * Casts the given object to the specified target type. Calls to this method should preferably be preceded by a call to
     * {@link TypeCaster#accepts(Object, Class)} to ensure that the target type is supported by this <code>TypeCaster</code>.
     *
     *
     * @param obj the value to be casted
     * @param targetType the target type of the cast
     * @return an representation of <code>obj</code> casted to the specified type <code>targetType</code>
     */
    public T cast(Object obj, Class<T> targetType);
}
