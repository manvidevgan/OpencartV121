package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class ,groups="datadriven")
	public void verify_loginDDT(String email, String pwd,String exp)
	{
		logger.info("*** Starting TC003_LoginDDT ***" );
		
		try {
		//HomePage
				HomePage hp=new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				//Login
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				//MyAccount 
				MyAccountPage macc= new MyAccountPage(driver);	
				boolean targetPage=macc.isMyAccountPageExists();
				
				if(exp.equalsIgnoreCase("valid"))
				{
					if(targetPage==true) 
					{
						Assert.assertTrue(true);
						macc.clickLogout();
					}else
					{
						Assert.assertTrue(false);
					}
				}
				if(exp.equalsIgnoreCase("Invalid")) { //data invaild
					if(targetPage==true) { //login successfull suppose
						macc.clickLogout(); //logout
						Assert.assertTrue(false);
					}else {
						Assert.assertTrue(true);
					}
				}
		}catch(Exception e) {
			Assert.fail();
		}
				
				logger.info("*** Finish TC003_LoginDDT ***" );
	}
	
}
