package com.pgs.pandda.xml.phase_tree_order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.log4j.Logger;
import javax.xml.bind.JAXBException;

import com.pgs.pandda.xml.PhaseGroupType;
import com.pgs.pandda.xml.PhaseTreeOrderTemplate;
import com.pgs.pandda.xml.PhaseType;
import com.pgs.spark.project.PandDaProject;
import com.pgs.spark.project.Phase;

public class MainParse {
	private static Logger logger = Logger.getLogger(MainParse.class.getName());
	private static String projectDir = "/datadb/tryggvesp/PandDa/xmlfiles";
	private static PandDaProject project;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DataParser pd = new DataParser();
		// JAXBElement<PhaseTreeOrderTemplate> doc;
		PhaseTreeOrderTemplate doc;
		
		// Get input stream from file
		try {
			String projectFileName = projectDir + "/phase_tree_order.xml";
			logger.info("Read file: " + projectFileName);
			
			FileInputStream fileInputStream = new FileInputStream(projectFileName);
			
			logger.info("Unmarshal");
			doc = pd.unmarshall(PhaseTreeOrderTemplate.class, fileInputStream);
			
			// Build the domain model for the project.
			project = new PandDaProject();
			project.setProjectName("3764pgs");
			
			List<Object> l = (List<Object>) doc.getGroupOrPhase();
			logger.info("Got list of " + l.size() +" members.");
			
			// Traverse the list of phase groups/phases
			for (Object obj: l) {
				// logger.info("List Object is " + obj);
				if (obj instanceof PhaseGroupType) {
					// Process each phase inside this group.
					// PhaseGroupType phaseGroup = (PhaseGroupType)obj;
					logger.info("Processing phase group with name: " + ((PhaseGroupType)obj).getName());
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
	
	
	// Iterate through the phases in the group and process each phase.
	private static void processPhaseGroup (PhaseGroupType phaseGroup) {
	
		List<PhaseType> phaseList = (List<PhaseType>) phaseGroup.getPhase();
		for (PhaseType phase : phaseList) {
			logger.debug("Process phase: " + phase.getName());
			processPhase (phase);
		}
	}
	
	// Iterate through tasks for a phase adding them to the task chain order
	private static void processPhase (PhaseType phase) {
		Phase p = new Phase();
		p.setPhaseName(phase.getName());
		
		logger.debug("Process chain order for phase: " + phase.getName());
		
		logger.debug("Add phase to project.");
		project.getPhases().add(p);
	}

}
