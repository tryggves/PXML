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

	public void makePhaseGroupType (String pgName, int pgIndex) {
		PhaseGroupType pg = objFact.createPhaseGroupType();
		pg.setName(pgName);
		pg.setIndex(new Integer(pgIndex));
		
		ptot.setPhaseGroup(pg);
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
