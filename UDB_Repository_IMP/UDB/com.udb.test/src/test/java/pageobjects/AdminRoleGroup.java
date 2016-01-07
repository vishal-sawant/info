package pageobjects;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.CommonMethods;
import base.LaunchBrowser;

public class AdminRoleGroup {

	private WebDriver driver;
	protected CommonMethods commonObj = LaunchBrowser.getCommonMethodObj();
	protected String grpNm = "TEST_GROUP_" + Math.random();

	public AdminRoleGroup(WebDriver driver) {
		this.driver = driver;
	}

	public boolean addGroupPgDisplayed() {
		if (commonObj.isElementPresentAndDisplayed("addGroupBtn"))
			return true;
		else
			return false;
	}

	public boolean verifyDiffColsOnRoleGrpPg() {
		List<WebElement> roleGroupColNms = commonObj
				.getWebElements("roleGrpCols");
		String[] columns = new String[] { "Action", "Group Name",
				"Description", "Module" };
		int i = 0;
		for (WebElement currentColumn : roleGroupColNms) {
			if (!currentColumn.getText().equals(columns[i])) {
				return false;
			}
			i++;
		}
		return true;
	}

	public boolean getDropDownEntries() {
		boolean match = true;
		String[] showEntriesValues = { "10", "25", "50", "100" };
		WebElement dropdown = driver.findElement(By.name("example_length"));
		Select select = new Select(dropdown);

		List<WebElement> options = select.getOptions();
		int i = 0;
		for (WebElement we : options) {
			if (!(we.getText().equals(showEntriesValues[i]))) {
				match = false;
			}
			i++;
		}
		return match;
	}

	public String getdefaultSelectedShowEntriesOption() {
		WebElement dropdown = driver.findElement(By.name("example_length"));
		Select select = new Select(dropdown);
		WebElement defaultSelected = select.getFirstSelectedOption();
		return defaultSelected.getText();
	}

	public int getNumberOfRecordsDisplayedOnRoleGrpPg() {
		List<WebElement> recordsOnCompHp = commonObj
				.getWebElements("numberOfRecordsDispOnRoleGrpPg");
		return recordsOnCompHp.size();
	}

	public boolean pageNavigation() {
		boolean pageNavigationWorks = true;
		driver.navigate().refresh();
		List<WebElement> recordsOnRoleGrpPg = commonObj
				.getWebElements("numberOfRecordsDispOnRoleGrpPg");
		int recordCount = recordsOnRoleGrpPg.size();
		if (recordCount == 10) {
			String roleGrpNm = commonObj.getText("nmOfGrp");
			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				WebDriverWait wait = new WebDriverWait(driver, 7);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				if (commonObj.getText("nmOfGrp").equals(roleGrpNm)) {
					pageNavigationWorks = false;
					break;
				} else
					roleGrpNm = commonObj.getText("nmOfGrp");
			}
		}
		return pageNavigationWorks;
	}

	public boolean clickOnDiffPageNavLinks() {
		driver.navigate().refresh();
		commonObj.click("lastNav");
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		if (!(commonObj.isElementPresentAndDisplayed("disabledNextNav") && commonObj
				.isElementPresentAndDisplayed("disabledLastNav")))
			return false;
		commonObj.click("firstNav");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		commonObj.click("nextNav");
		commonObj.click("previousNav");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		if (!(commonObj.isElementPresentAndDisplayed("disabledFirstNav") && commonObj
				.isElementPresentAndDisplayed("disabledPreviousNav")))
			return false;
		else
			return true;
	}

	public boolean searchByGroupName() {
		boolean searchWorks = true;
		driver.navigate().refresh();
		String grpNm = commonObj.getText("selectGrpName");
		grpNm = grpNm.split("_")[0];
		commonObj.type("searchByGrpNm", grpNm);
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		List<WebElement> recordsByGrpNm = commonObj
				.getWebElements("numberOfRecordsDispOnRoleGrpPg");
		for (WebElement record : recordsByGrpNm) {
			if (!record.findElement(By.xpath("./td[2]")).getText()
					.contains(grpNm)) {
				searchWorks = false;
				break;
			}
		}

		while (commonObj.isClickable("nextNav")) {
			commonObj.click("nextNav");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			recordsByGrpNm = commonObj
					.getWebElements("numberOfRecordsDispOnRoleGrpPg");
			for (WebElement record : recordsByGrpNm) {
				if (!record.findElement(By.xpath("./td[2]")).getText()
						.contains(grpNm)) {
					searchWorks = false;
					break;
				}
			}
		}
		return searchWorks;
	}

	public boolean checkGrpNmsInAscdingOrder() {
		boolean grpNmInAscdingOrder = true;
		driver.navigate().refresh();
		String grpNm1 = commonObj.getText("firstGrpNm");
		List<WebElement> filteredRecords = commonObj
				.getWebElements("numberOfRecordsDispOnRoleGrpPg");
		for (WebElement record : filteredRecords) {
			String grpNm2 = record.findElement(By.xpath("./td[2]")).getText();
			if (grpNm1.compareTo(grpNm2) > 0) {
				grpNmInAscdingOrder = false;
				break;
			} else {
				grpNm1 = grpNm2;
			}
		}
		while (commonObj.isClickable("nextNav")) {
			commonObj.click("nextNav");
			WebDriverWait wait = new WebDriverWait(driver, 7);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			grpNm1 = commonObj.getText("firstGrpNm");
			filteredRecords = commonObj
					.getWebElements("numberOfRecordsDispOnRoleGrpPg");
			for (WebElement record : filteredRecords) {
				String grpNm2 = record.findElement(By.xpath("./td[2]"))
						.getText();
				if (grpNm1.compareTo(grpNm2) > 0) {
					grpNmInAscdingOrder = false;
					break;
				} else {
					grpNm1 = grpNm2;
				}
			}
		}
		return grpNmInAscdingOrder;
	}

	public boolean grpNmsInDescendingOrder() {
		boolean grpNmInDescendingOrder = true;
		driver.navigate().refresh();
		commonObj.click("clickOnGrpNmCol");
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		String grpNm1 = commonObj.getText("firstGrpNm");
		List<WebElement> filteredRecords = commonObj
				.getWebElements("numberOfRecordsDispOnRoleGrpPg");
		for (WebElement record : filteredRecords) {
			String grpNm2 = record.findElement(By.xpath("./td[2]")).getText();
			if (grpNm1.compareTo(grpNm2) < 0) {
				grpNmInDescendingOrder = false;
				break;
			} else {
				grpNm1 = grpNm2;
			}
		}
		while (commonObj.isClickable("nextNav")) {
			commonObj.click("nextNav");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			grpNm1 = commonObj.getText("firstGrpNm");
			filteredRecords = commonObj
					.getWebElements("numberOfRecordsDispOnRoleGrpPg");
			for (WebElement record : filteredRecords) {
				String grpNm2 = record.findElement(By.xpath("./td[2]"))
						.getText();
				if (grpNm1.compareTo(grpNm2) < 0) {
					grpNmInDescendingOrder = false;
					break;
				} else {
					grpNm1 = grpNm2;
				}
			}
		}
		return grpNmInDescendingOrder;
	}

	public boolean closeNewGrpFrm() {
		commonObj.click("addGroupBtn");
		commonObj.click("closeGrpBttn");
		if (!commonObj.isElementPresentAndDisplayed("newGrpForm"))
			return true;
		else
			return false;
	}

	public boolean createNewGroup() {
		boolean addedNewGrp = false;
		commonObj.click("addGroupBtn");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		if (!commonObj.isElementPresentAndDisplayed("newGrpForm")) {
			return addedNewGrp;
		}
		commonObj.type("grpNmField", grpNm);
		commonObj.type("grpDesc", "Group created for testing purpose");
		List <WebElement> opts = driver.findElements(By.xpath("//select[@id='multiselect']/option")); 
		wait.until(ExpectedConditions.visibilityOfAllElements(opts));
		Select dropdown = new Select(driver.findElement(By.id("multiselect")));
		dropdown.selectByVisibleText("ROLE_COMPANY_PUBLISH");
		dropdown.selectByVisibleText("ROLE_COMPANY_WRITE");
		dropdown.selectByVisibleText("ROLE_MANDA_READ");
		dropdown.selectByVisibleText("ROLE_PEOPLE_READ");
		commonObj.click("moveSelectedToRight");
		commonObj.click("saveGrpBttn");
		if (commonObj.isElementPresentAndDisplayed("roleGrpMsg")) {
			commonObj.type("searchByGrpNm", grpNm);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			WebElement recordByGrpNm = commonObj
					.getWebElement("numberOfRecordsDispOnRoleGrpPg");
			if (recordByGrpNm.findElement(By.xpath("./td[2]")).getText()
					.contains(grpNm))
				addedNewGrp = true;
		} else
			addedNewGrp = false;
		return addedNewGrp;
	}

	public boolean invalidGrpNmCheck(String grpNm) {
		boolean grpNmChk = false;
		commonObj.click("addGroupBtn");
		WebElement grpForm = driver.findElement(By
				.xpath("//form[@name='groupForm']"));
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(grpForm));
		commonObj.type("grpNmField", grpNm);
		commonObj.type("grpDesc", "Group created for testing purpose");
		Select dropdown = new Select(driver.findElement(By.id("multiselect")));
		dropdown.selectByVisibleText("ROLE_COMPANY_PUBLISH");
		commonObj.click("moveSelectedToRight");
		commonObj.click("saveGrpBttn");
		if (commonObj.isElementPresentAndDisplayed("alertMsgForInvGrpNm"))
			grpNmChk = true;
		return grpNmChk;
	}

	public boolean addRmvModuleOptions() {
		boolean addRmvModule = true;
		commonObj.click("addGroupBtn");
		WebElement grpForm = driver.findElement(By
				.xpath("//form[@name='groupForm']"));
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.visibilityOf(grpForm));
		commonObj.type("grpNmField", grpNm);
		commonObj.type("grpDesc", "Group created for testing purpose");
		commonObj.click("moveAllToRight");
		Select leftDropdown = new Select(driver.findElement(By
				.id("multiselect")));
		List<WebElement> options1 = leftDropdown.getOptions();
		if (!options1.isEmpty())
			addRmvModule = false;
		commonObj.click("moveAllToLeft");
		Select RightDropdown = new Select(driver.findElement(By
				.id("multiselect_to")));
		List<WebElement> options2 = RightDropdown.getOptions();
		if (!options2.isEmpty())
			addRmvModule = false;
		String[] moduleNm = { "ROLE_COMPANY_PUBLISH", "ROLE_COMPANY_WRITE",
				"ROLE_MANDA_READ", "ROLE_PEOPLE_READ" };
		leftDropdown.selectByVisibleText(moduleNm[0]);
		leftDropdown.selectByVisibleText(moduleNm[1]);
		leftDropdown.selectByVisibleText(moduleNm[2]);
		leftDropdown.selectByVisibleText(moduleNm[3]);
		commonObj.click("moveSelectedToRight");
		List<WebElement> options3 = RightDropdown.getOptions();
		if (moduleNm.length != options3.size())
			addRmvModule = false;
		for (WebElement opt : options3) {
			for (int i = 0; i < moduleNm.length; i++) {
				if (opt.getText().equals(moduleNm[i]))
					break;
				else if (i == moduleNm.length - 1)
					addRmvModule = false;
			}
		}
		commonObj.click("moveSelectedToLeft");
		List<WebElement> options4 = RightDropdown.getOptions();
		if (!options4.isEmpty())
			addRmvModule = false;
		return addRmvModule;
	}

	public boolean mandatoryFields() {
		boolean chkMandateFields = true;
		String grpName = "TEST_GROUP_" + Math.random();
		commonObj.click("addGroupBtn");
		commonObj.type("grpDesc", "Group created for testing purpose");
		Select dropdown = new Select(driver.findElement(By.id("multiselect")));
		dropdown.selectByVisibleText("ROLE_COMPANY_PUBLISH");
		commonObj.click("moveSelectedToRight");
		commonObj.click("saveGrpBttn");
		if (!commonObj.isElementPresentAndDisplayed("grpNmMandatory"))
			chkMandateFields = false;
		commonObj.type("grpNmField", grpName);
		commonObj.click("moveSelectedToLeft");
		commonObj.click("saveGrpBttn");
		if (!commonObj.isElementPresentAndDisplayed("moduleNmMandatory"))
			chkMandateFields = false;
		commonObj.click("moveSelectedToRight");
		commonObj.clear("grpDesc");
		commonObj.click("saveGrpBttn");
		if (!commonObj.isElementPresentAndDisplayed("roleGrpMsg"))
			chkMandateFields = false;
		return chkMandateFields;
	}

	public boolean cancelNewGrpFrm() {
		boolean cancelNewGrp = false;
		commonObj.click("addGroupBtn");
		if (!commonObj.isElementPresentAndDisplayed("newGrpForm")) {
			return cancelNewGrp;
		}
		commonObj.type("grpNmField", "TEST_GROUP_9999");
		commonObj.type("grpDesc", "Group created for testing purpose");
		Select dropdown = new Select(driver.findElement(By.id("multiselect")));
		dropdown.selectByVisibleText("ROLE_COMPANY_PUBLISH");
		commonObj.click("moveSelectedToRight");
		commonObj.click("cancelGrpBttn");
		if (!commonObj.isElementPresentAndDisplayed("newGrpForm")) {
			commonObj.type("searchByGrpNm", "TEST_GROUP_9999");
			WebDriverWait wait = new WebDriverWait(driver, 4);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			if (commonObj.isElementPresentAndDisplayed("noDataMsg"))
				cancelNewGrp = true;
		} else
			cancelNewGrp = false;
		return cancelNewGrp;
	}

	public boolean addExistingGrpNm() {
		boolean addExistingGrpNM = false;
		commonObj.click("addGroupBtn");
		commonObj.type("grpNmField", grpNm);
		commonObj.type("grpDesc", "Group created for testing purpose");
		Select dropdown = new Select(driver.findElement(By.id("multiselect")));
		dropdown.selectByVisibleText("ROLE_COMPANY_PUBLISH");
		commonObj.click("moveSelectedToRight");
		commonObj.click("saveGrpBttn");
		if (commonObj.isElementPresentAndDisplayed("existingRoleGrpMsg"))
			addExistingGrpNM = true;
		return addExistingGrpNM;
	}

	public boolean editGrpBttn() {
		boolean editBttnWorks = false;
		commonObj.type("searchByGrpNm", "TEST_GROUP");
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		commonObj.click("clkOnEditBttn");
		if (!commonObj.isElementPresentAndDisplayed("newGrpForm")) {
			return editBttnWorks;
		}
		String groupNm = driver.findElement(By.id("inputGroupName"))
				.getAttribute("value");
		groupNm = groupNm.replace("TEST", "UPDATED");
		commonObj.type("grpNmField", groupNm);
		String groupDesc = driver.findElement(By.id("inputDescription"))
				.getAttribute("value");
		groupDesc = groupDesc.replace("created", "updated");
		commonObj.type("grpDesc", groupDesc);
		commonObj.click("moveAllToRight");
		commonObj.click("moveSelectedToLeft");
		commonObj.click("saveGrpBttn");
		if (commonObj.isElementPresentAndDisplayed("roleGrpUpdtMsg"))
			editBttnWorks = true;
		return editBttnWorks;
	}

	public boolean deleteGrpBttn() {
		boolean deleteBttnWorks = true;
		driver.navigate().refresh();
		String rcdCntBfrDel = commonObj.getWebElement("totalRoleGrpEntries")
				.getText();
		rcdCntBfrDel = rcdCntBfrDel.split("of ")[1];
		rcdCntBfrDel = rcdCntBfrDel.split(" ")[0];
		int totalRcdsBfrDel = Integer.parseInt(rcdCntBfrDel);
		commonObj.type("searchByGrpNm", "TEST_GROUP");
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		String roleGrpNm = commonObj.getWebElement("roleGrpRcrdToDel")
				.getText();
		commonObj.click("clkOnDeleteBttn");
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		if (!commonObj.isElementPresentAndDisplayed("roleGrpDelMsg"))
			deleteBttnWorks = false;
		driver.navigate().refresh();
		String rcdCntaftrDel = commonObj.getWebElement("totalRoleGrpEntries")
				.getText();
		rcdCntaftrDel = rcdCntaftrDel.split("of ")[1];
		rcdCntaftrDel = rcdCntaftrDel.split(" ")[0];
		int totalRcdsAfrDel = Integer.parseInt(rcdCntaftrDel);
		if (totalRcdsAfrDel != (totalRcdsBfrDel - 1))
			deleteBttnWorks = false;
		commonObj.type("searchByGrpNm", roleGrpNm);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		if (!commonObj.isElementPresentAndDisplayed("noDataMsg"))
			deleteBttnWorks = false;
		return deleteBttnWorks;
	}

	public boolean deleteGrpFrmAssignedGrpList() {
		boolean delGrpFrmAssgndUser = true;
		driver.navigate().refresh();
		commonObj.click("adminUser");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		commonObj.click("clkOnUser1EditBttn");
		List <WebElement> opts = driver.findElements(By.xpath("//select[@id='multiselect']/option")); 
		wait.until(ExpectedConditions.visibilityOfAllElements(opts));
		Select dropdown1 = new Select(driver.findElement(By.id("multiselect")));
		dropdown1.selectByVisibleText(grpNm);
		commonObj.click("moveSelectedToRight");
		commonObj.click("saveUserBttn");
		commonObj.click("clkOnUser2EditBttn");
		List <WebElement> opts1 = driver.findElements(By.xpath("//select[@id='multiselect']/option")); 
		wait.until(ExpectedConditions.visibilityOfAllElements(opts1));
		Select dropdown2 = new Select(driver.findElement(By.id("multiselect")));
		dropdown2.selectByVisibleText(grpNm);
		commonObj.click("moveSelectedToRight");
		commonObj.click("saveUserBttn");
		commonObj.click("roleGroup");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Search Group Name']")));
		commonObj.type("searchByGrpNm", grpNm);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		commonObj.click("clkOnDeleteBttn");
		Alert alert = driver.switchTo().alert();
		alert.accept();
		commonObj.click("adminUser");
		commonObj.click("clkOnUser1EditBttn");
		Select dropdown3 = new Select(driver.findElement(By
				.id("multiselect_to")));
		List<WebElement> user1Opts = dropdown3.getOptions();
		for (WebElement option : user1Opts) {
			if (option.getText().equals(grpNm)) {
				delGrpFrmAssgndUser = false;
				break;
			}
		}
		commonObj.click("cancelUserBttn");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		commonObj.click("clkOnUser2EditBttn");
		Select dropdown4 = new Select(driver.findElement(By
				.id("multiselect_to")));
		List<WebElement> user2Opts = dropdown4.getOptions();
		for (WebElement option : user2Opts) {
			if (option.getText().equals(grpNm)) {
				delGrpFrmAssgndUser = false;
				break;
			}
		}
		return delGrpFrmAssgndUser;
	}
}
