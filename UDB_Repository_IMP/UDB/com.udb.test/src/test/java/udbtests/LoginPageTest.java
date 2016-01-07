package udbtests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import base.LaunchBrowser;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class LoginPageTest extends LaunchBrowser {
	private LoginPage loginPage;
	private HomePage homePage;

	/*@BeforeClass
	public void setUp() {
		driver = getDriver();
	}*/

	@Test (priority=1)
	public void verifyUDBLogin() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn(logger);
		Assert.assertTrue(homePage.getPageTitle().contains("UDB"), "Home page title does not match");
		logger.info("Test case passed: verify user able to login into UDB application and UDB home page displayed");
		Reporter.log("Test case passed: verify user able to login into UDB application and UDB home page displayed");			
	}
	
	@Test (priority=2)
	public void verifyInvalidUDBLogin() {
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.checkInvalidLogin(logger), "Test case failed: invalid UDB login");
		logger.info("Test case passed: verify user not able to login into UDB application using invalid credentials");
		Reporter.log("Test case passed: verify user not able to login into UDB application using invalid credentials");		
	}

}
