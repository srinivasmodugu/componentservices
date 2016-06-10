package com.dci.ComponentInfo.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Bean for component from getComponentById service
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
@XmlRootElement(name = "component")
//@JsonPropertyOrder({"FELEMENTINSTANCEID", "FELEMENT_ID", "FDATA_DESC", "FEFFECTIVEDATE", "FEXPIRATIONDATE", "FDATA", "FCREATEDBY", "FLASTCHANGEDBY", "FLANGUAGEID", "FLANGUAGE_DESC", "FCOMPSTATUS", "FCOMPSTATUS_DESC", "FVERSIONID"})
public class Component_ID {

	private int FELEMENTINSTANCEID;
	private String FELEMENT_ID;
	private String FDATA_DESC;
	private String FEFFECTIVEDATE;
	private String FEXPIRATIONDATE;
	private String FDATA;
	private String FCREATEDBY;
	private String FLASTCHANGEDBY;
	private int FLANGUAGEID;
	private String FLANGUAGE_DESC;
	private int FCOMPSTATUS;
	private String FCOMPSTATUS_DESC;
	private int FVERSIONID;
	
	public int getFELEMENTINSTANCEID() {
		return FELEMENTINSTANCEID;
	}
	public void setFELEMENTINSTANCEID(int fELEMENTINSTANCEID) {
		FELEMENTINSTANCEID = fELEMENTINSTANCEID;
	}
	public String getFELEMENT_ID() {
		return FELEMENT_ID;
	}
	public void setFELEMENT_ID(String fELEMENT_ID) {
		FELEMENT_ID = fELEMENT_ID;
	}
	public String getFDATA_DESC() {
		return FDATA_DESC;
	}
	public void setFDATA_DESC(String fDATA_DESC) {
		FDATA_DESC = fDATA_DESC;
	}
	public String getFEFFECTIVEDATE() {
		return FEFFECTIVEDATE;
	}
	public void setFEFFECTIVEDATE(String fEFFECTIVEDATE) {
		FEFFECTIVEDATE = fEFFECTIVEDATE;
	}
	public String getFEXPIRATIONDATE() {
		return FEXPIRATIONDATE;
	}
	public void setFEXPIRATIONDATE(String fEXPIRATIONDATE) {
		FEXPIRATIONDATE = fEXPIRATIONDATE;
	}
	public String getFDATA() {
		return FDATA;
	}
	public void setFDATA(String fDATA) {
		FDATA = fDATA;
	}
	public String getFCREATEDBY() {
		return FCREATEDBY;
	}
	public void setFCREATEDBY(String fCREATEDBY) {
		FCREATEDBY = fCREATEDBY;
	}
	public String getFLASTCHANGEDBY() {
		return FLASTCHANGEDBY;
	}
	public void setFLASTCHANGEDBY(String fLASTCHANGEDBY) {
		FLASTCHANGEDBY = fLASTCHANGEDBY;
	}
	public int getFLANGUAGEID() {
		return FLANGUAGEID;
	}
	public void setFLANGUAGEID(int fLANGUAGEID) {
		FLANGUAGEID = fLANGUAGEID;
		this.setFLANGUAGE_DESC(Integer.toString(fLANGUAGEID));
	}
	public String getFLANGUAGE_DESC() {
		return FLANGUAGE_DESC;
	}
	
	/**
	 * Set the Language description by the language indicator
	 * @param fLANGUAGE_DESC
	 */
	public void setFLANGUAGE_DESC(String fLANGUAGE_DESC) {
		if (fLANGUAGE_DESC.equals("1")) {
			FLANGUAGE_DESC = "English";
		} else {
			FLANGUAGE_DESC = "Error";
		}
	}
	public int getFCOMPSTATUS() {
		return FCOMPSTATUS;
	}
	public void setFCOMPSTATUS(int fCOMPSTATUS) {
		FCOMPSTATUS = fCOMPSTATUS;
		this.setFCOMPSTATUS_DESC(Integer.toString(fCOMPSTATUS));
	}
	public String getFCOMPSTATUS_DESC() {
		return FCOMPSTATUS_DESC;
	}
	
	/**
	 * Set the component status description by component status indicator
	 * @param fCOMPSTATUS_DESC
	 */
	public void setFCOMPSTATUS_DESC(String fCOMPSTATUS_DESC) {
		if (fCOMPSTATUS_DESC.equals("0")) {
			this.FCOMPSTATUS_DESC = "Pending";
		} else if (fCOMPSTATUS_DESC.equals("1")) {
			this.FCOMPSTATUS_DESC = "Review";
		} else if (fCOMPSTATUS_DESC.equals("2")) {
			this.FCOMPSTATUS_DESC = "Approved";
		} else {
			this.FCOMPSTATUS_DESC = "Error";
		}
	}
	public int getFVERSIONID() {
		return FVERSIONID;
	}
	public void setFVERSIONID(int fVERSIONID) {
		FVERSIONID = fVERSIONID;
	}
	
	
}
