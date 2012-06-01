package com.excilys.proxyconfig.typecasters;

import com.excilys.proxyconfig.annotations.Separator;
import com.excilys.proxyconfig.internal.InvocationContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListTypeCasterTest {

    private ListTypeCaster caster = new ListTypeCaster();

    @Test
    public void simpleCast() throws Exception{
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        List<TypeCaster> casters = new ArrayList<TypeCaster>();
        casters.add(PrimitiveTypesCaster.INT);

        InvocationContext context = new InvocationContext(null, "1,2, 3", getClass().getMethod("dummyMethod"), null, casters);

        assertEquals(expected, caster.cast(context, List.class));
    }

    @Test
    public void castWithSeparatorAtTypeLevel() throws Exception{
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        List<TypeCaster> casters = new ArrayList<TypeCaster>();
        casters.add(PrimitiveTypesCaster.INT);

        InvocationContext context = new InvocationContext(null, "1 : 2 : 3", ConfigTest.class.getMethod("getListSeparatedBySemiColumn"), null, casters);

        assertEquals(expected, caster.cast(context, List.class));
    }

    @Test
    public void castWithSeparatorAtMethodLevel() throws Exception{
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        List<TypeCaster> casters = new ArrayList<TypeCaster>();
        casters.add(PrimitiveTypesCaster.INT);

        InvocationContext context = new InvocationContext(null, "1 , 2 , 3", ConfigTest.class.getMethod("getListSeparatedByComma"), null, casters);

        assertEquals(expected, caster.cast(context, List.class));
    }

    @SuppressWarnings("unused")
    public List<Integer> dummyMethod() {
        return null;
    }


    @SuppressWarnings("unused")
    @Separator(regexp = "\\s*:\\s*")
    private static interface ConfigTest {
        List<Integer> getListSeparatedBySemiColumn();

        @Separator(regexp = Separator.DEFAULT_SEPARATOR)
        List<Integer> getListSeparatedByComma();
    }
}
