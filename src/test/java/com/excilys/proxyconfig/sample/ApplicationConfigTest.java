package com.excilys.proxyconfig.sample;

import com.excilys.proxyconfig.exceptions.TypeException;
import com.excilys.proxyconfig.ConfigurationFactory;
import com.excilys.proxyconfig.sources.PropertiesConfigurationSource;
import com.excilys.proxyconfig.transformers.DefaultMethodNameTransformers;
import com.excilys.proxyconfig.typecasters.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ApplicationConfigTest {

    private ApplicationConfig configuration;

    @Before
    public void setup() throws IOException {
        ConfigurationFactory.setMethodNameTransformers(DefaultMethodNameTransformers.values());
        ConfigurationFactory.setConfigurationSource(new PropertiesConfigurationSource("configuration/app-config.properties"));
        ConfigurationFactory.setTypeCasters(PrimitiveTypesCaster.values());
        ConfigurationFactory.addTypeCaster(new ListTypeCaster());
        ConfigurationFactory.addTypeCaster(new ArrayTypeCaster());
        ConfigurationFactory.addTypeCaster(new MessageFormatTypeCaster());
        configuration = ConfigurationFactory.getConfiguration(ApplicationConfig.class);
    }

    @Test
    public void simpleGetReturnsValue() {
        assertEquals("Hello World application", configuration.getApplicationName());
        assertEquals("First version", configuration.getApplicationVersion());
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionIsThrownIfNoMatchingConfigIsFound() {
        configuration.getThisPropertyIfYouCan();
    }

    @Test
    public void numericValuesAreSupported() {
        assertEquals(42, configuration.getNumericValue());
        assertEquals(Float.valueOf(1.2345F), configuration.getFloatValue());
        assertEquals(Long.valueOf(1337), configuration.getLongValue());
    }

    @Test(expected = NumberFormatException.class)
    public void invalidNumericValueThrowsException() {
        configuration.getInvalidLongValue();
    }

    @Test
    public void messagesAreFormatted() throws Exception {
        Date when = new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-21");
        String expected = "[ERR.CODE.1] [2012-12-21] An unexpected error occured: the world has ended!";
        assertEquals(expected, configuration.getUnexpectedError("ERR.CODE.1", "the world has ended!", when));
    }

    @Test
    public void charAlsoWork() {
        assertEquals('b', configuration.getCharacter());
    }

    @Test
    public void camelCaseCanBeReplacedWithDots() {
        assertEquals("Yay!", configuration.getNonCamelCase());
    }

    @Test(expected = TypeException.class)
    public void keyExistsButNoTypeCasterIsConfigured() {
        configuration.getEndOfWorld();
    }

    @Test
    public void defaultTransformerIsUsedIfNotConfigured() {
        ConfigurationFactory.setMethodNameTransformers();
        configuration = ConfigurationFactory.getConfiguration(ApplicationConfig.class);
        assertEquals("Hello World application", configuration.getApplicationName());
    }

    @Test
    public void unsupportedMethodReturnsNull() {
        assertNull(configuration.whatAmIDoingHere());
    }

    @Test
    public void voidMethodsAreSilentlyIgnored() {
        configuration.getVoidReturn();
    }

    @Test
    public void arraysAreRecognized() {
        String[] expected = new String[]{"one", "two", "three"};

        assertArrayEquals(expected, configuration.getArrayOfString());
    }

    @Test
    public void listOfString() {
        String[] expected = new String[]{"one", "two", "three"};

        assertEquals(Arrays.asList(expected), configuration.getListOfString());
    }

    @Test
    public void listOfIntegers() {
        Integer[] expected = new Integer[]{1, 2, 3};

        assertEquals(Arrays.asList(expected), configuration.getListOfInt());
    }
}
