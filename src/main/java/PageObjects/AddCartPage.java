package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class AddCartPage {
	
    WebDriver driver;
    WaitUtils waitUtils;

    public AddCartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
    
    private static By searchBox = By.xpath("//input[@name='search']");
    private static By searchButton = By.xpath("//div[@id='search']/descendant::button");
    private static By macBookLink = By.xpath("//a[text()='MacBook']");
    private static By availabilityText = By.xpath("//li[text()='Availability: In Stock']");
    private static By addToCartButton = By.xpath("//button[text()='Add to Cart']");
    private static By shopppingCartButton = By.xpath("//a[@title='Shopping Cart']");
    private static By macBookInCart = By.xpath("//form//a[text()='MacBook']");
    private static By checkOutButton = By.xpath("//a[text()='Checkout']");
    
    
    public void enterItemToSearch(String itemToSearch) {
        driver.findElement(searchBox).sendKeys(itemToSearch);
    }
    
    public void clickSearch() {
    	waitUtils.waitForElementClickable(searchButton, 10).click();
    }
    
    public void clickMacBook() {
    	waitUtils.waitForElementClickable(macBookLink, 10).click();
    }
    
    public String getItemAvailability() {
        return waitUtils.waitForElementVisibility(availabilityText, 10).getText();
    }
    
    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }
    
    public void clickShopppingCartButton() {
    	waitUtils.waitForElementClickable(shopppingCartButton, 10).click();
    }
    
    public String verifyMacBookInCart() {
        return waitUtils.waitForElementVisibility(macBookInCart,10).getText();
    }
    
    public void clickCheckOutButton() {
        driver.findElement(checkOutButton).click();
    }

}
