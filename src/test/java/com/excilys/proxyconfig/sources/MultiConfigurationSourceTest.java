package com.excilys.proxyconfig.sources;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MultiConfigurationSourceTest {

    @Test
    public void multipleSourcesAreSupported() throws IOException {
        MultiConfigurationSource multiSource = new MultiConfigurationSource(
                new PropertiesConfigurationSource("configuration/app-config.properties"),
                new PropertiesConfigurationSource("configuration/app-config.xml")
        );

        assertEquals(2, multiSource.getSources().size());

        assertFalse(multiSource.containsKey("hell.no"));
        assertNull(multiSource.getValue("hell.no"));

        assertTrue(multiSource.containsKey("app.invalid.configuration.key"));
        assertEquals("Huh?", multiSource.getValue("app.invalid.configuration.key"));

        assertTrue(multiSource.containsKey("com.myapp.xml.property3"));
        assertEquals("13.37", multiSource.getValue("com.myapp.xml.property3"));
    }

    @Test
    public void noSourceWorksWithoutError() {
        MultiConfigurationSource multiSource = new MultiConfigurationSource();

        assertEquals(0, multiSource.getSources().size());
    }

    @Test
    public void newSourcesAreTakenDynamically() throws IOException {
        MultiConfigurationSource source = new MultiConfigurationSource();

        assertFalse(source.containsKey("com.myapp.xml.property3"));

        source.addSource(new PropertiesConfigurationSource("configuration/app-config.xml"));

        assertTrue(source.containsKey("com.myapp.xml.property3"));
    }
}
