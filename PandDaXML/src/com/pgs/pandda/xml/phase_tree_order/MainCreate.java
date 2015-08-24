package com.pgs.pandda.xml.phase_tree_order;

import com.pgs.pandda.xml.project.PhaseType;

public class MainCreate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateData cd = new CreateData();
		
		cd.makePhaseGroupType("Phase-test", 1);
		PhaseType pt = cd.makePhaseType("06j21_SWATH_1", 1);
		cd.addPhaseToPhaseGroup(pt);
		
		cd.marshal();

	}

}
