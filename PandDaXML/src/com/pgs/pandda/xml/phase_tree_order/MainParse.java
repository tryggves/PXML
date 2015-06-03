package com.pgs.pandda.xml.phase_tree_order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.pgs.pandda.xml.PhaseTreeOrderTemplate;

public class MainParse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ParseData pd = new ParseData();
		// JAXBElement<PhaseTreeOrderTemplate> doc;
		PhaseTreeOrderTemplate doc;
		
		// Get input stream from file
		try {
			FileInputStream fileInputStream = new FileInputStream("phase_tree_order.xml");
			doc = pd.unmarshall(PhaseTreeOrderTemplate.class, fileInputStream);
			List<Object> l = (List<Object>) doc.getGroupOrPhase();
		} catch (FileNotFoundException fe) {
			System.err.println (fe);
			System.exit(1);
		} catch (JAXBException je) {
			System.err.println (je);
			System.exit(1);
		}

	}

}
