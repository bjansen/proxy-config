package com.excilys.proxyconfig.typecasters;

import org.junit.Test;

import java.lang.annotation.RetentionPolicy;

import static org.junit.Assert.*;

public class EnumTypeCasterTest {

    private EnumTypeCaster caster = new EnumTypeCaster();

    @Test
    public void invalidValueReturnsNull() {
        assertNull(caster.cast("WHAT", RetentionPolicy.class));
    }

    @Test
    public void validValueIgnoreCaseReturnsEnum() {
        assertEquals(RetentionPolicy.SOURCE, caster.cast("SOURCE", RetentionPolicy.class));
        assertEquals(RetentionPolicy.SOURCE, caster.cast("sOuRcE", RetentionPolicy.class));
    }

    @Test
    public void acceptsOnlyEnums() {
        assertTrue(caster.accepts(null, RetentionPolicy.class));
        assertFalse(caster.accepts(null, String.class));
    }
}
