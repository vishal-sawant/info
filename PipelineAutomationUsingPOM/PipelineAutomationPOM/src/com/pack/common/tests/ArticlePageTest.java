package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.pack.base.LaunchBrowser;
import com.pack.common.pageobjects.ArticlePage;
import com.pack.common.pageobjects.HomePage;
import com.pack.common.pageobjects.LoginPage;

public class ArticlePageTest extends LaunchBrowser {
	
	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ArticlePage articlePage;
	
	@Test
	public void verifyArticleID() throws Exception{
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn();
		String homePgArticleId = homePage.getIdOfArticlePresentOnHomePg();
		articlePage = homePage.clickOnArticle();
		String articleId = articlePage.getArticleID();
		System.out.println("Article ID on Home page: " +homePgArticleId);
		System.out.println("Article ID in URL: " +articleId);
		Assert.assertTrue(homePgArticleId.equals(articleId), "Article ID showing in the Article link does not match with ID showing on the Article page");
		logger.info("Test case passed: verify article id showing in the url");
		Reporter.log("Test case passed: verify article id showing in the url");
		
		Assert.assertTrue(articlePage.verifySectionsOnArticlePage(), "Required article sections not matching");
		logger.info("Test case passed: verify required sections displayed on an article page");
		Reporter.log("Test case passed: verify required sections displayed on an article page");
	}

}
