package com.dci.ComponentInfo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Bean for a list of component_search. XML output purpose.
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
@XmlRootElement(name = "components")
public class ComponentList_Search {

	@XmlElement(required = true)
	public List<Component_Search> component;
	
//	public ComponentList_Search() {
//		this.component = new ArrayList<Component_Search>();
//	}
//
//	public List<Component_Search> getComponents() {
//		return component;
//	}
//
//	public void setComponents(List<Component_Search> components) {
//		for (Component_Search temp : components) {
//			this.component.add(temp);
//		}
//	}
}
