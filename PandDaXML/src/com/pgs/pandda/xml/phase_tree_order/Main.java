package com.pgs.pandda.xml.phase_tree_order;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CreateData cd = new CreateData();
		
		cd.makePhaseGroupType("Phase-test", 1);
		cd.marshal();

	}

}
