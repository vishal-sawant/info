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

public class PersonHome {

	private WebDriver driver;
	private Logger logger;
	protected CommonMethods commonObj = LaunchBrowser.getCommonMethodObj();

	public PersonHome(WebDriver driver) {
		this.driver = driver;
	}

	public String getPersonHomePageText() {
		String title = commonObj.getText("testonPersonHomePage");
		return title;
	}

	public boolean verifyDifferentColumnsOnPersonHP() {
		List<WebElement> personHPColumnNames = commonObj
				.getWebElements("personHomePgColumns");
		String[] columns = new String[] { "Action", "Person Id", "First Name",
				"Last Name", "Title", "Company", "Deals", "Updated User",
				"Updated Date", "Pub" };
		int i = 0;
		for (WebElement currentColumn : personHPColumnNames) {
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

	public int getNumberOfRecordsDisplayedOnPersonHp() {
		List<WebElement> recordsOnCompHp = commonObj
				.getWebElements("numberOfRecordsDispOnPersonHp");
		return recordsOnCompHp.size();

	}

	public boolean adjustNumberOfRecordsToBeDisplayedOnPersonHp() {
		boolean isRecordsNumberMatches = true;
		String[] showEntriesValues = { "25", "50", "100" };
		Select select = new Select(
				driver.findElement(By.name("example_length")));
		for (int i = 0; i < showEntriesValues.length; i++) {
			select.selectByValue(showEntriesValues[i]);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsOnCompHp = commonObj
					.getWebElements("numberOfRecordsDispOnPersonHp");
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
		List<WebElement> recordsOnHomeHp = commonObj
				.getWebElements("numberOfRecordsDispOnPersonHp");
		int temp = Integer.parseInt(recordsToDisplay);
		int recordCount = recordsOnHomeHp.size();
		if (recordCount == temp) {
			String pName = commonObj.getText("personId");
			for (int j = 0; j < 30; j++) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				if (commonObj.getText("personId").equals(pName)) {
					pageNavigationWorks = false;
					break;
				} else
					pName = commonObj.getText("personId");
			}
		}
		return pageNavigationWorks;
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

	public boolean diffSearchOnPersonHp(String searchType) {
		boolean searchWorks = true;
		WebDriverWait wait = new WebDriverWait(driver, 9);

		switch (searchType) {

		case "byPersonId":
			String personId = commonObj.getText("personId");
			commonObj.type("searchByPersonID", personId);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByPersonId = commonObj
					.getWebElements("numberOfRecordsDispOnPersonHp");
			int recordCount = recordsByPersonId.size();
			if (!(recordCount == 1 && commonObj.getText("getPersonId").equals(
					personId)))
				searchWorks = false;
			break;

		case "byFirstName":
			driver.navigate().refresh();
			String getFName = "";
			String firstName = commonObj.getText("getPersonFname");
			firstName = firstName.toLowerCase();
			commonObj.type("searchByFirstName", firstName);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByFirstName = commonObj
					.getWebElements("numberOfRecordsDispOnPersonHp");
			for (WebElement record : recordsByFirstName) {
				getFName = record.findElement(By.xpath("./td[3]")).getText();
				getFName = getFName.toLowerCase();
				if (!getFName.contains(firstName)) {
					searchWorks = false;
					break;
				}
			}
			// if(commonObj.isElementPresentAndEnable("nextNav")){
			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				recordsByFirstName = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : recordsByFirstName) {
					getFName = record.findElement(By.xpath("./td[3]"))
							.getText();
					getFName = getFName.toLowerCase();
					if (!getFName.contains(firstName)) {
						searchWorks = false;
						break;
					}
				}
			}
			break;

		case "byLastName":
			driver.navigate().refresh();
			String getLName = "";
			String lastName = commonObj.getText("getPersonLname");
			lastName = lastName.toLowerCase();
			commonObj.type("searchByLastName", lastName);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByLastName = commonObj
					.getWebElements("numberOfRecordsDispOnPersonHp");
			for (WebElement record : recordsByLastName) {
				getLName = record.findElement(By.xpath("./td[4]")).getText();
				getLName = getLName.toLowerCase();
				if (!getLName.contains(lastName)) {
					searchWorks = false;
					break;
				}
			}
			// if(commonObj.isElementPresentAndEnable("nextNav")){
			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				recordsByLastName = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : recordsByLastName) {
					getLName = record.findElement(By.xpath("./td[4]")).getText();
					getLName = getLName.toLowerCase();
					if (!getLName.contains(lastName)) {
						searchWorks = false;
						break;
					}
				}
			}
			break;

		case "byTitle":
			driver.navigate().refresh();
			String title = "";
			String getTitle = "";
			if (title.equals("")) {
				List<WebElement> allTitles = commonObj
						.getWebElements("getAllTitles");
				for (WebElement record : allTitles) {
					if (!record.getText().isEmpty()) {
						title = record.getText();
						title = title.toLowerCase();
						break;
					}
				}
				commonObj.click("nextNav");
			}

			commonObj.type("searchByTitle", title);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByTitle = commonObj
					.getWebElements("numberOfRecordsDispOnPersonHp");
			for (WebElement record : recordsByTitle) {
				getTitle = record.findElement(By.xpath("./td[5]")).getText();
				getTitle = getTitle.toLowerCase();
				if (!getTitle.contains(title)) {
					searchWorks = false;
					break;
				}
			}

			for (int i=0; i<40 && commonObj.isClickable("nextNav");i++) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				recordsByTitle = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : recordsByTitle) {
					getTitle = record.findElement(By.xpath("./td[5]"))
							.getText();
					getTitle = getTitle.toLowerCase();
					if (!getTitle.contains(title)) {
						searchWorks = false;
						break;
					}
				}
			}
			break;

		case "byCompany":
			driver.navigate().refresh();
			String company = "";
			if (company.equals("")) {
				List<WebElement> allComps = commonObj
						.getWebElements("getAllCompNames");
				for (WebElement record : allComps) {
					if (!record.getText().isEmpty()) {
						company = record.getText();
						if(company.contains(";")){
						company=company.split(";")[0];
						}
						break;
					}
				}
				commonObj.click("nextNav");
			}

			commonObj.type("searchByComp", company);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			List<WebElement> recordsByComp = commonObj
					.getWebElements("numberOfRecordsDispOnPersonHp");
			for (WebElement record : recordsByComp) {
				if (!record.findElement(By.xpath("./td[6]")).getText()
						.contains(company)) {
					searchWorks = false;
					break;
				}
			}

			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				recordsByComp = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : recordsByComp) {
					if (!record.findElement(By.xpath("./td[6]")).getText()
							.contains(company)) {
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
					.getWebElements("numberOfRecordsDispOnPersonHp");
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
						.getWebElements("numberOfRecordsDispOnPersonHp");
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
						.getWebElements("numberOfRecordsDispOnPersonHp");
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
							.getWebElements("numberOfRecordsDispOnPersonHp");
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
				logger.info("Some exception occurred:"+e);
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
					.getWebElements("numberOfRecordsDispOnPersonHp");
			totalRecords = allRecords.size();
			while (commonObj.isClickable("nextNav")) {
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
				allRecords = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				totalRecords = totalRecords + allRecords.size();
			}
			for (int i = 0; i < 3; i++) {
				if (pubValue[i].equals("")) {
					commonObj.selectDropdwonElement("pubDropdown", pubValue[i]);
					wait.until(ExpectedConditions
							.invisibilityOfElementLocated(By
									.id("example_processing")));
					List<WebElement> records = commonObj
							.getWebElements("numberOfRecordsDispOnPersonHp");
					count = records.size();
					while (commonObj.isClickable("nextNav")) {
						commonObj.click("nextNav");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.id("example_processing")));
						records = commonObj
								.getWebElements("numberOfRecordsDispOnPersonHp");
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
							.getWebElements("numberOfRecordsDispOnPersonHp");
					for (WebElement record : publishedRecords) {
						if (!record.findElement(By.xpath("./td[10]")).getText()
								.contains(pubValue[i])) {
							searchWorks = false;
							break;
						}
					}
					// while (commonObj.isClickable("nextNav"))
					for (int j = 0; j < 5 && commonObj.isClickable("nextNav"); j++) {
						commonObj.click("nextNav");
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.id("example_processing")));
						publishedRecords = commonObj
								.getWebElements("numberOfRecordsDispOnPersonHp");
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

		}
		return searchWorks;
	}

	public boolean filterRecordsByUpdatedDate() {
		boolean defaultFilter = true;
		WebDriverWait wait = new WebDriverWait(driver, 12);
		try {
			driver.navigate().refresh();
			for (int j = 0; j < 5; j++) {
				String firstUpdatedDate = commonObj.getText("updatedDate");
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date updatedDate = dateFormat.parse(firstUpdatedDate);
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
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
			logger.info("Some exception occurred: "+e);
		}
		return defaultFilter;
	}

	public boolean filterByFunctionality(String byFilter) {
		boolean filterWorks = true;
		WebDriverWait wait = new WebDriverWait(driver, 12);
		driver.navigate().refresh();
		switch (byFilter) {
		case "filterOnPersonId":
			commonObj.click("clickOnPersonIdColumn"); // To sort in ascending
														// order
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			commonObj.click("clickOnPersonIdColumn"); // To sort in descending
														// order
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 5; j++) {
				String personID = commonObj.getText("firstPersonID");
				int personId1 = Integer.parseInt(personID);
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : filteredRecords) {
					String currentPersonId = record.findElement(
							By.xpath("./td[2]")).getText();
					int personId2 = Integer.parseInt(currentPersonId);
					if (personId1 < personId2) {
						filterWorks = false;
						break;
					} else {
						personId1 = personId2;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
			break;

		case "filterOnFirstName":
			commonObj.click("clickOnPersonFirstNameColumn"); 
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 5; j++) {
				String firstName1 = commonObj.getText("getPersonFirstName");
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : filteredRecords) {
					String firstName2 = record.findElement(By.xpath("./td[3]"))
							.getText();
					if (firstName1.compareTo(firstName2) > 0) {
						filterWorks = false;
						break;
					} else {
						firstName1 = firstName2;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
			break;

		case "filterOnLastName":
			commonObj.click("clickOnPersonLastNameColumn"); // To sort in
															// ascending
			// order
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 5; j++) {
				String lastName1 = commonObj.getText("getPersonLastName");
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
				for (WebElement record : filteredRecords) {
					String lastName2 = record.findElement(By.xpath("./td[4]"))
							.getText();
					if (lastName1.compareTo(lastName2) > 0) {
						filterWorks = false;
						break;
					} else {
						lastName1 = lastName2;
					}
				}
				commonObj.click("nextNav");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.id("example_processing")));
			}
			break;

		case "filterOnUpdatedUser":
			commonObj.click("clickOnUpdatedUserColumn"); // To sort in ascending
															// order
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.id("example_processing")));
			for (int j = 0; j < 12; j++) {
				String userName1 = commonObj.getText("firstUpdatedUser");
				List<WebElement> filteredRecords = commonObj
						.getWebElements("numberOfRecordsDispOnPersonHp");
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
