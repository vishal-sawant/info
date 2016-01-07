package com.pack.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class LaunchBrowser {
	// public static WebDriver driver;
	public WebDriver driver;
	protected Logger logger;
	static CommonMethods CommonMethodObj=null;
	
	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite() {
		logger = getLogger();
		logger.info("Loaded property files and created logger instance");
		
		if (this.driver == null) {
			this.driver = setupSeleniumWebDriver(CommonConstant.browser);
		}
	}

	public WebDriver setupSeleniumWebDriver(String browser) {
		try {
			if (browser.equals("FF")) {
				logger.info("Setting up FireFox driver.");
				driver = new FirefoxDriver();
			} else if (browser.equals("IE")) {
				System.setProperty("webdriver.ie.driver",
						"./Resources/IEDriverServer.exe");
				logger.info("Setting up Internet Explorer driver.");
				driver = new InternetExplorerDriver();
			} else if (browser.equals("CH")) {
				System.setProperty("webdriver.chrome.driver",
						"./Resources/chromedriver.exe");
				logger.info("Setting up Chrome driver.");
				driver = new ChromeDriver();
			}
			CommonMethodObj = new CommonMethods(driver,
					CommonConstant.Config_File_path);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			
			Listener.driver = driver;
			// Launching page under test
			driver.get(CommonConstant.PIPELINEURL);
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	@AfterClass(alwaysRun = true)
	public void setupAfterSuite() throws InterruptedException, IOException {
		driver.manage().deleteAllCookies();
		SystemUtil systutil = new SystemUtil();
		systutil.detectOSVersion();
		systutil.detectBrowserVersion(driver);
		Thread.sleep(3000);
		/*
		 * Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		 * String browserName = cap.getBrowserName().toLowerCase(); String os =
		 * cap.getPlatform().toString(); String v = cap.getVersion().toString();
		 * logger.info("Operating System : "+os);
		 * logger.info("Browser Name and Version : "+browserName+" "+v);
		 */
		driver.quit();
		/*
		 * Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		 * Thread.sleep(3000);
		 * Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
		 * Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
		 */
	}

	public Logger getLogger() {
		Logs logs = new Logs();
		return logs.getLogger();
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	/*public void setCommonMethodObj(CommonMethods obj) {
		this.CommonMethodObj=obj;
	}*/
	
	public static CommonMethods getCommonMethodObj(){
		return CommonMethodObj;
	}
}
