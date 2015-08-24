package com.pgs.pandda.project;

import org.apache.log4j.Logger;

/**
 * PandDa project domain model class.
 * 
 * @author trysor
 *
 */
public class Task {
	private static Logger logger = Logger.getLogger(Task.class.getName());
	
	private String taskName;			// Task name. This is also the base name for the 
										// task information XML file <task_name>.xml
	private String auxDatabaseName;		// Auxiliary database name; NULL if no AUXDB associated
	private String parDatabaseName;		// Parameter database name; NULL if no PDB associated
	
	// Task types as given by the task XML file
	// LT_STANDALONE LT_MASTER_TEMPLATE LT_TEMPLATE
	// TT_UPLOAD_USER_SPECIFY TT_DOWNLOAD_USER_SPECIFY TT_UPLOAD_STANDALONE
	// RT_TEMPLATE RT_STANDALONE
	
	public static String standalone = "LT_STANDALONE";
	public static String masterTemplate = "LT_MASTER_TEMPLATE";
	
	// Constructor setting the task name.
	public Task (String taskName){
		this.taskName = taskName;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getAuxDatabaseName() {
		return auxDatabaseName;
	}
	
	public void setAuxDatabaseName(String auxDatabaseName) {
		this.auxDatabaseName = auxDatabaseName;
	}
	
	public String getParDatabaseName() {
		return parDatabaseName;
	}
	
	public void setParDatabaseName(String parDatabaseName) {
		this.parDatabaseName = parDatabaseName;
	}
}
