package com.pgs.pandda.project;

import org.apache.log4j.Logger;

/**
 * PandDa project domain model class.
 * 
 * @author trysor
 *
 */
public class Phase {
	private static Logger logger = Logger.getLogger(Phase.class.getName());
	
	private String phaseName;			// The name of the phase
	private ChainOrder chainOrder;		// A phase contains one and only one Chain order
	
	
	public Phase () {
		chainOrder = new ChainOrder ();
	}
	
	public ChainOrder getChainOrder () {
		return chainOrder;
	}
	
	public void setChainOrder (ChainOrder chOrder) {
		this.chainOrder = chOrder;
	}
	

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}


}
