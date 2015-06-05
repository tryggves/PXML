package com.pgs.pandda.xml.phase_tree_order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.acl.Group;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.pgs.pandda.xml.PhaseGroupType;
import com.pgs.pandda.xml.PhaseTreeOrderTemplate;
import com.pgs.pandda.xml.PhaseType;

public class MainParse {
	private static Logger logger = Logger.getLogger(MainParse.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ParseData pd = new ParseData();
		// JAXBElement<PhaseTreeOrderTemplate> doc;
		PhaseTreeOrderTemplate doc;
		
		// Get input stream from file
		try {
			logger.info("Read file phase_tree_order.xml");
			
			FileInputStream fileInputStream = new FileInputStream("phase_tree_order.xml");
			
			logger.info("Unmarshal");
			doc = pd.unmarshall(PhaseTreeOrderTemplate.class, fileInputStream);
			
			List<Object> l = (List<Object>) doc.getGroupOrPhase();
			logger.info("Got list of " + l.size() +" members.");
			
			// Traverse the list of phase groups/phases
			for (Object obj: l) {
				logger.info("List Object is " + obj);
				if (obj instanceof PhaseGroupType) {
					// Process each phase inside this group.
					// PhaseGroupType phaseGroup = (PhaseGroupType)obj;
					logger.info("Processing group phase group with name: " + ((PhaseGroupType)obj).getName());
					processPhaseGroup ((PhaseGroupType) obj);
				} else if (obj instanceof PhaseType) {
					// Process phase
					logger.info("Processing phase with name: " + ((PhaseType)obj).getName());
					processPhase ((PhaseType) obj);
				} else {
					logger.error("Cannot parse object: " + obj.getClass().getName());
					// Throw some exception
				}
			}
		} catch (FileNotFoundException fe) {
			System.err.println (fe);
			System.exit(1);
		} catch (JAXBException je) {
			System.err.println (je);
			System.exit(1);
		}

	}
	
	private static void processPhaseGroup (PhaseGroupType phaseGroup) {
		// Iterate through the phases in the group and add them to list.
		
	}
	
	private static void processPhase (PhaseType phase) {
		
	}

}
