package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.pack.base.CommonMethods;
import com.pack.base.LaunchBrowser;

public class TheDealPage {

	private WebDriver driver;
	protected CommonMethods commonObj = LaunchBrowser.getCommonMethodObj();

	public TheDealPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTheDealPageTitle() {
		return commonObj.getPageTitle();
	}
}
