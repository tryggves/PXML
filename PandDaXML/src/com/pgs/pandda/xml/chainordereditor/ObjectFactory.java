//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.06 at 12:52:01 PM CEST 
//


package com.pgs.pandda.xml.chainordereditor;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.pgs.pandda.xml.chainordereditor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pgs.pandda.xml.chainordereditor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChainOrderTemplate }
     * 
     */
    public ChainOrderTemplate createChainOrderTemplate() {
        return new ChainOrderTemplate();
    }

    /**
     * Create an instance of {@link ChainOrderTemplate.TaskInformation }
     * 
     */
    public ChainOrderTemplate.TaskInformation createChainOrderTemplateTaskInformation() {
        return new ChainOrderTemplate.TaskInformation();
    }

    /**
     * Create an instance of {@link ChainOrderTemplate.TaskInformation.TaskParameter }
     * 
     */
    public ChainOrderTemplate.TaskInformation.TaskParameter createChainOrderTemplateTaskInformationTaskParameter() {
        return new ChainOrderTemplate.TaskInformation.TaskParameter();
    }

}