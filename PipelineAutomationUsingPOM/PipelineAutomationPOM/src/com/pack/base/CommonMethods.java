package com.pack.base;

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
			System.out.println("Exception occured while clicking on an element: "+super.getLocator(locatorName));
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
			driver.findElement(super.getLocator(locatorName)).click();
		} catch (Exception e) {
			System.out.println("Exception occured while finding out an element: "+super.getLocator(locatorName));
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
			System.out.println("Exception occured while selecting an drop down element: "+super.getLocator(locatorName));
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
			System.out.println("Exception occured while clearing the text of: "+super.getLocator(locatorName));
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
			System.out.println("Exception occured while entering text "+inputText+ "in to: "+super.getLocator(locatorName));
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
			System.out.println("Some error has occured to find out the text: "
					+ super.getLocator(locatorName));

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
			sizeOfWebElement = driver.findElements(super.getLocator(locatorName)).size();
		} catch (Exception e) {
			System.out.println("Some error has occured while finding out size of a WebElement: "
					+ super.getLocator(locatorName));

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
	public boolean isElementPresentAndDisplayed(String locatorName) {
		boolean flag = false;
		try {
			flag = driver.findElement(super.getLocator(locatorName))
					.isDisplayed();
		} catch (Exception e) {
			System.out.println("Unable to locate an element: "
					+ super.getLocator(locatorName));

		}
		return flag;
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
			System.out.println("Unable to find out an Web element: "
					+super.getLocator(locatorName));
		}
		return element;
	}

	/**
	 * Checks if check-box on the page is selected or not
	 * 
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isSelected(String locatorName) throws Exception {
		boolean flag = false;
		try {
			flag = (driver.findElement(super.getLocator(locatorName)).isSelected());
		} catch (Exception e) {
			System.out.println("Unable to confirm whether an element is selected or not: "
					+ super.getLocator(locatorName));
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
			System.out.println("Exception occured while checking page is loaded or not using page title: "
					+pageTitle);
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
			System.out.println("Exception occured while verifying the text: "+text+" is present or not");
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
			System.out.println("Exception occured while verifying whether element is present or not: "
					+ super.getLocator(locatorName));
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
			System.out.println("Exception occured while opening page: "+pageUrl+" to test");
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
			System.out.println("Exception occured while navigating to page: "+pageUrl+" to test");
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
		String currentPageUrl =null;
		try{
			currentPageUrl = driver.getCurrentUrl();
			return currentPageUrl;
		}catch(Exception e){
			System.out.println("Exception occured while getting the current page url");
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
