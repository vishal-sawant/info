package signup.service;

import org.testng.annotations.Test;

import common.classes.CommonConstant;
import common.classes.CommonMethods;
import common.classes.CreateAccount;
import common.classes.ReadWriteExcel;

public class AAO_SignUp extends ConfigureTST {
	@Test
	public void aaoSignUp() throws Exception {

		String aao_user = "aaotestuser" + Math.random();
		aao_user = aao_user.concat("@thestreet.com");

		// Writing email id into the excel file.
		ReadWriteExcel.putCellData(CommonConstant.Test_Data_Path,
				CommonConstant.sheet, 3, 0, aao_user);
		// Reading email id into the excel file.
		ReadWriteExcel.setExcelFile(CommonConstant.Test_Data_Path,
				CommonConstant.sheet);
		String email_id = ReadWriteExcel.getCellData(3, 0);

		// Get object of CommonMethds
		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		
		CM.waitForPageToLoad("30");
		CM.click("continue_site_link");
		CM.waitForPageToLoad("20");
		CM.accessSignUpLink("subscribe_link", "aao_link");
		CM.waitForPageToLoad("20");
		CM.click("free_trial_button");
		CM.clear("email_add");
		CM.type("email_add", email_id);
		CM.click("submit_button");

		CreateAccount ca;
		ca = new CreateAccount();
		ca.newAccount(driver);

		CM.type("telephone_number", "1234567890");
		CM.click("submit_button");
		CM.checkForTextPresent("Thank you");
	}

}
