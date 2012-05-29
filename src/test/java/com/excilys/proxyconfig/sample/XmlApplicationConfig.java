package com.excilys.proxyconfig.sample;

import com.excilys.proxyconfig.annotations.ConfigurationKey;
import com.excilys.proxyconfig.annotations.Prefix;

@Prefix("com.myapp.xml.")
public interface XmlApplicationConfig {

    public String getProperty1();

    public Integer getProperty2();

    public float getProperty3();

    @ConfigurationKey(value = "property4", fullyQualified = true)
    public String getAnotherProperty();
}