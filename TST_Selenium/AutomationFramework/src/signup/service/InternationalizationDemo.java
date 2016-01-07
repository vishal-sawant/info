package signup.service;

import org.openqa.selenium.By;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.annotations.Test;

import common.classes.Internationalization;

public class InternationalizationDemo extends ConfigureTST {

		//WebDriver driver;

		@Test(priority = 0)
		public void launchURL() {
			driver.navigate().to("http://www.google.jp");

		}

		@Test(priority = 1)
		public void searchInLanguage() {
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Sleeper.sleepTight(7000);
			Internationalization.runNative2Ascii(""
					+ ".\\Resources\\japanese.txt", ".\\Resources\\output.txt");
			driver.findElement(By.xpath("//div[@id='sb_ifc0']//input"))
					.sendKeys(
							Internationalization
									.readParsedResourceFile(".\\Resources\\output.txt"));
			Sleeper.sleepTight(4000);
		}
	}

