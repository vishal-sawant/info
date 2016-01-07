package com.pack.common.pageobjects;

import org.openqa.selenium.WebDriver;
import com.pack.base.CommonConstant;
import com.pack.base.CommonMethods;
import com.pack.base.LaunchBrowser;
import com.pack.base.ReadWriteExcel;

public class LoginPage {
	protected CommonMethods commonObj;
	protected WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage clickLoginBtn() {
		try {
			ReadWriteExcel.setExcelFile(CommonConstant.PIPELINE_DATA_PATH,
					CommonConstant.PIPELINE_USERS);
			String userName = ReadWriteExcel.getCellData(1, 0);
			String password = ReadWriteExcel.getCellData(1, 1);
			commonObj = LaunchBrowser.getCommonMethodObj();
			commonObj.click("userName");
			commonObj.type("userName", userName);
			commonObj.click("password");
			commonObj.type("password", password);
			commonObj.click("submitButton");
		} catch (Exception e) {
			System.out.println("Some error has occured here " + e.getMessage());
		}
		return new HomePage(driver);
	}

	public String getLoginPageTitle() {
		commonObj = LaunchBrowser.getCommonMethodObj();
		String title = commonObj.getPageTitle();
		return title;
	}

	public boolean verifyLoginPageTitle() {
		String expectedPageTitle = "Welcome to The Deal Pipeline login";
		return getLoginPageTitle().contains(expectedPageTitle);
	}

}
