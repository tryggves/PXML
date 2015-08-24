package com.pgs.pandda.project;

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
	private String chordDirectoryRef;	// This is the directory name of the chain order
	
	private List<Task> tasks; 			// List of Tasks
	
	// Initialize the ordered list
	public ChainOrder () {
		tasks = new ArrayList<Task>();
	}
	
	public List<Task> getTasks () {
		return tasks;
	}
	
	public void setChordDirectoryRef(String chordDirectoryRef) {
		this.chordDirectoryRef = chordDirectoryRef;
	}
	
	public String getChordDirectoryRef () {
		return this.chordDirectoryRef;
	}

	// TODO:
	// Get number of tasks in this chain order
}
