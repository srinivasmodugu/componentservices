package com.dci.ComponentInfo.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Bean for a list of component_id. For XML output purpose
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
@XmlRootElement(name = "components")
public class ComponentList_ID {
	
	@XmlElement(required = true)
	public List<Component_ID> component;
	
}
