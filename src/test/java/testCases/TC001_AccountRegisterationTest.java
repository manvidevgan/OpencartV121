package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountResgistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegisterationTest extends BaseClass{

	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registeration()
	{
		logger.info("**** Strating TC001_AccountRegisterationTest ****");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAcocont");
		hp.clickRegister();
		logger.info("Clicked on Register");
		AccountResgistrationPage regpage=new AccountResgistrationPage(driver);
		
		logger.info("Providing customer details...");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com"); // randomly generated the 
			regpage.setTelephone(randomNumber());
			//String password=randomAlphaNumeric();
			String pwd=randomAlphaNumberic(); 
			regpage.setPassword(pwd); 
			regpage.setConfirmPassword (pwd);
			
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
		
			
			logger.info("Validating expected message..");
			String confmsg=regpage.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}else {
				logger.error("Test Failed..");
				logger.debug("Debug logs...");
				Assert.assertTrue(false);
			}
			
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}catch(Exception e)
		{
			
			Assert.fail();
		}
	}
	
	
	
	
}
