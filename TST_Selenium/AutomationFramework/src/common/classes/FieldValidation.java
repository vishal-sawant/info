package common.classes;

import org.openqa.selenium.WebDriver;

public class FieldValidation {

	public String userEmail = ("testuser" + Math.random())
			.concat("@thestreet.com");

	public String VerifyBlankFormSubmission(WebDriver driver,
			String Validate_SignUpService, String locatorName) throws Exception {

		String actualText;
		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		CM.waitForPageToLoad("10");
		CM.click("continue_site_link");
		// CM.waitForPageToLoad("70");
		Thread.sleep(5000);
		switch (Validate_SignUpService) {

		case "cc_link":
			CM.accessSignUpLink("subscribe_link", "cc_link");
			CM.waitForPageToLoad("10");
			CM.click("cc_button");
			// MLP ==> "Begin your exclusive membership//
			CM.click("membership_button");
			break;

		case "aap_link":
			CM.accessSignUpLink("subscribe_link", "aap_link");
			CM.waitForPageToLoad("10");
			CM.click("portfolio_button");
			CM.clear("email_add");
			CM.type("email_add", userEmail);
			CM.click("submit_button");
			break;

		case "aao_link":
			CM.accessSignUpLink("subscribe_link", "aao_link");
			CM.waitForPageToLoad("10");
			CM.click("free_trial_button");
			CM.clear("email_add");
			CM.type("email_add", userEmail);
			CM.click("submit_button");
			CM.waitForPageToLoad("10");
			break;

		case "dst_link":
			CM.accessSignUpLink("subscribe_link", "dst_link");
			CM.waitForPageToLoad("10");
			CM.click("free-trial_button");
			CM.clear("email_add");
			CM.type("email_add", userEmail);
			CM.type("fname", "testfn");
			CM.type("lname", "testln");
			CM.click("submit_button");
			break;

		case "dsa_link":
			CM.accessSignUpLink("subscribe_link", "dsa_link");
			CM.waitForPageToLoad("10");
			CM.click("getstarted_button1");
			CM.click("getstarted_button2");
			CM.click("email_add");
			CM.type("email_add", userEmail);
			CM.type("fname", "testfn");
			CM.type("lname", "testln");
			CM.click("submit_button");
			break;
		}

		CM.click("submit_button");
		CM.waitForPageToLoad("20");
		actualText = CM.getText(locatorName);
		CM.deleteCookies();
		try {
			CM.naviagetTo(CommonConstant.streeturl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualText;
	}

	public String verifyPasswordAccount(WebDriver driver, String locatorName,
			String Validate_SignUpFields, String validateField)
			throws Exception {
		String actualText;
		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		CM.waitForPageToLoad("20");
		CM.click("continue_site_link");
		Thread.sleep(2000);

		switch (Validate_SignUpFields) {

		case "cc_link":
			CM.accessSignUpLink("subscribe_link", "cc_link");
			CM.waitForPageToLoad("10");
			CM.click("cc_button");
			// MLP ==> "Begin your exclusive membership//
			CM.click("membership_button");
			break;

		case "aap_link":
			CM.accessSignUpLink("subscribe_link", "aap_link");
			CM.waitForPageToLoad("10");
			CM.click("portfolio_button");
			if (!locatorName.equalsIgnoreCase("error_invalidEmail")){
				CM.click("email_add");
				CM.type("email_add", userEmail);
				CM.click("submit_button");
			}
			break;

		case "aao_link":
			CM.accessSignUpLink("subscribe_link", "aao_link");
			CM.click("free_trial_button");
			if (!locatorName.equalsIgnoreCase("error_invalidEmail")) {
				CM.click("email_add");
				CM.type("email_add", userEmail);
				CM.click("submit_button");
			}
			break;

		case "dst_link":
			CM.accessSignUpLink("subscribe_link", "dst_link");
			CM.waitForPageToLoad("10");
			CM.click("free-trial_button");
			if (((locatorName.equalsIgnoreCase("error_invalidPassword"))|(locatorName.equalsIgnoreCase("error_invalidConfirmPassword")))){
				CM.click("email_add");
				CM.type("email_add", userEmail);
				CM.type("fname", "testfn");
				CM.type("lname", "testln");
				CM.click("submit_button");
			}
			break;

		case "dsa_link":
			CM.accessSignUpLink("subscribe_link", "dsa_link");
			CM.waitForPageToLoad("10");
			CM.click("getstarted_button1");
			CM.click("getstarted_button2");
			if (((locatorName.equalsIgnoreCase("error_invalidPassword"))|(locatorName.equalsIgnoreCase("error_invalidConfirmPassword")))){
				CM.click("email_add");
				CM.type("email_add", userEmail);
				CM.type("fname", "testfn");
				CM.type("lname", "testln");
				CM.click("submit_button");
			}
			break;
		}

		switch (locatorName) {

		case "error_invalidEmail":
			if (Validate_SignUpFields.equalsIgnoreCase("cc_link")) {
				CM.click("email_id");
				CM.type("email_id", validateField);
			} else {
				CM.click("email_add");
				CM.type("email_add", validateField);
			}
			break;

		case "error_invalidfirstName":
			if(Validate_SignUpFields.equalsIgnoreCase("cc_link")){
				CM.click("first_name");
				CM.type("first_name", validateField);
			}else{
				CM.click("fname");
				CM.type("fname", validateField);
			}
			break;

		case "error_invalidLastName":
			if(Validate_SignUpFields.equalsIgnoreCase("cc_link")){
				CM.click("last_name");
				CM.type("last_name", validateField);
			}else{
				CM.click("lname");
				CM.type("lname", validateField);
			}
			break;

		case "error_invalidPassword":
			CM.click("pass");
			CM.type("pass", validateField);
			break;

		case "error_invalidConfirmPassword":
			CM.click("confirmpass");
			CM.type("confirmpass", validateField);
			break;
		}

		CM.click("submit_button");
		CM.waitForPageToLoad("20");
		actualText = CM.getText(locatorName);
		CM.deleteCookies();
		try {
			CM.naviagetTo(CommonConstant.streeturl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualText;
	}

}
