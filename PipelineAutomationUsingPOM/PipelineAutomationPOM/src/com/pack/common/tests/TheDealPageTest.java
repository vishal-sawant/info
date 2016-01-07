package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pack.base.LaunchBrowser;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.LoginPage;
import com.pack.common.pageobjects.TheDealPage;

public class TheDealPageTest extends LaunchBrowser{
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private TheDealPage theDealPage;
	
	@Test
	public void verifyTheDealPageIsDisplayed(){
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn();
		theDealPage = homePage.clickOnTheDealLogo();
		String expectedTitle = "M&A, private equity, hedge funds, Dealmakers: The Deal Pipelineeeeeeeeeeeee";
		String theDealPageTitle = theDealPage.getTheDealPageTitle();
		Assert.assertTrue(theDealPageTitle.equals(expectedTitle), "The Deal Page title does not match");
		logger.info("Test case passed: verify user is navigated to 'http://www.thedeal.com/' page");
		Reporter.log("Test case passed: verify user is navigated to 'http://www.thedeal.com/' page");
	}

}
