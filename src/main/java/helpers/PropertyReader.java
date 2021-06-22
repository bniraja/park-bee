package helpers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final String PROPERTY_FILE_NAME = "src/main/resources/environments.properties";

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        FileReader input = null;
        try {
            input = new FileReader(PROPERTY_FILE_NAME);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }
}


