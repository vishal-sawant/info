package common.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import mx4j.log.Logger;

import org.openqa.selenium.By;

public class WebElementMapping {

	// property file and provide the locator information to the test.

	Properties properties;
	Logger logger;

	public WebElementMapping(String mapFile) {
		properties = new Properties();
		try {
			FileInputStream Master = new FileInputStream(mapFile);
			properties.load(Master);
			Master.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public By getLocator(String ElementName) throws Exception {

		// Read value using the logical name as Key
		String locator = properties.getProperty(ElementName);
		// Split the value which contains locator type and locator value
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		// Return a instance of By class based on type of locator
		String locSwitch = locatorType.toLowerCase();

		switch (locSwitch) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
		case "tag":
			return By.className(locatorValue);
		case "linktext":
		case "link":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
		case "css":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:		
				throw new Exception("Locator type '" + locatorType
						+ "' not defined!!");
		}
	}
}
