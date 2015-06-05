package com.pgs.spark.project;

import org.apache.log4j.Logger;

/**
 * PandDa project domain model class.
 * 
 * @author trysor
 *
 */
public class Task {
	private static Logger logger = Logger.getLogger(Task.class.getName());
	
	private String taskName;			// Task name
	private String auxDatabaseName;		// Auxiliary database name; NULL if no AUXDB associated
	private String parDatabaseName;		// Parameter database name; NULL if no PDB associated
	
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
