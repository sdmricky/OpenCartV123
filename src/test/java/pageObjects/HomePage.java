package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//creating constructor
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//span[text()='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//ul[contains(@class, 'dropdown')]//a[text()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//ul[contains(@class, 'menu-right')]//a[text()='Login']") WebElement lnkLogin;
	
	//Action methods
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
}