package com.pgs.pandda.xml.phase_tree_order;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.pgs.pandda.xml.ObjectFactory;
import com.pgs.pandda.xml.PhaseGroupType;
import com.pgs.pandda.xml.PhaseTreeOrderTemplate;
import com.pgs.pandda.xml.PhaseType;


public class CreateData {

	private ObjectFactory objFact;
	private PhaseTreeOrderTemplate ptot;
	
	public CreateData () {
		objFact = new ObjectFactory();
		ptot = objFact.createPhaseTreeOrderTemplate();
	}

	// Create a phase group and add it to the list of phases/phase groups
	public void makePhaseGroupType (String pgName, int pgIndex) {
		
		PhaseGroupType pg = objFact.createPhaseGroupType();
		pg.setName(pgName);
		pg.setIndex(new Integer(pgIndex));
		
		ptot.getGroupOrPhase().add(pg);
	}
	
	// Create a Phase with name and index
	public PhaseType makePhaseType (String phName, int phIndex) {
		PhaseType pt = objFact.createPhaseType();
		pt.setIndex(phIndex);
		pt.setName(phName);
		
		return pt;
	}
	
	// Add Phase to phase group
	public void addPhaseToPhaseGroup (PhaseType pt) {
		PhaseGroupType pg;
		
		try {
			pg = (PhaseGroupType) ptot.getGroupOrPhase().get(0);
			pg.getPhase().add(pt);
		} catch (IndexOutOfBoundsException ie) {
			System.err.println(ie);
		}
	}
	
	public PhaseTreeOrderTemplate getPhaseTreeOrderTemplate () {
		return ptot;
	}
	
	public void marshal() {
        try {
            JAXBContext jc = JAXBContext.newInstance( "com.pgs.pandda.xml" );
            Marshaller m = jc.createMarshaller();
            m.marshal( ptot, System.out );
        } catch( JAXBException jbe ){
            // ...
        	System.err.print(jbe);
        }
	}
}
