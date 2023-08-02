package ConfigReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigReader {
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new FileNotFoundException("Property file not found");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRunMode() {
        return properties.getProperty("runMode");
    }

    public static String getRemoteUrl() {
        return properties.getProperty("remoteWebDriverUrl");
    }

    public static String getBrowserType() {
        return properties.getProperty("browserType");
    }

}