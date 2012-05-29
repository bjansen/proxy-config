package com.excilys.proxyconfig;

import com.excilys.proxyconfig.exceptions.TypeException;
import com.excilys.proxyconfig.sample.ApplicationConfig;
import com.excilys.proxyconfig.sources.PropertiesConfigurationSource;
import com.excilys.proxyconfig.transformers.DefaultMethodNameTransformers;
import com.excilys.proxyconfig.typecasters.PrimitiveTypesCaster;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ConfigurationFactoryTest {

    private ApplicationConfig configuration;

    public ConfigurationFactoryTest() throws IOException {
        ConfigurationFactory.setTypeCasters(PrimitiveTypesCaster.LONG, PrimitiveTypesCaster.FLOAT);
        ConfigurationFactory.setConfigurationSource(new PropertiesConfigurationSource("configuration/app-config.properties"));
        ConfigurationFactory.setMethodNameTransformers(DefaultMethodNameTransformers.UNCAP_FIRST);
        configuration = ConfigurationFactory.getConfiguration(ApplicationConfig.class);
    }

    @Test
    public void multipleCastersAreMixed() {
        assertNotNull(configuration.getLongValue());
        assertNotNull(configuration.getFloatValue());
    }

    @Test(expected = TypeException.class)
    public void missingCasterThrowsException() {
        configuration.getNumericValue();
    }
}
