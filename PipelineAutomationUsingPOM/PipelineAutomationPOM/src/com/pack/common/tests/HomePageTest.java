package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pack.base.LaunchBrowser;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.LoginPage;

public class HomePageTest extends LaunchBrowser {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;

	@Test(priority = 1)
	public void verifyHomePageElements() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn();

		/*Assert.assertTrue(homePage.clickInitialPopup(),
				"Some problem with initial help popup displayed on the Home page");
		logger.info("Test case passed: verify help pop up displayed on Pipeline home page and working fine");
		Reporter.log("Test case passed: verify help pop up displayed on Pipeline home page and working fine");*/

		Assert.assertTrue(homePage.verifyTextOnHomePage(),
				"Text 'Today's Deal' does not display on Pipeline Hompe page");
		logger.info("Test case passed: verify text 'TODAY'S DEALS' present on Pipeline home page");
		Reporter.log("Test case passed: verify text 'TODAY'S DEALS' present on Pipeline home page");

		Assert.assertTrue(homePage.verifyTodaysDateDisplayedOnHomePage(),
				"Today's date displayed on the home page does not match with system date");
		logger.info("Test case passed: verify today's date displayed on the Pipeline home page");
		Reporter.log("Test case passed: verify today's date displayed on the Pipeline home page");

		try {
			Assert.assertTrue(homePage.verifySectionsOnPipelineHomepage(),
					"Unable to find specified sections on Pipeline Home page");
			logger.info("Test case passed: verify QVM, Exclusive video, Deal Dashboard, Rumor Mill sections displayed on Pipeline home page");
			Reporter.log("Test case passed: verify QVM, Exclusive video, Deal Dashboard, Rumor Mill sections displayed on Pipeline home page");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Assert.assertTrue(homePage.verifyPageNavigation(),
				"page navigation functionality does not work properly");

		logger.info("Test case passed: verify page navigation works properly, verify user able to navigate to different pages by changing the page id in URL");
		Reporter.log("Test case passed: verify page navigation works properly, verify user able to navigate to different pages by changing the page id in URL");
		
		Assert.assertTrue(homePage.verifyArbitrageSituationsChartPageIsDisplayed(),
				"Arbitrage Situations Chart page is empty or there is some error");
		logger.info("Test case passed: verify Arbitrage Situations Chart page displayed and it's not empty");
		Reporter.log("Test case passed: verify Arbitrage Situations Chart page displayed and it's not empty");
		
		Assert.assertTrue(homePage.verifyInvestmentBankingPageIsDisplayed(),
				"Investment Banking page is not displayed or there is some error");
		logger.info("Test case passed: verify Investment Banking page displayed and user can see related deals or articles");
		Reporter.log("Test case passed: verify Investment Banking page displayed and user can see related deals or articles");
		
		Assert.assertTrue(homePage.verifyPrivateEquityScreenerPageIsDisplayed(),
				"Private Equity Screener page is not displayed or there is some error");
		logger.info("Test case passed: verify Private Equity Screener page displayed and user can see related data by clicking on alphabet link from 'Search Firms Alphabetically' section");
		Reporter.log("Test case passed: verify Private Equity Screener page displayed and user can see related data by clicking on alphabet link from 'Search Firms Alphabetically' section");
		
	}
}
