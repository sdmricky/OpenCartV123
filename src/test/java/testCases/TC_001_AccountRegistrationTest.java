package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{

	@Test(groups= {"Regression","Master"})
	public void verifyAccountRegistration() throws InterruptedException
	{
		try
		{
			logger.info("****** Starting TC_001_AccounRegistrationTest *******");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on 'My Account' link");
			hp.clickRegister();
			logger.info("Clicked on 'Register' link");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details.....");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomAlphaNumeric()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String password = randomAlphaNumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			
			logger.info("Validating expected message......");
			String confirmationmsg = regpage.getConfirmationMsg();
			if(confirmationmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed.....");
				logger.debug("Debug logs......");
				Assert.assertTrue(false);
			}
//			Assert.assertEquals(confirmationmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished TC_001_AccounRegistrationTest *******");
	}
}