Design a Singleton class called "ConfigurationManager" to store 
application settings. The class should have the following features:

- A method `void loadConfigurationFromFile(String filePath) throws IOException` to load 
configuration settings from a file

A sample configuration file shall be as follows:  
```
 key1=value1  
 key2=value2
```

- Methods to get and set configuration settings
`String getSetting(String key)`
`void setSetting(String key, String value)`

- A method `void saveConfigurationToFile(String filePath) throws IOException`
that stores configuration in the file as above.

Hint: Use `java.util.Properties` to implement above.