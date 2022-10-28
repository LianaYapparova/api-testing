package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class UtilsMethod {

public static String getUrl(String value){
    Properties properties = new Properties();
    try {
        properties.load(UtilsMethod.class.getClassLoader().getResourceAsStream("file.properties"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    String data = properties.getProperty(value);
    try {
        data = new String(data.getBytes(), StandardCharsets.UTF_8);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return data;
}
}
