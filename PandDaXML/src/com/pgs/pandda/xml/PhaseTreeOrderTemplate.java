//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.29 at 03:10:59 PM CEST 
//


package com.pgs.pandda.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="PhaseGroup" type="{http://www.pgs.com/phase_tree_order}PhaseGroupType"/>
 *           &lt;element name="Phase" type="{http://www.pgs.com/phase_tree_order}PhaseType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "phaseGroup",
    "phase"
})
@XmlRootElement(name = "phaseTreeOrderTemplate")
public class PhaseTreeOrderTemplate {

    @XmlElement(name = "PhaseGroup")
    protected PhaseGroupType phaseGroup;
    @XmlElement(name = "Phase")
    protected PhaseType phase;

    /**
     * Gets the value of the phaseGroup property.
     * 
     * @return
     *     possible object is
     *     {@link PhaseGroupType }
     *     
     */
    public PhaseGroupType getPhaseGroup() {
        return phaseGroup;
    }

    /**
     * Sets the value of the phaseGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhaseGroupType }
     *     
     */
    public void setPhaseGroup(PhaseGroupType value) {
        this.phaseGroup = value;
    }

    /**
     * Gets the value of the phase property.
     * 
     * @return
     *     possible object is
     *     {@link PhaseType }
     *     
     */
    public PhaseType getPhase() {
        return phase;
    }

    /**
     * Sets the value of the phase property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhaseType }
     *     
     */
    public void setPhase(PhaseType value) {
        this.phase = value;
    }

}