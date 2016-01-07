package common.classes;

import org.openqa.selenium.WebDriver;

public class CreateAccount {
	public void newAccount(WebDriver accountdriver) throws Exception {
		CommonMethods CM = new CommonMethods(accountdriver,
				CommonConstant.Config_File_path);

		CM.type("pass", "thestreet");
		CM.type("confirmpass", "thestreet");
		CM.type("ccreditcardnumber", "4111111111111111");
		CM.type("ccreditcardcvv", "123");
		CM.type("nameoncreditcard", "testfn testln");
		CM.type("address1", "123 123");
		CM.type("address2", "123 123");
		CM.type("city", "New York");
		CM.selectDropdwonElement("state", "NY");
		CM.type("postalCode", "55555");
	}

}
