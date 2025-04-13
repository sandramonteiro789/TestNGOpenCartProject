package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public static By myAccount = By.xpath("//span[text()='My Account']");
    public static By registerButton = By.xpath("//a[text()='Register']");
    public static By firstNameField = By.xpath("//input[@name='firstname']");
    public static By lastNameField = By.xpath("//input[@name='lastname']");
    public static By emailField = By.xpath("//input[@name='email']");
    public static By telephoneField = By.xpath("//input[@name='telephone']");
    public static By passwordField = By.xpath("//input[@name='password']");
    public static By confirmPasswordField = By.xpath("//input[@name='confirm']");
    public static By agreeCheckBox = By.xpath("//input[@name='agree']");
    public static By continueButton = By.xpath("//input[@value='Continue']");
    public static By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");
    
    public static By loginMenu = By.xpath("//li/a[text()='Login']");
    public static By loginButton = By.xpath("//input[@value='Login']");
    public static By logOutButton = By.xpath("//div/a[text()='Logout']");
  

}
