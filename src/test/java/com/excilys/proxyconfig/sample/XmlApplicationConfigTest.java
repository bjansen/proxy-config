package com.excilys.proxyconfig.sample;

import com.excilys.proxyconfig.ConfigurationFactory;
import com.excilys.proxyconfig.sources.PropertiesConfigurationSource;
import com.excilys.proxyconfig.transformers.DefaultMethodNameTransformers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class XmlApplicationConfigTest {

    private XmlApplicationConfig configuration;

    @Before
    public void setup() throws IOException {
        ConfigurationFactory.setMethodNameTransformers(DefaultMethodNameTransformers.values());
        ConfigurationFactory.setConfigurationSource(new PropertiesConfigurationSource("configuration/app-config.xml"));

        configuration = ConfigurationFactory.getConfiguration(XmlApplicationConfig.class);
    }

    @Test
    public void itWorksTheSameWayAsPropertiesFile() {
        assertEquals("bar", configuration.getProperty1());
        assertEquals(Integer.valueOf(1), configuration.getProperty2());
        assertEquals(13.37f, configuration.getProperty3(), 0.01);
        assertEquals("also works", configuration.getAnotherProperty());
    }
}
