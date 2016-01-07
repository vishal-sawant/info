package common.classes;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

/**
 * Class which contains utility methods.
 * 
 * 
 */

public class SystemUtil {

	public String OsName;
	public String BrowserVersion;
	public Logger logger;

	public SystemUtil() {
		Logs logs = new Logs();
		logger = logs.getLogger();
	}

	private static enum Os {
		WINDOWS("Windows"), MAC("Mac"), LINUX("Linux");
		private String os;

		private String getOs() {
			return os;
		}

		Os(String os) {
			this.os = os;
		}
	}

	/**
	 * Method to detect the OS and its version
	 */
	public void detectOSVersion() {
		String os = null;
		if (System.getProperty("os.name").contains(Os.WINDOWS.getOs())) {
			os = "WINDOWS";
		} else if (System.getProperty("os.name").contains(Os.MAC.getOs())) {
			os = "MAC";
		} else if (System.getProperty("os.name").contains(Os.LINUX.getOs())) {
			os = "Linux";
		} else {
			Assert.fail("Could not find OS Version for your operating system !");
		}
		String OsVersion = System.getProperty("os.version");
		if (os.equals("WINDOWS")) {
			switch (OsVersion) {
			case "5.0":
				OsVersion = "2000";
				break;
			case "5.1":
				OsVersion = "XP";
				break;
			case "5.2":
				OsVersion = "XP 64-Bit Edition";
				break;
			case "6.0":
				OsVersion = "Vista OR Server 2008";
				break;
			case "6.1":
				OsVersion = "7";
				break;
			case "6.2":
				OsVersion = "8";
				break;
			case "6.3":
				OsVersion = "8.1";
				break;
			case "10.0":
				OsVersion = "10";
				break;
			}
		}
		OsName = os + " " + OsVersion;
		logger.info("Test executed on Operating System : " + OsName);
	}

	public void detectBrowserVersion(WebDriver driver) {
		Capabilities capabilities = ((RemoteWebDriver) driver)
				.getCapabilities();
		String browserName = capabilities.getBrowserName();
		String browserVersion = capabilities.getVersion().toString();
		BrowserVersion = browserName + " " + browserVersion;
		logger.info("Test executed on Browser : " + BrowserVersion);
	}
}
