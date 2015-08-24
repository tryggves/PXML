package com.pgs.pandda.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * PandDa project domain model class. This is the top level project class
 * See Umbrello class diagram.
 * 
 * @author trysor
 *
 */
public class PandDaProject {
	private static Logger logger = Logger.getLogger(PandDaProject.class.getName());
	
	// Not shure if we need the PhaseGroup since that is more for the PandDa gui
	// Need ordered list of phases.
	private String projectName;		// Project name
	private List<Phase> phases;		// List of one or more phases
	
	public PandDaProject () {
		// Create empty phase list.
		logger.debug("Constructor: Initialize empty phase list.");
		phases = new ArrayList<Phase>();
	}
	
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<Phase> getPhases() {
		return phases;
	}
	public void setPhases(List<Phase> phases) {
		this.phases = phases;
	}
}
