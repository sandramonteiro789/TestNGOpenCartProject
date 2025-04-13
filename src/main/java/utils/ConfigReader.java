package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
	
	private static final Logger log = LogManager.getLogger(ConfigReader.class);

	private static Properties properties = new Properties();

	static {
		try (FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getBrowser() {
		log.info("Browser = "+properties.getProperty("browser"));
		return properties.getProperty("browser");
	}
	
	public static String getUrl() {
		log.info("URL = "+properties.getProperty("url"));
		return properties.getProperty("url");
	}
}
