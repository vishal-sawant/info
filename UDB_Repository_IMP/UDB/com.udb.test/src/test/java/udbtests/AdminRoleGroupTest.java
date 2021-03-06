package udbtests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.CommonConstant;
import base.LaunchBrowser;
import base.ReadWriteExcel;
import pageobjects.AdminRoleGroup;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class AdminRoleGroupTest extends LaunchBrowser {
	private LoginPage loginPage;
	private HomePage homePage;
	private AdminRoleGroup adminRoleGroup;

	@Test(priority = 1)
	public void verifyRoleGroupPgDisp() {
		loginPage = new LoginPage(driver);
		homePage = loginPage.clickLoginBtn(logger);
		adminRoleGroup = homePage.clickOnAdminRoleGroup();
		Assert.assertTrue(adminRoleGroup.addGroupPgDisplayed(),
				"Role group page not displayed");
		logger.info("Test case passed: verify by clicking on 'Role Group' button, it redirects to Add Group page");
		Reporter.log("Test case passed: verify by clicking on 'Role Group' button, it redirects to Add Group page");
	}

	//@Test(priority = 2)
	public void verifyAllColsDispOnRoleGrpPg() {
		Assert.assertTrue(adminRoleGroup.verifyDiffColsOnRoleGrpPg(),
				"Not all columns are displayed on Admin Role Group page");
		logger.info("Test case passed: verify all the columns are displayed on Admin Role Group page");
		Reporter.log("Test case passed: verify all the company columns are displayed on Admin Role Group page");
	}

	//@Test(priority = 3)
	public void verifyShowEntriesDropdownvalues() {
		Assert.assertTrue(adminRoleGroup.getDropDownEntries(),
				"Show Entries drop down values does not match");
		logger.info("Test case passed: verify user able to see values 10, 25, 50, 100 in 'Show entries' dropdown");
		Reporter.log("Test case passed: verify user able to see values 10, 25, 50, 100 in 'Show entries' dropdown");
	}

	//@Test(priority = 4)
	public void verifyDefaultSelectedValueOfShowEntiresDropdown() {
		Assert.assertEquals(
				adminRoleGroup.getdefaultSelectedShowEntriesOption(), "10",
				"By default, 10 is not selected in 'Show entries' dropdown");
		logger.info("Test case passed: verify by default, 10 is selected in 'Show entries' dropdown");
		Reporter.log("Test case passed: verify by default, 10 is selected in 'Show entries' dropdown");
	}

	//@Test(priority = 5)
	public void verifyDefaultNumberOfRecordsDispOnRoleGrpPg() {
		Assert.assertEquals(
				adminRoleGroup.getNumberOfRecordsDisplayedOnRoleGrpPg(), 10,
				"Not 10 records are displayed per page");
		logger.info("Test case passed: verify if 10 is selected in Show Entries, only 10 records are displayed per page");
		Reporter.log("Test case passed: verify if 10 is selected in Show Entries, only 10 records are displayed per page");
	}

	//@Test(priority = 6)
	public void verifyRoleGrpPageNavigation() {
		Assert.assertTrue(adminRoleGroup.pageNavigation(),
				"Role group page navigation does not work");
		logger.info("Test case passed: verify Role group page pagination works fine");
		Reporter.log("Test case passed: verify Role group home page pagination works fine");
	}

	//@Test(priority = 7)
	public void verifyDiffPageNavLinkWorks() {
		Assert.assertTrue(adminRoleGroup.clickOnDiffPageNavLinks(),
				"Different page navigation links do not work");
		logger.info("Test case passed: verify page navigation links like First, Previous, Next and Last links work properly");
		Reporter.log("Test case passed: verify page navigation links like First, Previous, Next and Last links work properly");
	}

	//@Test(priority = 8)
	public void verifySearchOnRoleGrpPg() {
		Assert.assertTrue(adminRoleGroup.searchByGroupName(),
				"Search functionality does not work on Role group page");
		logger.info("Test case passed: verify user able to search records by Role group name");
		Reporter.log("Test case passed: verify user able to search records by Role group name");
	}

	//@Test(priority = 9)
	public void verifyDefaultSortingOnGrpByName() {
		Assert.assertTrue(adminRoleGroup.checkGrpNmsInAscdingOrder(),
				"Role group names are not displayed in ascending order");
		logger.info("Test case passed: verify by default, group names are displayed in ascending order");
		Reporter.log("Test case passed: verify by default, group names are displayed in ascending order");
	}

	//@Test(priority = 10)
	public void verifyGrpNameSortingInDescendingOrder() {
		Assert.assertTrue(adminRoleGroup.grpNmsInDescendingOrder(),
				"Sorting functionality on Group Name column does not work properly");
		logger.info("Test case passed: verify sorting functionality on Group Name column works properly");
		Reporter.log("Test case passed: verify sorting functionality on Group Name column works properly");
	}

	//@Test(priority = 11)
	public void verifyCloseNewGrpFrom() {
		Assert.assertTrue(adminRoleGroup.closeNewGrpFrm(),
				"Unable to close New Role Group form");
		logger.info("Test case passed: verify user is able to close new role group form");
		Reporter.log("Test case passed: verify user is able to close new role group form");
	}

	@Test(priority = 12)
	public void verifyCreateNewGroup() {
		Assert.assertTrue(adminRoleGroup.createNewGroup(),
				"Unable to create a new group");
		logger.info("Test case passed: verify user is able to create a new role group using Add New Group button");
		Reporter.log("Test case passed: verify user is able to create a new role group using Add New Group button");
	}

	@DataProvider
	public Object[][] supplyInvalidRoleGrpNm() throws IOException {
		Object[][] roleGrpNm = ReadWriteExcel.getExcelData(
				CommonConstant.UDB_DATA_PATH, CommonConstant.InvalidGrpNm);
		return roleGrpNm;
	}

	//@Test(priority = 13, dataProvider = "supplyInvalidRoleGrpNm")
	public void verifyInvalidGrpNm(String grpNm) {
		Assert.assertTrue(adminRoleGroup.invalidGrpNmCheck(grpNm),
				"Role group name accepts invalid special characters");
		logger.info("Test case passed: verify group name allows only alphabets, number and special characters like : '&', '_', '.', '-', '''");
		Reporter.log("Test case passed: verify group name allows only alphabets, number and special characters like : '&', '_', '.', '-', '''");
	}

	//@Test(priority = 14)
	public void verifyAddRemoveModuleFunctionality() {
		Assert.assertTrue(adminRoleGroup.addRmvModuleOptions(),
				"Unable to add remove module options properly");
		logger.info("Test case passed: verify user is able to add remove module options properly on New Role Group form");
		Reporter.log("Test case passed: verify user is able to add remove module options properly on New Role Group form");
	}

	//@Test(priority = 15)
	public void verifyMandateFieldsOnAddNewGrpForm() {
		Assert.assertTrue(adminRoleGroup.mandatoryFields(),
				"Mandatory fields of Add New Group form does not work properly");
		logger.info("Test case passed: verify mandatory and non mandatory fields work properly on New Role Group form");
		Reporter.log("Test case passed: verify mandatory and non mandatory fields work properly on New Role Group form");
	}

	//@Test(priority = 16)
	public void verifyCancelBttnOnNewGrpForm() {
		Assert.assertTrue(adminRoleGroup.cancelNewGrpFrm(),
				"Cancel button of Add New Group form does not work properly");
		logger.info("Test case passed: verify by clicking on the Cancel button New Role Group form gets closed and record does not get saved");
		Reporter.log("Test case passed: verify by clicking on the Cancel button New Role Group form gets closed and record does not get saved");
	}

	//@Test(priority = 17)
	public void verifyAddingNewRoleGrpWithExistingRoleGrpNm() {
		Assert.assertTrue(adminRoleGroup.addExistingGrpNm(),
				"New Role Group form accepts existing role group name");
		logger.info("Test case passed: verify New Role Group form does not get saved if user enters existing Role Group name");
		Reporter.log("Test case passed: verify New Role Group form does not get saved if user enters existing Role Group name");
	}

	@Test(priority = 18)
	public void verifyEditGroupFunctionality() {
		Assert.assertTrue(adminRoleGroup.editGrpBttn(),
				"Unable to update existing role group");
		logger.info("Test case passed: verify user is able to update existing role group details by clicking on the Edit Group button");
		Reporter.log("Test case passed: verify user is able to update existing role group details by clicking on the Edit Group button");
	}

	@Test(priority = 19)
	public void verifyDeleteGroupFunctionality() {
		Assert.assertTrue(adminRoleGroup.deleteGrpBttn(),
				"Unable to delete role group");
		logger.info("Test case passed: verify user is able to delete role group record by clicking on the delete button and accepting the confrimation popup");
		Reporter.log("Test case passed: verify user is able to delete role group record by clicking on the delete button and accepting the confrimation popup");
	}

	//@Test(priority = 20)
	public void verifyDeleteGroupFromAssignedUser() {
		Assert.assertTrue(adminRoleGroup.deleteGrpFrmAssignedGrpList(),
				"Unable to remove deleted role group from role group list assigned to the user");
		logger.info("Test case passed: verify after deleting the role group from UDB same should be removed from user role group list");
		Reporter.log("Test case passed: verify after deleting the role group from UDB same should be removed from user role group list");
	}
}
