package common.classes;

/**
 * Class which contains global constants.
 * 
 * 
 */
public class CommonConstant {
	public static final String browser = "CH";
	public static final String streeturl = "http://awsstage.thestreet.com";
	public static final String workingDir = System.getProperty("user.dir"); // Get
																			// current
																			// working
																			// directory
	public static final String LOG_FILEPATH = workingDir.concat("\\Logs");
	public static final String LOG4J_FILEPATH = workingDir
			.concat("\\commonProperties\\log4j.properties");
	public static final String Config_File_path = workingDir
			.concat("\\commonProperties\\Locators");
	public static final String Test_Data_Path = workingDir
			.concat("\\Test Data\\SignUp_Email.xlsx");
	public static final String sheet = "Valid-data";
	public static final String InvalidData = "Invalid-data";
}
