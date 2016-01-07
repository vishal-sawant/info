package com.pack.common.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pack.base.LaunchBrowser;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.LoginPage;

public class LoginPageTest extends LaunchBrowser {
	private LoginPage loginPage;
	private HomePage homePage;

	/*@BeforeClass
	public void setUp() {
		driver = getDriver();
	}*/

	@Test
	public void verifyPipelineLoginFunction() throws InterruptedException {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn();
		logger.info("Test case passed: verify user able to login into pipeline application");
		Reporter.log("Test case passed: verify user able to login into pipeline application");
		Assert.assertTrue(homePage.getPageTitle().contains("TODAY'S DEALS"), "Home page title does not match");		
	}

}
