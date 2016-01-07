package com.pack.base;

import java.text.DateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This is a utility for logging information in log file.
 * 
 */
public class Logs {

    private Logger logger;

    public Logs() {
        System.setProperty("log4j.date", getLogFileName());
        BasicConfigurator.resetConfiguration();
        PropertyConfigurator.configure(CommonConstant.LOG4J_FILEPATH);
        logger = Logger.getLogger(Logs.class);
    };

    /* Initialize the log file name on the bases of date */
    public String getLogFileName() {
        String date = null;
        Date now = new Date();
        String datetime = DateFormat.getInstance().format(now);
        StringTokenizer tokens = new StringTokenizer(datetime);
        while (tokens.hasMoreTokens()) {
            date = tokens.nextToken();
            break;
        }
        date = date.replace('/', '_');
        return date;
    }

    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }
}
