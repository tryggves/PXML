package com.pgs.spark.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * PandDa project domain model class.
 * 
 * @author trysor
 *
 */
public class ChainOrder {
	private static Logger logger = Logger.getLogger(ChainOrder.class.getName());
	
	// List of Tasks
	List<Task> tasks;
	
	// Initialize the ordered list
	public ChainOrder () {
		tasks = new ArrayList<Task>();
	}
	
	public List<Task> getTasks () {
		return tasks;
	}
}
