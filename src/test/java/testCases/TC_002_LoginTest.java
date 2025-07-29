package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("************Starting TC_002_LoginTest***********");
		
		try
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on 'My Account' link");
			hp.clickLogin();
			logger.info("Clicked on 'Login' link");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Providing user credentials.....");
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			MyAccountPage accpage = new MyAccountPage(driver);
			boolean targetPage = accpage.isMyAccountDisplayed();
			
			Assert.assertTrue(targetPage); //Assert.assertEquals(targetPage, true, "Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("********Finished TC_002_LoginTest********");
	}
}