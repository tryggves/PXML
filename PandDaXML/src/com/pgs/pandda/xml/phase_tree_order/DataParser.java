package com.pgs.pandda.xml.phase_tree_order;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class DataParser {
	
	
	public <T> T unmarshall (Class<T> docClass, InputStream inputStream) throws JAXBException{
		String packageName = docClass.getPackage().getName();
		JAXBContext jc = JAXBContext.newInstance(packageName);
		Unmarshaller u = jc.createUnmarshaller();
		// JAXBElement<T> doc = (JAXBElement<T>)u.unmarshal(inputStream);
		// return doc.getValue();
		
		T doc = (T) u.unmarshal(inputStream);
		return doc;
	}

}
