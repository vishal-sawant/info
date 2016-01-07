package signup.service;

import org.testng.annotations.Test;
import common.classes.CommonConstant;
import common.classes.CommonMethods;
import common.classes.CreateAccount;
import common.classes.ReadWriteExcel;

public class CC_SignUp extends ConfigureTST {
	@Test
	public void ccSignUp() throws Exception {

		String cc_user = "cctestuser" + Math.random();
		cc_user = cc_user.concat("@thestreet.com");
		// Writing email id into the excel file.
		ReadWriteExcel.putCellData(CommonConstant.Test_Data_Path,
				CommonConstant.sheet, 1, 0, cc_user);
		// Reading email id from the excel file.
		ReadWriteExcel.setExcelFile(CommonConstant.Test_Data_Path,
				CommonConstant.sheet);
		String email_id = ReadWriteExcel.getCellData(1, 0);
		// This creates object of CommonMethods class, also pass parameter
		// driver and file path from class CommonConstant
		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		// Put a wait, this means that any search for elements on the page could
		// take the time the implicit wait is set for before throwing exception
		CM.waitForPageToLoad("30");
		CM.click("continue_site_link");
		CM.waitForPageToLoad("20");
		CM.accessSignUpLink("subscribe_link", "cc_link");
		CM.waitForPageToLoad("10");
		CM.click("cc_button");
		// MLP ==> "Begin your exclusive membership//
		CM.click("membership_button");
		// CC Page
		CM.click("email_id");
		CM.type("email_id", email_id);
		CM.click("first_name");
		CM.type("first_name", "testfn");
		CM.click("last_name");
		CM.type("last_name", "testln");
		
		CreateAccount ca;
		ca = new CreateAccount();
		ca.newAccount(driver);

		CM.click("telephone_number");
		CM.type("telephone_number", "1234567890");
		CM.click("submit_button");
		CM.checkForTextPresent("Thank you");
		
	}
}
