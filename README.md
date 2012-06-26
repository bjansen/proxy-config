proxy-config
============

Type-safe configurations based on interfaces

Why is it so awesome for my app?
--------------------------------
Because you won't have to mess with string-only-and-not-type-safe-Properties! You only need to to represent 
your configuration as a plain old Java interface, and proxy-config will automatically bind it with virtually any
configuration source (properties files included ;-)).

First define your interface as you would like to use it in your code:

```java
public interface ApplicationConfig {

  // Standard access
  public String getApplicationName();

  // Non-String return type
  public int getApplicationVersion();

  // User-defined key name
  @ConfigurationKey("developer.names")
  @Separator(regexp = "\\s*,\\s*") // This is the default value, you don't have to use @Separator int this case
  public List<String> getDevelopers();

  // Default value if no matching key is found in the source
  @Default("1,2,3")
  public Integer[] getPreviousVersions();
}
```

Then write the according properties file (proxy-config will try to deduce the configuration keys from the method name,
minus the "get" prefix):

```properties
application.name=My superb app
application.version=4
developer.names=Foo, Bar, Baz

# Note that previous.versions is missing
```

All right, you are ready to start using proxy-config in your project!

```java
ConfigurationFactory.setMethodNameTransformers(DefaultMethodNameTransformers.CAMEL_CASE_TO_DOTS);
ConfigurationFactory.setConfigurationSource(new PropertiesConfigurationSource("app-config.properties"));
ConfigurationFactory.setTypeCasters(PrimitiveTypesCaster.values());

ApplicationConfig config = ConfigurationFactory.getConfiguration(AnnotatedApplicationConfig.class);

// Yay!
System.out.println(String.format("Running app %s", config.getApplicationName()));
```

How can I start using it?
-------------------------
The single entry point is the ConfigurationFactory. You have to configure it using the static methods 
setMethodNameTransformers, setConfigurationSource and setTypeCasters to fit your needs.

Then you only have to pass your interface's class to getConfiguration() and you are ready to rock!

proxy-config ships a default set of transformers, sources and casters:

* DefaultMethodNameTransformers (NO_TRANSFORM, LOWER_CASE, CAMEL_CASE_TO_DOTS, CAMEL_CASE_TO_UNDERSCORES, UNCAP_FIRST)
* PrimitiveTypesCaster to cast String to primitive types or their wrapper counterpart
* MessageFormatTypeCaster to format a String using MessageFormatter and the parameters passed to the interface's method
* ListTypeCaster and ArrayTypeCaster for lists and arrays of supported types
* EnumTypeCaster to cast a String to an enum's value
* ResourceBundleConfigurationSource and PropertiesConfigurationSource to load your config from a properties file

You can also create your own subclasses of ConfigurationSource, MethodNameTransformer and TypeCaster if this is not enough!

Comparison with other tools
---------------------------
A spreadsheet comparing features provided by proxy-config and similar tools is available [here](https://docs.google.com/a/excilys.com/spreadsheet/ccc?key=0AndG-zBLycfndEI4UmJTdkZOZEV3bEUwdUxRZi1raFE#gid=0)