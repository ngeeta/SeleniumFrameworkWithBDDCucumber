package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import Base.BaseClass;

public class ConfigReader {
	public static Properties properties = new Properties();
	static {
		try {
			FileInputStream fs = new FileInputStream("Configuration/selenium.properties");
			properties.load(fs);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
