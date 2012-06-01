package com.excilys.proxyconfig.typecasters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeTypeCaster implements TypeCaster<Object> {

    private List<TypeCaster> casters = new ArrayList<TypeCaster>();

    public CompositeTypeCaster(TypeCaster... casters) {
        this.casters.addAll(Arrays.asList(casters));
    }

    public void add(TypeCaster caster) {
        casters.add(caster);
    }

    @Override
    public boolean accepts(Object obj, Class<?> targetType) {
        for (TypeCaster caster : casters) {
            if (caster.accepts(obj, targetType)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object cast(Object obj, Class<Object> targetType) {
        for (TypeCaster caster : casters) {
            if (caster.accepts(obj, targetType)) {
                return caster.cast(obj, targetType);
            }
        }

        return null;
    }

    public static CompositeTypeCaster forPrimitiveTypes() {
        return new CompositeTypeCaster(PrimitiveTypesCaster.values());
    }
}
