package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import utils.BaseClass;
import utils.ConfigReader;

public class SimpleTest extends BaseClass {
	
	private static final Logger log = LogManager.getLogger(SimpleTest.class);
	static WebDriver driver;
	
	@Test(priority=1)
	public void firstTest() {
		
		driver = BaseClass.getDriver();
		
		driver.get(ConfigReader.getUrl());
		
		log.info("first test");
		
		log.trace("This is a TRACE message");
		log.debug("This is a DEBUG message");
		log.info("This is an INFO message");
		log.warn("This is a WARN message");
		log.error("This is an ERROR message");
	}
	
	@Test(priority=2)
	public void secondTest() {
		log.info("second test");
	}

}
