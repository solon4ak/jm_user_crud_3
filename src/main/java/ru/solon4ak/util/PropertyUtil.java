package ru.solon4ak.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyUtil {

    private static PropertyUtil instance;

    private PropertyUtil() {}

    public static PropertyUtil getInstance() {
        if (instance == null) {
            instance = new PropertyUtil();
        }
        return instance;
    }

    public Properties getProperties() {
        String path = "config.properties";
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties.");
            }
            //load a properties file from class path, inside static method
            prop.load(input);
//            prop.list(System.out);

        } catch (IOException ex) {
            Logger.getLogger(PropertyUtil.class.getName()).log(Level.WARNING, "Exception while loading properties.");
        }

        return prop;
    }
}
