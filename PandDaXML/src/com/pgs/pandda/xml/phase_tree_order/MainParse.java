package com.pgs.pandda.xml.phase_tree_order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.List;

import org.apache.log4j.Logger;
import javax.xml.bind.JAXBException;

import com.pgs.pandda.project.ChainOrder;
import com.pgs.pandda.project.PandDaProject;
import com.pgs.pandda.project.Phase;
import com.pgs.pandda.project.Task;
import com.pgs.pandda.xml.chainordereditor.ChainOrderTemplate;
import com.pgs.pandda.xml.chainordereditor.ChainOrderTemplate.TaskInformation.TaskParameter;
import com.pgs.pandda.xml.phase_ref.RefFileTemplate;
import com.pgs.pandda.xml.project.PhaseGroupType;
import com.pgs.pandda.xml.project.PhaseTreeOrderTemplate;
import com.pgs.pandda.xml.project.PhaseType;
import com.pgs.pandda.xml.task.TaskEditorTemplate;
import com.pgs.pandda.xml.task.TaskEditorTemplate.TaskInformation.Parameter;

public class MainParse {
	private static Logger logger = Logger.getLogger(MainParse.class.getName());
	private static String defaultProjectBase = "/datadb/tryggvesp"; 
	private static String projectDir = "PandDa/xmlfiles";
	private static String phaseDir = "PandDa/phase_directory";
	private static PandDaProject project;
	private static DataParser pd = new DataParser();
	
	private static String projectBase = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		projectBase = defaultProjectBase;
		
		// Check if there is a project given as argument in which case override
		if (args.length > 0) {
			logger.info("Override project with name: " + args[0]);
			projectBase = "/datadb/" + args[0];
		}
		
		// JAXBElement<PhaseTreeOrderTemplate> doc;
		PhaseTreeOrderTemplate doc;
		
		// Get input stream from file
		try {
			String projectFileName = projectBase + "/" + projectDir + "/phase_tree_order.xml";
			logger.info("Read file: " + projectFileName);
			
			FileInputStream fileInputStream = new FileInputStream(projectFileName);
			
			logger.debug ("Unmarshal the project XML file...");
			doc = pd.unmarshall(PhaseTreeOrderTemplate.class, fileInputStream);
			
			// Build the domain model for the project.
			project = new PandDaProject();
			// TODO: The project name should be parameterized and used to build the projectBase
			project.setProjectName("3764pgs");
			
			List<Object> l = (List<Object>) doc.getGroupOrPhase();
			logger.debug("Got list of " + l.size() +" members.");
			
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
			
			processPhase (phase);
		}
	}
	
	// The phase_directory/<phase name>/phase_dir_index.xml file contains 
	// reference to the chain order. 
	private static void processPhase (PhaseType phase) {
		Phase newPhase = new Phase();
		newPhase.setPhaseName(phase.getName());
		logger.debug("******************** Process phase: " + phase.getName() + "****************************************");
		
		logger.debug("Parse the phase_dir_index.xml file for phase: " + phase.getName());
		try {
			RefFileTemplate refFileTemplateDoc;
			
			String phaseRefFileName = projectBase + "/" + phaseDir + "/" + phase.getName() + "/phase_dir_index.xml";
			logger.debug("Parse file: " + phaseRefFileName);
			
			FileInputStream phaseRefInputStream = new FileInputStream(phaseRefFileName);
			
			logger.debug("Unmarshal phase_dir_index.xml");
			refFileTemplateDoc = pd.unmarshall(RefFileTemplate.class, phaseRefInputStream);
			
			logger.debug("Chord directory reference: " + refFileTemplateDoc.getReference().getName());
			newPhase.getChainOrder().setChordDirectoryRef(refFileTemplateDoc.getReference().getName());
		} catch (FileNotFoundException fe) {
			System.err.println (fe);
			System.exit(1);
		} catch (JAXBException je) {
			System.err.println (je);
			System.exit(1);
		}
		
		logger.debug("Add phase to project.");
		project.getPhases().add(newPhase);
		
		logger.debug("Process chain order for phase: " + phase.getName());
		processChainOrder(newPhase);
		
	}
	
	// Iterate through tasks for a phase adding them to the chain order task list
	private static void processChainOrder (Phase newPhase) {
		
		// ----------------------------------------------------------------------------
		// Parse the Phase<num>:ChainOrderEditor.xml file to get tasks
		// ----------------------------------------------------------------------------
		String phaseChainOrderFileName = findPhaseNumChainOrderEditor(newPhase.getChainOrder().getChordDirectoryRef());
		if (phaseChainOrderFileName == null) {
			logger.error ("Could not find one and only one Phase<num>:ChainOrderEditor.xml file");
			
			// TODO: Should throw exception 
			return;
		}
		
		String chainOrderTemplateFileName = newPhase.getChainOrder().getChordDirectoryRef() +
				"/" + phaseChainOrderFileName;
		
		logger.debug("Parse the chain order file: " + phaseChainOrderFileName);
		// Check if there is any data in file (possibly empty)
		File phaseChainOrderFile = new File(chainOrderTemplateFileName);
		if (0 == phaseChainOrderFile.length()) {
			logger.info("File size is zero. Do nothing.");
			return;
		}
		
		// Document model for chain order template.
		ChainOrderTemplate chainOrderTemplateDoc;
		
		try {
			
			logger.debug("Parse file: " + chainOrderTemplateFileName);
			
			FileInputStream chainOrderTemplateInputStream = new FileInputStream(chainOrderTemplateFileName);
			
			logger.debug("Unmarshal " + chainOrderTemplateFileName);
			chainOrderTemplateDoc = pd.unmarshall(ChainOrderTemplate.class, chainOrderTemplateInputStream);

			List<TaskParameter> taskList = chainOrderTemplateDoc.getTaskInformation().getTaskParameter();
			//-----------------------------------------------------------------------------
			// For each task:
			// 		Add task object to chain order list of tasks.
			//		Parse the task xml file to populate task domain object by calling processTask
			// ----------------------------------------------------------------------------
			for (TaskParameter taskParameter : taskList) {
				logger.debug ("**** Process task: " + taskParameter.getName());
				
				Task newTask = new Task(taskParameter.getName());
				newPhase.getChainOrder().getTasks().add(newTask);
				
				processTask (newPhase.getChainOrder(), newTask);
			}
			
			
		} catch (FileNotFoundException fe) {
			System.err.println (fe);
			System.exit(1);
		} catch (JAXBException je) {
			System.err.println (je);
			System.exit(1);
		}
		

		
	}
	
	
	// Process the task information.
	// Get task xml file from <task name>.xml file
	// Parse the task xml file to populate task domain object
	private static void processTask (ChainOrder chainOrder, Task newTask) {
		String taskFileName = chainOrder.getChordDirectoryRef() + "/" + newTask.getTaskName() + ".xml";
		
		try {
			logger.debug("Parse task XML file: " + taskFileName);
			FileInputStream taskTemplateInputStream = new FileInputStream(taskFileName);
			
			logger.debug("Unmarshal " + taskFileName);
			TaskEditorTemplate taskEditorTemplateDoc = pd.unmarshall(TaskEditorTemplate.class, taskTemplateInputStream);
		
			// Figure out what kind of task this is (standalone, template, master template)
			// NB! Sometimes the <parameter name="Task type:" does not exist, resulting in return NULL.
			
			logger.debug("Task type is: " + lookupTaskParameter(taskEditorTemplateDoc, "Task type:"));
			logger.debug("Auxiliary database:" + lookupTaskParameter(taskEditorTemplateDoc, "Assign auxiliary database:"));
			
			// PDB database name has value "Choose PDB" initially.
			String pdbDbName = lookupTaskParameter(taskEditorTemplateDoc, "Assign database:");
			if (pdbDbName != null && pdbDbName.equalsIgnoreCase("Choose PDB")) {
				logger.debug("PDB database:");
			} else {
				logger.debug("PDB database:" + pdbDbName);
			}
				
			// TODO: More robust error handling needed.
		} catch (FileNotFoundException fe) {
			logger.error("Open input file failed : " + fe);
			// Don't exit here, just return to caller to allow for further processing.
			return;
		} catch (JAXBException je) {
			System.err.println (je);
			System.exit(1);
		}
		
	}
	
	
	// Look up the task information parameter with the name passed as parameter
	private static String lookupTaskParameter (TaskEditorTemplate teTemplate, String parameterName) {
		List<Parameter> taskParameters = teTemplate.getTaskInformation().getParameter();
		
		for (Parameter parameter : taskParameters) {
			if (parameter.getName().equals(parameterName)) {
				return parameter.getValue();
			}
		}
		
		return null;
	}
	
	
	// Look up the file with pattern Phase<num>:ChainOrderEditor.xml in
	// directory given by parameter chordDirRef.
	private static String findPhaseNumChainOrderEditor (String chordDirRef) {
		
		logger.debug ("Search for chain order editor file in directory: " + chordDirRef);
		File dir = new File(chordDirRef);
	    
		File[] files = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.matches("Phase[0-9]+:ChainOrderEditor.xml");
			}
		});
		
		logger.debug("Number of file hits: " + files.length);
		if (files.length != 1) {
			logger.error("Expected 1 hit and got: " + files.length +". Giving up...");
			return null;
		} else {
			logger.debug("File name is: " + files[0].getName());
			return files[0].getName();
		}
	}

}
