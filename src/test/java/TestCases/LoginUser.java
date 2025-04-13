package TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import utils.BaseClass;
import utils.ConfigReader;
import utils.ExcelUtils;
import utils.WaitUtils;

public class LoginUser extends BaseClass {
	private static final Logger log = LogManager.getLogger(LoginUser.class);
	static WebDriver driver;
	
	WaitUtils waitUtils;
	ExcelUtils excelUtils;
	HomePage homePage;
	
	@Test(priority=1)
	public void loginUser() throws IOException {
		
		driver = BaseClass.getDriver();
		
		driver.get(ConfigReader.getUrl());
		
		homePage = new HomePage(driver);
		
		driver.findElement(HomePage.myAccount).click();
		log.info("User navigates to My Account");
		
		waitUtils =  new WaitUtils(driver);
		waitUtils.waitForElementClickable(HomePage.loginMenu,10);
		driver.findElement(HomePage.loginMenu).click();
		log.info("User clicks on Login Menu");
		
		excelUtils = new ExcelUtils();
		String email=excelUtils.getCellData("register", "EMail");
		driver.findElement(HomePage.emailField).sendKeys(email);
		log.info("User enters Email address as : " + email);
		
		String password=excelUtils.getCellData("register", "Password");
		driver.findElement(HomePage.passwordField).sendKeys(password);
		log.info("User enters Password");
		
		driver.findElement(HomePage.loginButton).click();
		log.info("User clicks on Login Button");
		
		String logoutText=driver.findElement(HomePage.logOutButton).getText();
		Assert.assertEquals(logoutText, "Logout");
		log.info("User is Logged in!!!");
	}

}
