package base;

import static org.testng.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.HasInputDevices;

public class CommonMethods extends WebElementMapping {
	protected WebDriver driver;
	protected Logger logger;

	public CommonMethods(WebDriver driver, String mapFile) {
		super(mapFile);
		this.driver = driver;
	}

	/**
	 * Clicks element found using By object.
	 * 
	 * @param Locator
	 *            Name
	 * @throws Exception
	 */
	public void click(String locatorName) {
		try {
			// System.out.println("Driver: " + driver + " And Locator is : "
			// + e.getMessage());
			driver.findElement(super.getLocator(locatorName)).click();
		} catch (Exception e) {
			logger.info("Exception occured while clicking on an element: ", e);
		}
	}

	/**
	 * To access Subscribe link on the Street home page.
	 * 
	 * @param Locator
	 *            Name
	 * @throws Exception
	 */
	public void accessSignUpLink(String subscribeLocator,
			String signUpServiceLocator) throws Exception {
		if (CommonConstant.browser.equalsIgnoreCase("IE")) {
			click(subscribeLocator);
			waitForPageToLoad("20");
			click(signUpServiceLocator);
		} else {
			WebElement elementSubscribe = driver.findElement(super
					.getLocator(subscribeLocator));
			Mouse mouse = ((HasInputDevices) driver).getMouse();
			Locatable hoverItem = (Locatable) elementSubscribe;
			mouse.mouseMove(hoverItem.getCoordinates());
			// Thread.sleep(3000);
			WebElement signupServiceLink = driver.findElement(super
					.getLocator(signUpServiceLocator));
			Locatable clickItem = (Locatable) signupServiceLink;
			mouse.mouseDown(clickItem.getCoordinates());
			Thread.sleep(2000);
			mouse.mouseUp(clickItem.getCoordinates());
			Thread.sleep(2000);
		}

	}

	/**
	 * Find an element using By object.
	 * 
	 * @param Locator
	 *            Name
	 */
	public void findElement(String locatorName) {
		try {
			driver.findElement(super.getLocator(locatorName));
		} catch (Exception e) {
			logger.info("Exception occured while finding out an element: ", e);
		}
	}

	/**
	 * Select an element from drop down field by using selectByValue.
	 * 
	 * @param Locator
	 *            Name
	 */
	public void selectDropdwonElement(String locatorName, String value) {
		try {
			Select dropdown = new Select(driver.findElement(super
					.getLocator(locatorName)));
			dropdown.selectByValue(value);
		} catch (Exception e) {
			logger.info(
					"Exception occured while selecting an drop down element: ",
					e);
		}
	}

	/**
	 * Select an element from drop down field by using selectByVisibleText.
	 * 
	 * @param Locator
	 *            Name
	 */
	public void selDropdownElementUsingVisibleText(String locatorName,
			String value) {
		try {
				Select dropdown = new Select(driver.findElement(super
					.getLocator(locatorName)));				
			dropdown.selectByVisibleText(value);
				
		} catch (Exception e) {
			logger.info(
					"Exception occured while selecting an drop down element: ",
					e);
		}
	}
	
	/**
	 * Clears the text for provided element
	 * 
	 * @param Locator
	 *            Name
	 */
	public void clear(String locatorName) {
		try {
			driver.findElement(super.getLocator(locatorName)).clear();
		} catch (Exception e) {
			logger.info("Exception occured while clearing the text of: ", e);

		}
	}

	/**
	 * Send text keys to the element found using By object.
	 * 
	 * @param Locator
	 *            Name
	 * @param inputText
	 */
	public void type(String locatorName, String inputText) {
		try {
			driver.findElement(super.getLocator(locatorName)).clear();
			driver.findElement(super.getLocator(locatorName)).sendKeys(
					inputText);
		} catch (Exception e) {
			logger.info("Exception occured while entering text: ", e);
		}
	}

	/**
	 * Gets text from the element found using By object.
	 * 
	 * @param Locator
	 *            Name
	 * @throws Exception
	 */
	public String getText(String locatorName) {
		String textToReturn = null;
		try {
			textToReturn = driver.findElement(super.getLocator(locatorName))
					.getText();
		} catch (Exception e) {
			logger.info("Some error has occured to find out the text: ", e);

		}
		return textToReturn;
	}

	/**
	 * Gets page title using driver.
	 * 
	 * Name
	 * 
	 * @throws Exception
	 */

	public String getPageTitle() {
		return driver.getTitle();
	}

	/**
	 * Gets count of element in the page using By object.
	 * 
	 * @param Locator
	 *            Name
	 */
	public long getcount(String locatorName) throws Exception {
		long sizeOfWebElement = 0;
		try {
			sizeOfWebElement = driver.findElements(
					super.getLocator(locatorName)).size();
		} catch (Exception e) {
			logger.info("Some error has occured to find out the text: ", e);

		}
		return sizeOfWebElement;
	}

	/**
	 * Checks if the element is in the DOM and displayed.
	 * 
	 * @param by
	 *            - selector to find the element
	 * @return true or false
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean isElementPresentAndDisplayed(String locatorName) {
		boolean flag = false;
		try {
			int time = 5;
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOfElementLocated(super
					.getLocator(locatorName)));
			flag = true;
			;
		} catch (Exception e) {
			logger.info("Some exception occurred:", e);
			flag = false;
		} finally {
			return flag;
		}
	}

	/**
	 * Checks if the element is click able or not.
	 * 
	 * @param by
	 *            - selector to find the element
	 * @return true or false
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean isClickable(String locatorName) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(super
					.getLocator(locatorName)));
			flag = true;
		} catch (Exception e) {
			logger.info("Some exception occurred:", e);
			flag = false;
		} finally {
			return flag;
		}

	}

	/**
	 * Returns the first WebElement using the given method. It shortens
	 * "driver.findElement(By)".
	 * 
	 * @param Locator
	 *            Name element locater.
	 * @return the first WebElement
	 * @throws Exception
	 */
	public WebElement getWebElement(String locatorName) {
		WebElement element = null;
		try {
			element = driver.findElement(super.getLocator(locatorName));
		} catch (Exception e) {
			logger.info("Unable to find out an Web element: " + e);
		}
		return element;
	}

	/**
	 * Returns list of WebElements using the given method. It uses
	 * "driver.findElements(By)".
	 * 
	 * @param Locator
	 *            Name element locater.
	 * @return the first WebElement
	 * @throws Exception
	 */
	public List<WebElement> getWebElements(String locatorName) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(super.getLocator(locatorName));
		} catch (Exception e) {
			logger.info("Unable to find out an Web elements: ", e);
		}
		return elements;
	}

	/**
	 * Code to switch to an iframe window
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public void switchToIframe(String locatorName) {
		try {
			driver.switchTo().frame(
					driver.findElement(super.getLocator(locatorName)));
		} catch (Exception e) {
			logger.info(
					"Unable to switch to a iFrame window: " + e.getMessage(), e);
		}
	}

	/**
	 * Code to switch to an iframe window using iframe ID
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public void switchToIframeUsingFrameID(String iframeId) {
		try {
			driver.switchTo().frame(iframeId);

		} catch (Exception e) {
			logger.info("Unable to switch to a iFrame window: " + iframeId, e);
		}
	}

	/**
	 * Code to switch to an iframe window using index
	 * 
	 * @throws Exception
	 */
	public void switchToIframeUsingIndex(int x) {
		try {
			driver.switchTo().frame(x);
		} catch (Exception e) {
			logger.info(
					"Unable to switch to a iFrame window using frame index: "
							+ x, e);
		}
	}

	/**
	 * Checks if check-box on the page is selected or not
	 * 
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isSelected(String locatorName) {
		boolean flag = false;
		try {
			flag = (driver.findElement(super.getLocator(locatorName))
					.isSelected());
		} catch (Exception e) {
			logger.info(
					"Unable to confirm whether an element is selected or not: ",
					e);
		}
		return flag;
	}

	/**
	 * Check if page is loaded by comparing the expected page-title with an
	 * actual page-title.
	 * 
	 * @return
	 */
	public boolean isPageLoaded(String pageTitle) {
		boolean flag = false;
		try {
			flag = (driver.getTitle().contains(pageTitle));
		} catch (Exception e) {
			logger.info(
					"Exception occured while checking page is loaded or not using page title: "
							+ pageTitle, e);
		}
		return flag;

	}

	/**
	 * Method checks the presence of text on the page.
	 * 
	 * @param text
	 * @return
	 */
	public boolean isTextPresent(String text) {
		boolean flag = false;
		try {
			flag = driver.getPageSource().contains(text);
		} catch (Exception e) {
			logger.info("Exception occured while verifying the text: " + text
					+ " is present or not", e);
		}
		return flag;
	}

	/**
	 * Method checks the presence of element in the page.
	 * 
	 * @param by
	 * @return
	 * @throws Exception
	 */
	public boolean isElementPresent(String locatorName) throws Exception {
		boolean flag = false;
		try {
			driver.findElement(super.getLocator(locatorName));
			return true;
		} catch (Exception e) {
			logger.info(
					"Exception occured while verifying whether element is present or not: ",
					e);
		}
		return flag;
	}

	/**
	 * Method to open page under test.
	 * 
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public void openPageToTest(String pageUrl) throws Exception {
		try {
			driver.get(pageUrl);
		} catch (Exception e) {
			logger.info("Exception occured while opening page: " + pageUrl
					+ " to test", e);
		}
	}

	/**
	 * Method to navigate to specified page.
	 * 
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public void naviagetTo(String pageUrl) {
		try {
			driver.navigate().to(pageUrl);
		} catch (Exception e) {
			logger.info("Exception occured while navigating to page: "
					+ pageUrl + " to test", e);
		}
	}

	/**
	 * Method to get current page url
	 * 
	 * @param String
	 * @return
	 * @throws Exception
	 */

	public String getPageUrl() {
		String currentPageUrl = null;
		try {
			currentPageUrl = driver.getCurrentUrl();
			return currentPageUrl;
		} catch (Exception e) {
			logger.info("Exception occured while getting the current page url",
					e);
		}
		return currentPageUrl;
	}

	/**
	 * Method checks the presence of element in the DOM.
	 * 
	 * @param cssSelector
	 *            element locater
	 * @return WebElement
	 */
	/*
	 * public boolean isElementPresent(String cssSelector) { try {
	 * driver.findElement(By.cssSelector(cssSelector)); return true; } catch
	 * (NoSuchElementException e) { return false; } }
	 */

	/**
	 * Method to check if required Text is present on the page.
	 * 
	 * @param text
	 *            : Text to be searched on the page
	 * @throws InterruptedException
	 */
	public void checkForTextPresent(String text) throws InterruptedException {
		int time = 20;
		for (int second = 0; second <= time; second++) {
			if (second >= time) {
				fail("Timeout, Not able to find text: " + text + " on page : "
						+ driver.getTitle());
			}
			try {
				if (isTextPresent(text)) {
					break;
				}
			} catch (Exception e) {
				logger.info("Required text was not found due to error: ", e);
			}
			Sleeper.sleepTight(500);
		}
	}

	/**
	 * Method waits for page to get loaded.
	 * 
	 * @param timeOutInSeconds
	 */
	public void waitForPageToLoad(String timeOutInSeconds) {
		String windowTitle = driver.getTitle();
		int time = Integer.parseInt(timeOutInSeconds);
		int pageLength = 0;
		for (int second = 0; second <= time; second++) {
			if (second >= time) {
				fail("Timeout... Page load could not complete in "
						+ timeOutInSeconds + " seconds");
			}
			if (pageLength == driver.getPageSource().length()
					&& windowTitle != driver.getTitle()) {
				break;
			}
			Sleeper.sleepTight(500);
			pageLength = driver.getPageSource().length();
		}
	}

	public void waitForElementPresent(String locatorName) {
		int time = 20;
		for (int second = 0; second <= time; second++) {
			if (second >= time) {
				fail("Timeout, Not able to find element: " + locatorName
						+ " on page : " + driver.getTitle());
			}
			try {
				if (isElementPresent(locatorName)) {
					break;
				}
			} catch (Exception e) {
				logger.info("Required element was not found due to error: ", e);
			}
			Sleeper.sleepTight(500);
		}
	}

	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}

	public void waitForTextPresent(String text) {
		int time = 20;
		for (int second = 0; second <= time; second++) {
			if (second >= time) {
				fail("Timeout, Not able to find text: " + text + " on page : "
						+ driver.getTitle());
			}
			try {
				if (isTextPresent(text)) {
					break;
				}
			} catch (Exception e) {
				logger.info("Required text was not found due to error: ", e);
			}
			Sleeper.sleepTight(500);
		}
	}
}
