package com.excilys.proxyconfig.typecasters;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayTypeCasterTest {

    private ArrayTypeCaster caster = new ArrayTypeCaster();

    @Test
    public void oneDimensionArraysAreSupported() {
        String[] oneDimArray = new String[]{};

        assertTrue(caster.accepts(null, oneDimArray.getClass()));
    }

    @Test
    public void multiDimensionalArraysAreNotSupported() {
        String[][] twoDimArray = new String[][]{};

        assertFalse(caster.accepts(null, twoDimArray.getClass()));
    }
}
