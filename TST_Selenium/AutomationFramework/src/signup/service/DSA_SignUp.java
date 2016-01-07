package signup.service;

import org.testng.annotations.Test;

import common.classes.CommonConstant;
import common.classes.CommonMethods;
import common.classes.CreateAccount;
import common.classes.ReadWriteExcel;

public class DSA_SignUp extends ConfigureTST {
	@Test
	public void dsaSignUp() throws Exception {

		String dsa_user = "dsatestuser" + Math.random();
		dsa_user = dsa_user.concat("@thestreet.com");

		// Writing email id into the excel file.
		ReadWriteExcel.putCellData(CommonConstant.Test_Data_Path,
				CommonConstant.sheet, 5, 0, dsa_user);
		// Reading email id into the excel file.
		ReadWriteExcel.setExcelFile(CommonConstant.Test_Data_Path,
				CommonConstant.sheet);
		String email_id = ReadWriteExcel.getCellData(5, 0);
		// Get object of CommonMethds
		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		
		CM.waitForPageToLoad("30");
		CM.click("continue_site_link");
		CM.waitForPageToLoad("20");
		CM.accessSignUpLink("subscribe_link", "dsa_link");
		CM.waitForPageToLoad("20");
		CM.click("getstarted_button1");
		CM.click("getstarted_button2");
		CM.click("email_add");
		CM.type("email_add", email_id);
		CM.type("fname", "testfn");
		CM.type("lname", "testln");
		CM.click("submit_button");

		CreateAccount ca;
		ca = new CreateAccount();
		ca.newAccount(driver);

		CM.type("telephone_number", "1234567890");
		CM.click("submit_button");
		CM.waitForPageToLoad("20");
		CM.checkForTextPresent("Thank you");
	}
}
