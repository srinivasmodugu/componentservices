package com.dci.ComponentInfo.beans;

/**
 * Data bean for variable element
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class Variable {

	private int variableId;
	private String entityName;
	private String entityValue;
	
	public int getVariableId() {
		return variableId;
	}
	public void setVariableId(int variableId) {
		this.variableId = variableId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityValue() {
		return entityValue;
	}
	public void setEntityValue(String entityValue) {
		this.entityValue = entityValue;
	}
	
	
}
