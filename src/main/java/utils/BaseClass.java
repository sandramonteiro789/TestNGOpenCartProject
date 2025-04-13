package utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseClass {
	
	private static final Logger log = LogManager.getLogger(BaseClass.class);
	
	String browser = ConfigReader.getBrowser();
	
	static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@BeforeClass
	public void setUp() {
		if (browser.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
            driver.get().manage().window().maximize();
            log.info("Chrome browser is Initialized");
        } 
		
		else if (browser.equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver());
            driver.get().manage().window().maximize();
            log.info("Firefox browser is Initialized");
        } 
		
		else if (browser.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
            driver.get().manage().window().maximize();
            log.info("Edge browser is Initialized");
        } 
		
		else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
	}
	
//    @AfterClass
//    public void tearDown() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove(); 
//            log.info("Webdriver is Terminated");
//        }
//    }

    public static WebDriver getDriver() {
        return driver.get();
    }
    
    @AfterSuite(alwaysRun = true)
    public void generateAllureReport() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "allure serve target/allure-results");
            builder.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
