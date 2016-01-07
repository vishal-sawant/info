package com.pack.common.pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import com.pack.base.CommonMethods;
import com.pack.base.LaunchBrowser;

public class HomePage {
	private WebDriver driver;
	protected CommonMethods commonObj = LaunchBrowser.getCommonMethodObj();

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean clickInitialPopup() {
		try {

			if (commonObj.isElementPresentAndDisplayed("popupImage1")) {
				commonObj.click("popupNextButton");
				if (commonObj.isElementPresentAndDisplayed("popupImage2"))
					commonObj.click("popupNextButton");
				if (commonObj.isElementPresentAndDisplayed("popupImage3"))
					commonObj.click("popupNextButton");
				if (commonObj.isElementPresentAndDisplayed("popupImage4"))
					commonObj.click("popupNextButton");
				if (commonObj.isElementPresentAndDisplayed("popupImage5")) {
					commonObj.click("popupNextButton");
				}
				commonObj.click("popupCloseButton");

			} else
				return false;
		} catch (Exception e) {
			System.out.println("Some error has occured");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean verifyTextOnHomePage() {
		return commonObj.getText("textTodaysDeal").contains("TODAY'S DEALS");
	}

	public boolean verifyTodaysDateDisplayedOnHomePage() {
		DateFormat dateFormat = new SimpleDateFormat(
				"yyyyy.MMMMM.dd GGG hh:mm aaa");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		String[] onlyDate = todaysDate.split(" ");
		String[] dateMonthValue = onlyDate[0].split("\\.");

		String todaysDateOnPipeline = commonObj.getText("getTodaysDate");
		String[] monthDateOnPipeline = todaysDateOnPipeline.split(" ");

		// verified today's date
		if (dateMonthValue[2].contains(monthDateOnPipeline[2])
				&& monthDateOnPipeline[1].equalsIgnoreCase(dateMonthValue[1])) {
			return true;
		} else
			return false;
	}

	public ArticlePage clickOnArticle() {
		// commonObj.click("popupCloseButton");
		commonObj.click("articleLink");
		return new ArticlePage(driver);
	}

	public String getIdOfArticlePresentOnHomePg() {
		String articleHrefTag = commonObj.getWebElement("articleLink")
				.getAttribute("href");
		String articleIDHrefTag = articleHrefTag.split("=")[1];
		return articleIDHrefTag;
	}

	public TheDealPage clickOnTheDealLogo() {
		// commonObj.click("popupCloseButton");
		commonObj.click("logoTheDeal");
		return new TheDealPage(driver);
	}

	public boolean verifySectionsOnPipelineHomepage() {
		if (commonObj.isElementPresentAndDisplayed("sectionQVM")
				&& commonObj.isElementPresentAndDisplayed("sectionVideo")
				&& commonObj.isElementPresentAndDisplayed("sectionDashboard")
				&& commonObj.isElementPresentAndDisplayed("sectionRumorMill"))
			return true;
		else
			return false;
	}

	public boolean verifyPageNavigation() {
		String navigateToFirstPage = null;
		String navigatedPageurl = null;
		String navigatedNextPageurl = null;
		commonObj.click("pageNavigation2Link");
		navigatedPageurl = commonObj.getPageUrl();
		if (navigatedPageurl.endsWith("page=2")) {
			commonObj.click("pageNavigationNextLink");
			navigatedNextPageurl = commonObj.getPageUrl();
		}
		if (navigatedNextPageurl.endsWith("page=3")) {
			navigateToFirstPage = navigatedNextPageurl.replace("page=3",
					"page=1");
			commonObj.naviagetTo(navigateToFirstPage);
		}
		if (navigateToFirstPage.endsWith("page=1"))
			return true;
		else
			return false;
	}

	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyPageTitle() {
		String pageTitle = "TODAY'S DEALS";
		return getPageTitle().contains(pageTitle);
	}

	public boolean verifyArbitrageSituationsChartPageIsDisplayed() {
		commonObj.click("linkArbitrageSituationsChart");
		if ((commonObj.getText("headerTextArbitrageSituationsChart")
				.equals("Arbitrage Situations Chart"))
				&& (commonObj.getText("reportTextArbitrageSituations")
						.startsWith("Arbitrage situations as of market"))) {
			commonObj.click("homePageMenuLink");
			return true;
		} else {
			commonObj.click("homePageMenuLink");
			return false;
		}
	}

	public boolean verifyInvestmentBankingPageIsDisplayed() {
		boolean articleflag, dealflag;
		commonObj.click("investmentBankingLink");
		dealflag = commonObj
				.isElementPresentAndDisplayed("verifyDealsDisplayed");
		articleflag = commonObj
				.isElementPresentAndDisplayed("verifyArticleDisplayed");
		if (commonObj.getText("investmentBankingText").equals(
				"Investment Banking News")
				&& (dealflag || articleflag)) {
			commonObj.click("homePageMenuLink");
			return true;
		} else {
			commonObj.click("homePageMenuLink");
			return false;
		}
	}

	public boolean verifyPrivateEquityScreenerPageIsDisplayed() {
		commonObj.click("linkPrivateEquity");
		if (commonObj.getText("headerTextPrivateEquity").equals(
				"PRIVATE EQUITY SCREENER")) {
			
			commonObj.click("linkSearchFirmsAlphabetically");
			return true;
		} else {
			commonObj.click("homePageMenuLink");
			return false;
		}
	}

}
