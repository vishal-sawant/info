package udbtests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.LaunchBrowser;
import pageobjects.AuctionHome;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class AuctionHomeTest extends LaunchBrowser {
	private LoginPage loginPage;
	private HomePage homePage;
	private AuctionHome auctionHome;

	@Test(priority = 1)
	public void verifyAuctionHomePgDisplayed() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn(logger);
		auctionHome = homePage.clickOnAuctionLink();
		Assert.assertTrue(
				auctionHome.getAuctionHPageText().contains("Auction ID"),
				"Auction home page not displayed");
		logger.info("Test case passed: verify clicking on the Auction tab Auction home page gets displayed");
		Reporter.log("Test case passed: verify clicking on the Auction tab Auction home page gets displayed");
	}

	@Test(priority = 2)
	public void verifyAllColsDispOnAuctionHp() {
		Assert.assertTrue(auctionHome.verifyDiffColsOnAuctionHmPg(),
				"Not all columns are displayed on Auction home page");
		logger.info("Test case passed: verify all the M&A columns are displayed on Auction home page");
		Reporter.log("Test case passed: verify all the M&A columns are displayed on Auction home page");
	}

	//@Test(priority = 3)
	public void verifyDefaultSelectedValueOfShowEntiresDropdown() {
		Assert.assertEquals(auctionHome.getdefaultSelectedShowEntriesOption(),
				"10",
				"By default, 10 is not selected in 'Show entries' dropdown");
		logger.info("Test case passed: verify by default, 10 is selected in 'Show entries' dropdown");
		Reporter.log("Test case passed: verify by default, 10 is selected in 'Show entries' dropdown");
	}

	@Test(priority = 4)
	public void verifyDefaultNumberOfRecordsDispOnAuctionHmPg() {
		Assert.assertEquals(
				auctionHome.getNumberOfRecordsDisplayedOnAuctionHome(), 10,
				"Not 10 records are displayed per page");
		logger.info("Test case passed: verify if 10 is selected in Show Entries, only 10 records are displayed per page");
		Reporter.log("Test case passed: verify if 10 is selected in Show Entries, only 10 records are displayed per page");
	}

	@Test(priority = 5)
	public void verifyRecordsDisplayedAsPerSelValInDropdown() {
		Assert.assertTrue(
				auctionHome.adjustNumberOfRecordsToBeDispOnAunctionHm(),
				"Incorrect number of records displayed on Auction home page");
		logger.info("Test case passed: verify correct number of records display on Auction home page as per value selected in the show entries drop down");
		Reporter.log("Test case passed: verify correct number of records display on Auction home page as per value selected in the show entries drop down");
	}

	@DataProvider
	public Object[][] pageNavDataProvider() {
		return new Object[][] { { "10" }, { "25" }, { "50" }, { "100" } };
	}

	@Test(priority = 6, dataProvider = "pageNavDataProvider")
	public void verifyAuctionHmPgNav(String recordsToDisplay) {
		Assert.assertTrue(auctionHome.pageNavigation(recordsToDisplay),
				"Auction home page navigation does not work");
		logger.info("Test case passed: verify pagination works fine on Auction home page");
		Reporter.log("Test case passed: verify pagination works fine on Auction home page");
	}

	@Test(priority = 7)
	public void verifyDiffPageNavLinkWorks() {
		Assert.assertTrue(auctionHome.clickOnDiffPageNavLinks(),
				"Different page navigation links do not work");
		logger.info("Test case passed: verify page navigation links like First, Previous, Next and Last work properly");
		Reporter.log("Test case passed: verify page navigation links like First, Previous, Next and Last work properly");
	}

	@DataProvider
	public Object[][] searchOptionDataProvider() {
		return new Object[][] { { "byAuctionId" }, { "byTarget" },
				{ "bySeller" }, { "byFrom-ToDate" }, { "byStatus" },
				{ "byLastUpdatedUser" }, { "byLastUpdatedDate" },
				{ "byPublished-Unpublished" }, { "byUpdatesPublished" } };
	}

	@Test(priority = 8, dataProvider = "searchOptionDataProvider")
	public void verifySearchOnAuctionHp(String searchByType) {
		Assert.assertTrue(auctionHome.diffSearchOnAuctionHome(searchByType),
				"Search functionality does not work on Auction home page");
		logger.info("Test case passed: Verify user able to search records by Auction Id, Target, Seller,  From-To Date, Status, Updated User, Published, etc.");
		Reporter.log("Test case passed: Verify user able to search records by Auction Id, Target, Seller,  From-To Date, Status, Updated User, Published, etc.");
	}

	@Test (priority=9)
	public void verifyDefaultFilterWorksOnAuctionHp() {
		Assert.assertTrue(auctionHome.filterRecordsByUpdatedDate(),
				"By default records are not filtered by Last Updated Date on Auction Home page");
		logger.info("Test case passed: verify by default records are filtered by Last Updated Date in descending order on Auction Home page");
		Reporter.log("Test case passed: verify by default records are filtered by Last Updated Date in descending order on Auction Home page");
	}

	@DataProvider
	public Object[][] filterOptionDataProvider() {
		return new Object[][] { { "filterOnAuctionId" },
				{ "filterOnAnnouncementDate" }, { "filterOnStatus" },
				{ "filterOnLastUpdatedUser" } };
	}

	@Test (priority=10, dataProvider="filterOptionDataProvider")
	public void verifyFilterFunctionality(String filterByOption) {
		Assert.assertTrue(auctionHome.filterByFunctionality(filterByOption),
				"Records filter functionality does not work on Auction home page");
		logger.info("Test case passed: verify user able to filter records by Auction ID, Auction Date, Status and Updated User");
		Reporter.log("Test case passed: verify user able to filter records by Auction ID, Auction Date, Status and Updated User");
	}
}
