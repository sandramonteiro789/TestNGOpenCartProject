package TestCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AddCartPage;
import utils.BaseClass;
import utils.ConfigReader;
import utils.CustomException;
import utils.ExcelUtils;
import utils.WaitUtils;

public class AddToCart extends BaseClass {

	private static final Logger log = LogManager.getLogger(AddToCart.class);
	static WebDriver driver;
	
	WaitUtils waitUtils;
	ExcelUtils excelUtils;
	AddCartPage addCartPage;
	LoginUser loginUser;
	
	@Test(priority=1)
	public void addMacBookToCart() throws IOException, CustomException {
		
		driver = BaseClass.getDriver();
		
		driver.get(ConfigReader.getUrl());
		
		excelUtils = new ExcelUtils();
		addCartPage = new AddCartPage(driver);
		
		loginUser = new LoginUser();
		loginUser.loginUser();
		String itemToAdd=excelUtils.getCellData("AddToCart", "Search Item");
		addCartPage.enterItemToSearch(itemToAdd);
		log.info("User enter the item to search");
		log.info("User searched for : "+itemToAdd);
		
		addCartPage.clickSearch();
		log.info("User clicks on search button");
		
		addCartPage.clickMacBook();
		log.info("User selects "+itemToAdd+" from search results");
		
		if(addCartPage.getItemAvailability().equals("Availability: In Stock")) {
			
			addCartPage.clickAddToCartButton();
			log.info("User Adds "+itemToAdd+" to cart");
			
			addCartPage.clickShopppingCartButton();
			log.info("User goes to cart");
			
			Assert.assertEquals(addCartPage.verifyMacBookInCart(), "MacBook");
			log.info(addCartPage.verifyMacBookInCart()+" is present in cart");
			
			addCartPage.clickCheckOutButton();
			
		}else {
			throw new CustomException("Item Out Of Stock ");
		}
	}
}
