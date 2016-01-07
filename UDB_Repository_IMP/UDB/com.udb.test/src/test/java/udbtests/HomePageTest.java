package udbtests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageobjects.CompanyHome;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import base.LaunchBrowser;

public class HomePageTest extends LaunchBrowser {
	private LoginPage loginPage;
	private HomePage homePage;
	private CompanyHome company;

	/*
	 * @BeforeClass public void setUp() { driver = getDriver(); }
	 */

	@Test
	public void verifyCompHomePgDisplayed() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn(logger);
		company = homePage.clickOnCompanyHome();
		Assert.assertTrue(company.getCompHPageText().contains("Company Id"),
				"Company home page not displayed");
		logger.info("Test case passed: verify clicking on the company tab company home page gets displayed");
		Reporter.log("Test case passed: verify clicking on the company tab company home page gets displayed");
	}
}
