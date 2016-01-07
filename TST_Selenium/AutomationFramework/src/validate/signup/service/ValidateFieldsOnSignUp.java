package validate.signup.service;

import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import common.classes.CommonConstant;
import common.classes.FieldValidation;
import common.classes.ReadWriteExcel;
import signup.service.ConfigureTST;

public class ValidateFieldsOnSignUp extends ConfigureTST {

	public String nameOfSignupService;

	@Test(alwaysRun = true)
	@Parameters("Validate_SignUpService")
	public void blankformSubmit(String Validate_SignUpService) throws Exception {
		String actualErrorMsg;
		nameOfSignupService = Validate_SignUpService;
		String expectedErrorMsg = "we were unable to process";
		FieldValidation FV = new FieldValidation();
		actualErrorMsg = FV.VerifyBlankFormSubmission(driver,
				Validate_SignUpService, "error_text");

		Assert.assertEquals(actualErrorMsg.contains(expectedErrorMsg), true);
		logger.info("Blank Form Submit test case is passed");
	}

	@DataProvider(name = "passwordAccount")
	public Object[][] passwordAccountData(Method method) throws IOException {
		Object[][] arrayObject = ReadWriteExcel.getExcelData(
				CommonConstant.Test_Data_Path, CommonConstant.InvalidData);

		int objSize = arrayObject.length;
		Object[][] fieldToValidate = new Object[objSize][1];
		String methodName = method.getName();
		switch (methodName) {

		case "checkEmailID":
			for (int i = 0; i < objSize; i++) {
				fieldToValidate[i][0] = arrayObject[i][0];
			}
			break;
		case "checkFirstName":
			for (int i = 0; i < objSize; i++) {
				fieldToValidate[i][0] = arrayObject[i][1];
			}
			break;
		case "checkLastName":
			for (int i = 0; i < objSize; i++) {
				fieldToValidate[i][0] = arrayObject[i][2];
			}
			break;
		case "checkPassword":
			for (int i = 0; i < objSize; i++) {
				fieldToValidate[i][0] = arrayObject[i][3];
			}
			break;
		case "checkConfirmPassword":
			for (int i = 0; i < objSize; i++) {
				fieldToValidate[i][0] = arrayObject[i][4];
			}
		}
		return (fieldToValidate);
	}

	@Test(dataProvider = "passwordAccount", priority = 1)
	public void checkEmailID(String emailAdd) throws Exception {
		String actualErrorMsg;
		String expectedErrorMsg = "Email Address contains invalid";
		FieldValidation FV = new FieldValidation();
		actualErrorMsg = FV.verifyPasswordAccount(driver, "error_invalidEmail",
				nameOfSignupService, emailAdd);
		Assert.assertEquals(actualErrorMsg.contains(expectedErrorMsg), true);
		logger.info("Invalid Email test case passed");
	}

	@Test(dataProvider = "passwordAccount", priority = 2)
	public void checkFirstName(String firstName) throws Exception {
		if ((nameOfSignupService.equalsIgnoreCase("aao_link"))
				| (nameOfSignupService.equalsIgnoreCase("aap_link"))) {
			throw new SkipException(
					"Skipping this execution since Action Alert Options does not have First Name fields");
		}
		String actualErrorMsg;
		String expectedErrorMsg = "First Name contains invalid";
		FieldValidation FV = new FieldValidation();
		actualErrorMsg = FV.verifyPasswordAccount(driver,
				"error_invalidfirstName", nameOfSignupService, firstName);
		Assert.assertEquals(actualErrorMsg.contains(expectedErrorMsg), true);
		logger.info("Invalid First Name test case passed");
	}

	@Test(dataProvider = "passwordAccount", priority = 3)
	public void checkLastName(String lastName) throws Exception {
		if ((nameOfSignupService.equalsIgnoreCase("aao_link"))
				| (nameOfSignupService.equalsIgnoreCase("aap_link"))) {
			throw new SkipException(
					"Skipping this execution since Action Alert Options does not have Last Name fields");
		}
		String actualErrorMsg;
		String expectedErrorMsg = "Last Name contains invalid";
		FieldValidation FV = new FieldValidation();
		actualErrorMsg = FV.verifyPasswordAccount(driver,
				"error_invalidLastName", nameOfSignupService, lastName);
		Assert.assertEquals(actualErrorMsg.contains(expectedErrorMsg), true);
		logger.info("Invalid Last Name test case passed");
	}

	@Test(dataProvider = "passwordAccount", priority = 4)
	public void checkPassword(String password) throws Exception {
		String actualErrorMsg;
		String expectedErrorMsg = "between 5 and 30 characters.";
		FieldValidation FV = new FieldValidation();
		actualErrorMsg = FV.verifyPasswordAccount(driver,
				"error_invalidPassword", nameOfSignupService, password);

		Assert.assertEquals(actualErrorMsg.contains(expectedErrorMsg), true);
		logger.info("Invalid Password test case passed");
	}

	@Test(dataProvider = "passwordAccount", priority = 5)
	public void checkConfirmPassword(String confirmPassword) throws Exception {
		String actualErrorMsg;
		String expectedErrorMsg = "Confirm Password must be";
		FieldValidation FV = new FieldValidation();
		actualErrorMsg = FV.verifyPasswordAccount(driver,
				"error_invalidConfirmPassword", nameOfSignupService,
				confirmPassword);
		Assert.assertEquals(actualErrorMsg.contains(expectedErrorMsg), true);
		logger.info("Invalid Confirm Password test case passed");

	}

}
