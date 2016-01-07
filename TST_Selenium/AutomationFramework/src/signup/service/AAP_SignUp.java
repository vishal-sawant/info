package signup.service;

import org.testng.annotations.Test;

import common.classes.CommonConstant;
import common.classes.CommonMethods;
import common.classes.CreateAccount;
import common.classes.ReadWriteExcel;

public class AAP_SignUp extends ConfigureTST {
	@Test
	public void aapSignUp() throws Exception {

		String aap_user = "aaptestuser" + Math.random();
		aap_user = aap_user.concat("@thestreet.com");

		// Writing email id into the excel file.
		ReadWriteExcel.putCellData(CommonConstant.Test_Data_Path,
				CommonConstant.sheet, 2, 0, aap_user);
		// Reading email id from the excel file.
		ReadWriteExcel.setExcelFile(CommonConstant.Test_Data_Path,
				CommonConstant.sheet);
		String email_id = ReadWriteExcel.getCellData(2, 0);

		CommonMethods CM = new CommonMethods(driver,
				CommonConstant.Config_File_path);
		
		CM.waitForPageToLoad("30");
		CM.click("continue_site_link");
		CM.waitForPageToLoad("20");
		CM.accessSignUpLink("subscribe_link", "aap_link");
		CM.waitForPageToLoad("20");
		CM.click("portfolio_button");
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
