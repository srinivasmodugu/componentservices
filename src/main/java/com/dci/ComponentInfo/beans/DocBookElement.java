package com.dci.ComponentInfo.beans;

/**
 * Data Bean for the docbook element
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class DocBookElement {
	
	private String FELEMENT_ID;
	private String FDATA;
	private String FGLOBALORDINAL;
	private int FELEMENTINSTANCEID;
	private int FCOMPSTATUS;
	private String FCOMPSTATUS_DESC;
	private int FLANGUAGE;
	private String FLANGUAGE_DESC;
	
	public String getFELEMENT_ID() {
		return FELEMENT_ID;
	}
	public void setFELEMENT_ID(String fELEMENT_ID) {
		FELEMENT_ID = fELEMENT_ID;
	}
	public String getFDATA() {
		return FDATA;
	}
	public void setFDATA(String fDATA) {
		FDATA = fDATA;
	}
	public String getFGLOBALORDINAL() {
		return FGLOBALORDINAL;
	}
	public void setFGLOBALORDINAL(String fGLOBALORDINAL) {
		FGLOBALORDINAL = fGLOBALORDINAL;
	}
	public int getFELEMENTINSTANCEID() {
		return FELEMENTINSTANCEID;
	}
	public void setFELEMENTINSTANCEID(int fELEMENTINSTANCEID) {
		FELEMENTINSTANCEID = fELEMENTINSTANCEID;
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
	 * Set component status description by component status indicator
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
	public int getFLANGUAGE() {
		return FLANGUAGE;
	}
	public void setFLANGUAGE(int fLANGUAGE) {
		FLANGUAGE = fLANGUAGE;
		this.setFLANGUAGE_DESC(Integer.toString(fLANGUAGE));
	}
	public String getFLANGUAGE_DESC() {
		return FLANGUAGE_DESC;
	}
	
	/**
	 * set language description by language indicator
	 * @param fLANGUAGE_DESC
	 */
	public void setFLANGUAGE_DESC(String fLANGUAGE_DESC) {
		if (fLANGUAGE_DESC.equals("1")) {
			FLANGUAGE_DESC = "English";
		} else {
			FLANGUAGE_DESC = "Error";
		}
	}
	
	
}
