package common.classes;

import static org.testng.Assert.fail;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.Select;
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
			// + super.getLocator(locatorName));
			driver.findElement(super.getLocator(locatorName)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To access Subscribe link on the Street home page.
	 * 
	 * @param Locator
	 *            Name
	 * @throws Exception
	 */
	public void accessSignUpLink(String subscribeLocator, String signUpServiceLocator) throws Exception {
		  	WebElement elementSubscribe = driver.findElement(super.getLocator(subscribeLocator));
	        Mouse mouse =((HasInputDevices) driver).getMouse();
	        Locatable hoverItem = (Locatable) elementSubscribe;
	        mouse.mouseMove(hoverItem.getCoordinates());
	        WebElement signupServiceLink = driver.findElement(super.getLocator(signUpServiceLocator));
	        Locatable clickItem =  (Locatable) signupServiceLink;
	        mouse.mouseDown(clickItem.getCoordinates());
	        mouse.mouseUp(clickItem.getCoordinates());
	}
	/**
	 * Find an element using By object.
	 * 
	 * @param Locator
	 *            Name
	 */
	public void findElement(String locatorName) {
		try {
			driver.findElement(super.getLocator(locatorName)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Select an element from drop down field.
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			driver.findElement(super.getLocator(locatorName)).sendKeys(
					inputText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets text from the element found using By object.
	 * 
	 * @param Locator
	 *            Name
	 * @throws Exception
	 */
	public String getText(String locatorName) throws Exception {

		return driver.findElement(super.getLocator(locatorName)).getText();
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
		return driver.findElements(super.getLocator(locatorName)).size();
	}

	/**
	 * Checks if the element is in the DOM and displayed.
	 * 
	 * @param by
	 *            - selector to find the element
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isElementPresentAndDisplayed(String locatorName)
			throws Exception {
		try {
			return driver.findElement(super.getLocator(locatorName))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
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
	public WebElement getWebElement(String locatorName) throws Exception {
		return driver.findElement(super.getLocator(locatorName));
	}

	/**
	 * Checks if check-box on the page is selected or not
	 * 
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isSelected(String locatorName) throws Exception {
		return (driver.findElement(super.getLocator(locatorName)).isSelected());
	}

	/**
	 * Check if page is loaded by comparing the expected page-title with an
	 * actual page-title.
	 * 
	 * @return
	 */
	public boolean isPageLoaded(String pageTitle) {
		return (driver.getTitle().contains(pageTitle));
	}

	/**
	 * Method checks the presence of text on the page.
	 * 
	 * @param text
	 * @return
	 */
	public boolean isTextPresent(String text) {
		return driver.getPageSource().contains(text);
	}

	/**
	 * Method checks the presence of element in the page.
	 * 
	 * @param by
	 * @return
	 * @throws Exception
	 */
	public boolean isElementPresent(String locatorName) throws Exception {
		try {
			driver.findElement(super.getLocator(locatorName));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
			e.printStackTrace();
		}
	}

	/**
	 * Method to navigate to specified page.
	 * 
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public void naviagetTo(String pageUrl) throws Exception {
		try {
			driver.navigate().to(pageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		for (int second = 0;; second++) {
			if (second >= time) {
				fail("Timeout, Not able to find text: " + text + " on page : "
						+ driver.getTitle());
			}
			try {
				if (isTextPresent(text)) {
					break;
				}
			} catch (Exception e) {
				logger.info("Required text was not found due to error: "
						+ e.getMessage());
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
		for (int second = 0;; second++) {
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
		for (int second = 0;; second++) {
			if (second >= time) {
				fail("Timeout, Not able to find element: " + locatorName
						+ " on page : " + driver.getTitle());
			}
			try {
				if (isElementPresent(locatorName)) {
					break;
				}
			} catch (Exception e) {
				logger.info("Required element was not found due to error: "
						+ e.getMessage());
			}
			Sleeper.sleepTight(500);
		}
	}

	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}

	public void waitForTextPresent(String text) {
		int time = 20;
		for (int second = 0;; second++) {
			if (second >= time) {
				fail("Timeout, Not able to find text: " + text + " on page : "
						+ driver.getTitle());
			}
			try {
				if (isTextPresent(text)) {
					break;
				}
			} catch (Exception e) {
				logger.info("Required text was not found due to error: "
						+ e.getMessage());
			}
			Sleeper.sleepTight(500);
		}
	}
}
