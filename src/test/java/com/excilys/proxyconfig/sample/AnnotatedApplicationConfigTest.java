package com.excilys.proxyconfig.sample;

import com.excilys.proxyconfig.ConfigurationFactory;
import com.excilys.proxyconfig.sources.PropertiesConfigurationSource;
import com.excilys.proxyconfig.transformers.DefaultMethodNameTransformers;
import com.excilys.proxyconfig.typecasters.MessageFormatTypeCaster;
import com.excilys.proxyconfig.typecasters.PrimitiveTypesCaster;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnnotatedApplicationConfigTest {

    private AnnotatedApplicationConfig configuration;

    @Before
    public void setup() throws IOException {
        ConfigurationFactory.setMethodNameTransformers(DefaultMethodNameTransformers.CAMEL_CASE_TO_DOTS);
        ConfigurationFactory.setConfigurationSource(new PropertiesConfigurationSource("configuration/app-config.properties"));
        ConfigurationFactory.setTypeCasters(PrimitiveTypesCaster.values());
        ConfigurationFactory.addTypeCaster(new MessageFormatTypeCaster());
        configuration = ConfigurationFactory.getConfiguration(AnnotatedApplicationConfig.class);
    }

    @Test
    public void prefixAnnotationAtClassLevel() {
        assertEquals("baz powa", configuration.getPrefixedValue());
    }

    @Test
    public void prefixAnnotationArMethodLevelOverridesClassLevel() {
        assertEquals("foo forever", configuration.getMethodPrefixedValue());
    }

    @Test
    public void forcedConfigurationKeyOverridesMethodName() {
        assertEquals("Haha, you guessed me!", configuration.getConfigurationByGuess());
    }

    @Test(expected = IllegalStateException.class)
    public void validMethodNameButInvalidConfigurationKey() {
        configuration.getInvalidConfigurationKey();
    }

    @Test
    public void fullyQualifiedConfigurationKey() {
        assertEquals("Wait for it... dary!", configuration.getFullyQualifiedConfigurationKey());
    }

    @Test
    public void defaultValue() {
        assertEquals(-3, configuration.getNonExistingKey());
    }

    @Test
    public void defaultValueIsNull() {
        assertNull(configuration.getPropertyDefaultsToNull());
    }

    @Test(expected = IllegalStateException.class)
    public void primitiveDoesNotDefaultToNull() {
        configuration.getPrimitivePropertyDefaultsToNull();
    }
}
