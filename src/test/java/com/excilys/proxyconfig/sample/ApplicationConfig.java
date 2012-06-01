package com.excilys.proxyconfig.sample;

import com.excilys.proxyconfig.annotations.ConfigurationKey;

import java.util.Date;
import java.util.List;

public interface ApplicationConfig {

    public String getApplicationName();

    public String getApplicationVersion();

    public String getThisPropertyIfYouCan();

    public int getNumericValue();

    public Float getFloatValue();

    public Long getLongValue();

    public Long getInvalidLongValue();

    public String getUnexpectedError(String errorCode, String message, Date when);

    public String getNonCamelCase();

    public Date getEndOfWorld();

    public String whatAmIDoingHere();

    public void getVoidReturn();

    public char getCharacter();

    public String[] getArrayOfString();

    @ConfigurationKey(value = "array.of.string", fullyQualified = true)
    public List<String> getListOfString();

    public List<Integer> getListOfInt();
}
