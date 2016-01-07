package base;

/**
 * Class which contains global constants.
 * 
 * 
 */
public class CommonConstant {
	public static final String browser = "CH";
	public static final String PIPELINEURL = "http://stage.thedeal.com/udb/";
	public static final String workingDir = System.getProperty("user.dir"); // Get
																			// current
																			// working
																			// directory
	public static final String LOG_FILEPATH = workingDir.concat("\\Logs");
	public static final String LOG4J_FILEPATH = workingDir
			.concat("\\PropertyFiles\\log4j.properties");
	public static final String Config_File_path = workingDir
			.concat("\\PropertyFiles\\Locators");
	public static final String UDB_DATA_PATH = workingDir.concat("\\Test Data\\UDB_Data.xlsx");
	public static final String sheet = "Valid-data";
	public static final String InvalidData = "Invalid-data";
	public static final String UDB_USERS = "UDB_Users";
	public static final String UDB_DOWNLOADS = workingDir.concat("\\Test Data\\");
	public static final String InvalidGrpNm = "Invalid Group Name";
	//public static final String FAD_EXCEL = PIPELINE_DOWNLOADS.concat("")
	//this is just a test change made
	
}
