package TestCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
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

public class RegisterUser extends BaseClass {
	
	private static final Logger log = LogManager.getLogger(RegisterUser.class);
	static WebDriver driver;
	
	WaitUtils waitUtils;
	ExcelUtils excelUtils;
	HomePage homePage;
	
	@Test(priority=1)
	public void registerUser() throws IOException {
		
		driver = BaseClass.getDriver();
		
		driver.get(ConfigReader.getUrl());
		
		homePage = new HomePage(driver);
		
		driver.findElement(HomePage.myAccount).click();
		log.info("User navigates to My Account");
		
		waitUtils =  new WaitUtils(driver);
		waitUtils.waitForElementClickable(HomePage.registerButton,10);
		driver.findElement(HomePage.registerButton).click();
		log.info("User clicks on Register Button");
		
		excelUtils = new ExcelUtils();
		waitUtils.waitForElementClickable(HomePage.firstNameField,10);
		String firstName=excelUtils.getCellData("register", "First Name");
		driver.findElement(HomePage.firstNameField).sendKeys(firstName);
		log.info("User enters First Name as : "+firstName);

		String lastName=excelUtils.getCellData("register", "Last Name");
		driver.findElement(HomePage.lastNameField).sendKeys(lastName);
		log.info("User enters Last Name as : "+lastName);
		
		String randomString = RandomStringUtils.randomAlphabetic(5);
		String email= "user" + randomString + "@example.com";
		driver.findElement(HomePage.emailField).sendKeys(email);
		log.info("User enters Email address as : " + email);
		excelUtils.setCellData("register", "EMail", email);
		
		String telephone=excelUtils.getCellData("register", "Telephone");
		driver.findElement(HomePage.telephoneField).sendKeys(telephone);
		log.info("User enters Telephone as : "+telephone);
		
		String password=excelUtils.getCellData("register", "Password");
		driver.findElement(HomePage.passwordField).sendKeys(password);
		log.info("User enters Password");

		driver.findElement(HomePage.confirmPasswordField).sendKeys(password);
		log.info("User enters Password again");
		
		if(!driver.findElement(HomePage.agreeCheckBox).isSelected()) {
			driver.findElement(HomePage.agreeCheckBox).click();
			log.info("User clicks on Agree check Box");
		}
		
		driver.findElement(HomePage.continueButton).click();
		log.info("User clicks on Continue Button");
		
		waitUtils.waitForElementVisibility(HomePage.successMessage, 10);
		String actualSuccess = driver.findElement(HomePage.successMessage).getText();
		Assert.assertEquals(actualSuccess , "Your Account Has Been Created!" , "Account was not Created");
	}
}
