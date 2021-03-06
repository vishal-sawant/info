package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

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
			logger.info("Some Exception occurred:",e);
		}
	}

	public By getLocator(String ElementName)  {
		
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
		case "cssSelector":
		case "css":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:		
			try {
				throw new Exception("Locator type '" + locatorType
						+ "' not defined!!");
			} catch (Exception e) {
				logger.info("Exception occured: "+e.getMessage());
			}
			return By.id("");
		}	
	}
}
