package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public  static String getConfig(String key) throws IOException {
        Properties properties=new Properties();

        //InputStream inputStream=getClass().getClassLoader().getResourceAsStream("WEB-INF/classes/dbinformation");
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dbinformation"));
        if(properties.getProperty(key)!=null){
            return properties.getProperty(key);
        }

        return null;
    }
}
