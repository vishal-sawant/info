package signup.service;

import org.testng.annotations.Test;

import common.classes.CommonConstant;
import common.classes.CommonMethods;
import common.classes.CreateAccount;
import common.classes.ReadWriteExcel;

public class DST_SignUp extends ConfigureTST {
	@Test
	public void dstSignUp() throws Exception {

		String dst_user = "dsttestuser" + Math.random();
		dst_user = dst_user.concat("@thestreet.com");

		// Writing email id into the excel file.
		ReadWriteExcel.putCellData(CommonConstant.Test_Data_Path,
				CommonConstant.sheet, 4, 0, dst_user);
		// Reading email id into the excel file.
		ReadWriteExcel.setExcelFile(CommonConstant.Test_Data_Path,
				CommonConstant.sheet);
		String email_id = ReadWriteExcel.getCellData(4, 0);
		// Get object of CommonMethds
		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		
		CM.waitForPageToLoad("30");
		CM.click("continue_site_link");
		CM.waitForPageToLoad("20");
		CM.accessSignUpLink("subscribe_link", "dst_link");
		CM.waitForPageToLoad("20");
		CM.click("free-trial_button");
		CM.clear("email_add");
		CM.type("email_add", email_id);
		CM.type("fname", "testfn");
		CM.type("lname", "testln");
		CM.click("submit_button");

		CreateAccount ca;
		ca = new CreateAccount();
		ca.newAccount(driver);

		CM.click("submit_button");
		CM.checkForTextPresent("Thank you");
	}
}
