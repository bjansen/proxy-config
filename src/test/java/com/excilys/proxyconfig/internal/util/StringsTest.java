package com.excilys.proxyconfig.internal.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringsTest {

    @Test
    public void uncapFirst() {
        assertEquals("uncapped", Strings.uncapFirst("Uncapped"));
        assertEquals("uncappED", Strings.uncapFirst("UncappED"));

        assertEquals("a", Strings.uncapFirst("A"));
        assertEquals("a", Strings.uncapFirst("a"));

        assertEquals("", Strings.uncapFirst(""));
        assertEquals("", Strings.uncapFirst(null));
    }

    @Test
    public void camelCaseToDots() {
        assertEquals("", Strings.camelCaseToDots(null));
        assertEquals("", Strings.camelCaseToDots(""));

        assertEquals("a", Strings.camelCaseToDots("a"));
        assertEquals("abcd", Strings.camelCaseToDots("abcd"));

        assertEquals("a.bcd", Strings.camelCaseToDots("aBcd"));
        assertEquals("a.b.c.d", Strings.camelCaseToDots("ABCD"));

        assertEquals("look.how.it.works", Strings.camelCaseToDots("LookHowItWorks"));
    }
}
