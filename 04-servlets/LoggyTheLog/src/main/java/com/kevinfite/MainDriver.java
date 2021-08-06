package com.kevinfite;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/*
 * LOGGING LEVELS INCLUDE:
 * 
 * All, debug, info, warn, error, fatal, off, trace
 * 
 * (lowest priority) 								(higher)
 * All < debug < info < warn < error < fatal < off
 */
public class MainDriver {

	final static Logger logger = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
		logger.setLevel(Level.ERROR);
		
		if(logger.isInfoEnabled()) {
			logger.info("This is info: loggers are cool, right? Sugoi!");
		}
		
		logger.warn("This is a warning: it comes for me at dawn....");
		logger.error("This is an error: pineapples don't go on pizza...",
				new IndexOutOfBoundsException());
		logger.fatal("This is fatal...like the Dragonballz live action movie");
		logger.info("-------------");
	}

}
