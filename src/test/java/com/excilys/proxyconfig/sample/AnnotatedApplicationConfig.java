package com.excilys.proxyconfig.sample;

import com.excilys.proxyconfig.annotations.ConfigurationKey;
import com.excilys.proxyconfig.annotations.Default;
import com.excilys.proxyconfig.annotations.Prefix;

@Prefix("app.")
public interface AnnotatedApplicationConfig {

    public String getPrefixedValue();

    @Prefix("app.overriden.")
    public String getMethodPrefixedValue();

    @ConfigurationKey("try.to.guess.this")
    public String getConfigurationByGuess();

    @ConfigurationKey("you.will.not.find.me")
    public String getInvalidConfigurationKey();

    @ConfigurationKey(value = "another.app.configuration.key", fullyQualified = true)
    public String getFullyQualifiedConfigurationKey();

    @Default("-3")
    public int getNonExistingKey();

    @Default(Default.NULL)
    public String getPropertyDefaultsToNull();

    @Default(Default.NULL)
    public int getPrimitivePropertyDefaultsToNull();
}
