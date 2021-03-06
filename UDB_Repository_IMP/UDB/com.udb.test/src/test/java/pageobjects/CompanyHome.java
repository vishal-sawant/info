package pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.CommonMethods;
import base.LaunchBrowser;

public class CompanyHome {
	private WebDriver driver;
	protected CommonMethods commonObj = LaunchBrowser.getCommonMethodObj();

	public CompanyHome(WebDriver driver) {
		this.driver = driver;
	}

	public String getCompHPageText() {
		String title = commonObj.getText("textOnCompHP");
		return title;
	}

	public boolean verifyDifferentColumnsOnCompanyHP() {
		List<WebElement> companyHPColumnNames = commonObj
				.getWebElements("companyHomePgColumns");
		String[] columns = new String[] { "Action", "Company Id",
				"Company Name", "Industries", "Regions", "Company Type",
				"Deals", "Updated User", "Updated Date", "Pub" };
		int i = 0;
		for (WebElement currentColumn : companyHPColumnNames) {
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

	public int getNumberOfRecordsDisplayedOnCompHp() {
		List<WebElement> recordsOnCompHp = commonObj
				.getWebElements("numberOfRecordsDispOnCompHp");
		return recordsOnCompHp.size();

	}

	public boolean adjustNumberOfRecordsToBeDisplayedOnCompHP() {
		boolean isRecordsNumberMatches = true;
		String[] showEntriesValues = { "25", "50", "100" };
		Select select = new Select(
				driver.findElement(By.name("example_length")));
		for (int i = 0; i < showEntriesValues.length; i++) {
			select.selectByValue(showEntriesValues[i]);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsOnCompHp = commonObj
					.getWebElements("numberOfRecordsDispOnCompHp");
			int temp = Integer.parseInt(showEntriesValues[i]);
			int recordCount = recordsOnCompHp.size();
			if (recordCount != temp) {
				isRecordsNumberMatches = false;
				break;
			}
		}
		return isRecordsNumberMatches;
	}

	public boolean pageNavigation(String recordsToDisplay) {
		boolean pageNavigationWorks = true;
		driver.navigate().refresh();
		Select select = new Select(
				driver.findElement(By.name("example_length")));
		select.selectByValue(recordsToDisplay);
		WebDriverWait wait = new WebDriverWait(driver, 12);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		List<WebElement> recordsOnCompHp = commonObj
				.getWebElements("numberOfRecordsDispOnCompHp");
		int temp = Integer.parseInt(recordsToDisplay);
		int recordCount = recordsOnCompHp.size();
		if (recordCount == temp) {
			String compName = commonObj.getText("nameOfCompany");
			for (int j = 0; j < 30; j++) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				if (commonObj.getText("nameOfCompany").equals(compName)) {
					pageNavigationWorks = false;
					break;
				} else
					compName = commonObj.getText("nameOfCompany");
			}
		}
		return pageNavigationWorks;
	}
	
	public boolean compNmUsingNumericData(){
		commonObj.type("searchByCompName", "1000988");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(commonObj.isElementPresentAndDisplayed("example_processing"))
			return false;
		else
			return true;
	}

	public boolean clickOnDiffPageNavLinks() {
		driver.navigate().refresh();
		commonObj.click("lastNav");
		WebDriverWait wait = new WebDriverWait(driver, 12);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		if (!(commonObj.isElementPresentAndDisplayed("disabledNextNav") && commonObj
				.isElementPresentAndDisplayed("disabledLastNav")))
			return false;
		commonObj.click("previousNav");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		commonObj.click("firstNav");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.id("example_processing")));
		if (!(commonObj.isElementPresentAndDisplayed("disabledFirstNav") && commonObj
				.isElementPresentAndDisplayed("disabledPreviousNav")))
			return false;
		else
			return true;
	}

	public boolean diffSearchOnCompHp(String searchType, Logger logger) {
		boolean searchWorks = true;
		WebDriverWait wait = new WebDriverWait(driver, 12);

		switch (searchType) {

		case "byCompanyId":
			String compId = commonObj.getText("selectCompId");
			commonObj.type("searchByCompID", compId);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByCompId = commonObj
					.getWebElements("numberOfRecordsDispOnCompHp");
			int recordCount = recordsByCompId.size();
			if (!(recordCount == 1 && commonObj.getText("getCompId").equals(
					compId)))
				searchWorks = false;
			break;

		case "byCompanyName":
			driver.navigate().refresh();
			String compName = commonObj.getText("selectCompName");
			commonObj.type("searchByCompName", compName);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByCompName = commonObj
					.getWebElements("numberOfRecordsDispOnCompHp");
			for (WebElement record : recordsByCompName) {
				if (!record.findElement(By.xpath("./td[3]")).getText()
						.contains(compName)) {
					searchWorks = false;
					break;
				}
			}

			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				recordsByCompName = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : recordsByCompName) {
					if (!record.findElement(By.xpath("./td[3]")).getText()
							.contains(compName)) {
						searchWorks = false;
						break;
					}
				}
			}
			break;

		case "byUpdatedUser":
			driver.navigate().refresh();
			String getUpdatedUserNm = "";
			String updatedUser = commonObj.getText("selectUpdatedUser");
			updatedUser =updatedUser.toLowerCase();
			commonObj.type("searchByUser", updatedUser);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByUpdatedUser = commonObj
					.getWebElements("numberOfRecordsDispOnCompHp");
			for (WebElement record : recordsByUpdatedUser) {
				getUpdatedUserNm = record.findElement(By.xpath("./td[8]"))
						.getText();
				getUpdatedUserNm = getUpdatedUserNm.toLowerCase();
				if (!getUpdatedUserNm.contains(updatedUser)) {
					searchWorks = false;
					break;
				}
			}
			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				recordsByUpdatedUser = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : recordsByUpdatedUser) {
					getUpdatedUserNm = record.findElement(By.xpath("./td[8]"))
							.getText();
					getUpdatedUserNm = getUpdatedUserNm.toLowerCase();
					if (!getUpdatedUserNm.contains(updatedUser)) {
						searchWorks = false;
						break;
					}
				}
			}
			break;

		case "byFrom-ToDate":
			try {
				driver.navigate().refresh();
				commonObj.click("searchByFromDate");
				commonObj.click("datePreviousButton");
				// commonObj.click("datePreviousButton");
				commonObj.click("selectDate");

				commonObj.click("searchByToDate");
				commonObj.click("selectDate");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				String fromDate = driver.findElement(
						By.id("example_range_from_8")).getAttribute("value");
				String toDate = driver.findElement(By.id("example_range_to_8"))
						.getAttribute("value");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date frmDate = dateFormat.parse(fromDate);
				Date tDate = dateFormat.parse(toDate);
				List<WebElement> recordsByUpdatedDate = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : recordsByUpdatedDate) {
					String updatedDate = record
							.findElement(By.xpath("./td[9]")).getText();
					Date uDate = dateFormat.parse(updatedDate);
					if ((uDate.compareTo(frmDate) < 0 || uDate.equals(frmDate))
							&& (tDate.compareTo(uDate) < 0 || tDate
									.equals(uDate))) {
						searchWorks = false;
						break;
					}
				}
				while (commonObj.isClickable("nextNav")) {
					commonObj.click("nextNav");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					recordsByUpdatedDate = commonObj
							.getWebElements("numberOfRecordsDispOnCompHp");
					for (WebElement record : recordsByUpdatedDate) {
						String updatedDate = record.findElement(
								By.xpath("./td[9]")).getText();
						Date uDate = dateFormat.parse(updatedDate);
						if ((uDate.compareTo(frmDate) < 0 || uDate
								.equals(frmDate))
								&& (tDate.compareTo(uDate) < 0 || tDate
										.equals(uDate))) {
							searchWorks = false;
							break;
						}
					}
				}
			} catch (Exception e) {
				logger.info("Some exception occurred:", e);
			}

			break;

		case "byPublished-Unpublished":
			String[] pubValue = { "Y", "N", "" };
			commonObj.click("firstNav");
			int totalRecords = 0,
			count = 0;
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> allRecords = commonObj
					.getWebElements("numberOfRecordsDispOnCompHp");
			totalRecords = allRecords.size();
			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				allRecords = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				totalRecords = totalRecords + allRecords.size();
			}
			for (int i = 0; i < 3; i++) {
				if (pubValue[i].equals("")) {
					commonObj.selectDropdwonElement("pubDropdown", pubValue[i]);
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					List<WebElement> records = commonObj
							.getWebElements("numberOfRecordsDispOnCompHp");
					count = records.size();
					while (commonObj.isClickable("nextNav")) {
						commonObj.click("nextNav");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.id("example_processing")));
						records = commonObj
								.getWebElements("numberOfRecordsDispOnCompHp");
						count = count + records.size();
					}
					if (totalRecords != count)
						searchWorks = false;
				} else {
					commonObj.selectDropdwonElement("pubDropdown", pubValue[i]);
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					List<WebElement> publishedRecords = commonObj
							.getWebElements("numberOfRecordsDispOnCompHp");
					for (WebElement record : publishedRecords) {
						if (!record.findElement(By.xpath("./td[10]")).getText()
								.contains(pubValue[i])) {
							searchWorks = false;
							break;
						}
					}

					for (int j = 0; j < 5 && commonObj.isClickable("nextNav"); j++) {
						commonObj.click("nextNav");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.id("example_processing")));
						publishedRecords = commonObj
								.getWebElements("numberOfRecordsDispOnCompHp");
						for (WebElement record : publishedRecords) {
							if (!record.findElement(By.xpath("./td[10]"))
									.getText().contains(pubValue[i])) {
								searchWorks = false;
								break;
							}
						}
					}
				}
			}
			break;

		case "byDealTypes":
			String[] dealType = { "dealMA", "dealBkF", "dealBkW",
					"dealAuction", "dealOCR", "dealAll" };
			String[] dealTypeToVerify = { "Merger or Acquisition",
					"Bankruptcy Filing", "Bankruptcy Warning", "Auction",
					"Out Of Court Restructuring" };
			driver.navigate().refresh();
			for (int i = 0; i < dealType.length; i++) {
				commonObj.click("buttonDealTypes");
				if (!dealType[i].equals("dealAll")) {
					int counter = i;
					while (counter > 0) {
						if (commonObj.isSelected(dealType[counter - 1]))
							commonObj.click(dealType[counter - 1]);
						counter--;
					}
					commonObj.click(dealType[i]);
					commonObj.click("buttonClose");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					List<WebElement> filteredRecords = commonObj
							.getWebElements("numberOfRecordsDispOnCompHp");
					for (WebElement record : filteredRecords) {
						if (!record.findElement(By.xpath("./td[7]")).getText()
								.contains(dealTypeToVerify[i])) {
							searchWorks = false;
							break;
						}
					}
					for (int j = 0; j < 5 && commonObj.isClickable("nextNav"); j++) {
						commonObj.click("nextNav");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.id("example_processing")));
						filteredRecords = commonObj
								.getWebElements("numberOfRecordsDispOnCompHp");
						for (WebElement record : filteredRecords) {
							if (!record.findElement(By.xpath("./td[7]"))
									.getText().contains(dealTypeToVerify[i])) {
								searchWorks = false;
								break;
							}
						}
					}
				} else {
					commonObj.click("buttonReset");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					commonObj.click("buttonDealTypes");
					commonObj.click("dealMA");
					commonObj.click("dealBkF");
					commonObj.click("dealBkW");
					commonObj.click("dealAuction");
					commonObj.click("dealOCR");
					commonObj.click("buttonClose");
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					List<WebElement> filteredRecords = commonObj
							.getWebElements("numberOfRecordsDispOnCompHp");
					for (WebElement record : filteredRecords) {
						String currentDealType = record.findElement(
								By.xpath("./td[7]")).getText();
						if (!(currentDealType.contains("Merger or Acquisition")
								|| currentDealType.contains("Auction")
								|| currentDealType
										.contains("Bankruptcy Filing")
								|| currentDealType
										.contains("Out Of Court Restructuring") || currentDealType
									.contains("Bankruptcy Warning"))) {

							searchWorks = false;
							break;
						}
					}
					for (int j = 0; j < 5 && commonObj.isClickable("nextNav"); j++) {
						commonObj.click("nextNav");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.id("example_processing")));
						filteredRecords = commonObj
								.getWebElements("numberOfRecordsDispOnCompHp");
						for (WebElement record1 : filteredRecords) {
							String currentDealType = record1.findElement(
									By.xpath("./td[7]")).getText();
							if (!(currentDealType
									.contains("Merger or Acquisition")
									|| currentDealType.contains("Auction")
									|| currentDealType
											.contains("Bankruptcy Filing")
									|| currentDealType
											.contains("Out Of Court Restructuring") || currentDealType
										.contains("Bankruptcy Warning"))) {
								searchWorks = false;
								break;
							}
						}
					}
				}
			}
		}
		return searchWorks;
	}

	public boolean filterRecordsByUpdatedDate(Logger logger) {
		boolean defaultFilter = true;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			driver.navigate().refresh();
			for (int j = 0; j < 5; j++) {
				String firstUpdatedDate = commonObj.getText("updatedDate");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date updatedDate = dateFormat.parse(firstUpdatedDate);
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : filteredRecords) {
					String currentUpdatedDate = record.findElement(
							By.xpath("./td[9]")).getText();
					Date compareUpdatedDate = dateFormat
							.parse(currentUpdatedDate);
					if (!(compareUpdatedDate.compareTo(updatedDate) < 0 || updatedDate
							.equals(compareUpdatedDate))) {
						defaultFilter = false;
						break;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
		} catch (Exception e) {
			logger.info("Some exception occurred:", e);
		}
		return defaultFilter;
	}

	public boolean filterByFunctionality(String byFilter) {
		boolean filterWorks = true;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.navigate().refresh();
		switch (byFilter) {
		case "filterOnCompanyId":
			commonObj.click("clickOnCompIdColumn");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			commonObj.click("clickOnCompIdColumn");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 5; j++) {
				String companyID = commonObj.getText("firstCompanyID");
				int compId1 = Integer.parseInt(companyID);
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : filteredRecords) {
					String currentCompId = record.findElement(
							By.xpath("./td[2]")).getText();
					int compId2 = Integer.parseInt(currentCompId);
					if (compId1 < compId2) {
						filterWorks = false;
						break;
					} else {
						compId1 = compId2;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
			break;
		case "filterOnCompanyName":
			commonObj.click("clickOnCompNameColumn");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 5; j++) {
				String companyName1 = commonObj.getText("firstCompanyName");
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : filteredRecords) {
					String companyName2 = record.findElement(
							By.xpath("./td[3]")).getText();
					if (companyName1.compareTo(companyName2) > 0) {
						filterWorks = false;
						break;
					} else {
						companyName1 = companyName2;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
			break;

		case "filterOnUpdatedUser":
			commonObj.click("clickOnUpdatedUserColumn");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 12; j++) {
				String userName1 = commonObj.getText("firstUpdatedUser");
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnCompHp");
				for (WebElement record : filteredRecords) {
					String userName2 = record.findElement(By.xpath("./td[8]"))
							.getText();
					if (userName1.compareTo(userName2) > 0) {
						filterWorks = false;
						break;
					} else {
						userName1 = userName2;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
			break;
		}
		return filterWorks;
	}

}
