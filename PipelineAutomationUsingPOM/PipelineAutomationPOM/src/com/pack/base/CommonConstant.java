package com.pack.base;

/**
 * Class which contains global constants.
 * 
 * 
 */
public class CommonConstant {
	public static final String browser = "FF";
	public static final String PIPELINEURL = "http://stage.thedeal.com/pipeline";
	public static final String workingDir = System.getProperty("user.dir"); // Get
																			// current
																			// working
																			// directory
	public static final String LOG_FILEPATH = workingDir.concat("\\Logs");
	public static final String LOG4J_FILEPATH = workingDir
			.concat("\\commonProperties\\log4j.properties");
	public static final String Config_File_path = workingDir
			.concat("\\commonProperties\\Locators");
	public static final String PIPELINE_DATA_PATH = workingDir
			.concat("\\Test Data\\Pipeline_Data.xlsx");
	public static final String sheet = "Valid-data";
	public static final String InvalidData = "Invalid-data";
	public static final String PIPELINE_USERS = "Pipeline_users";
}
