package com.dci.ComponentInfo.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Bean for component from the search libary service
 * Setter will check if the parameters are null. If null, then set empty string
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
@XmlRootElement(name = "component")
public class Component_Search {

	private int FELEMENTINSTANCEID;
	private String FELEMENT_ID;
	private String FQUALDATA_DESC;
	private String FEFFECTIVEDATE;
	private String FEXPIRATIONDATE;
	private String FELEMENTCONTEXT_DESC;
	private String FELEMENT_DETAIL;
	private String FADDMORE;
	private String FCREATEDBY;
	private String FLASTCHANGEDBY;
	private int FLANGUAGEID;
	private String FLANGUAGE_DESC;
	private int FLOCALE_IND;
	private int FCOMPSTATUS;
	private String FCOMPSTATUS_DESC;
	private int FVERSIONID;
	
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
		if (fELEMENT_ID == null) {
			FELEMENT_ID = "";
		} else {
			FELEMENT_ID = fELEMENT_ID;
		}
	}
	public String getFQUALDATA_DESC() {
		return FQUALDATA_DESC;
	}
	
	public void setFQUALDATA_DESC(String fQUALDATA_DESC) {
		if (fQUALDATA_DESC == null) {
			FQUALDATA_DESC = "";
		} else {
			FQUALDATA_DESC = fQUALDATA_DESC;
		}
	}

	public String getFELEMENTCONTEXT_DESC() {
		return FELEMENTCONTEXT_DESC;
	}
	
	public void setFELEMENTCONTEXT_DESC(String fELEMENTCONTEXT_DESC) {
		if (fELEMENTCONTEXT_DESC == null) {
			FELEMENTCONTEXT_DESC = "";
		} else {
			FELEMENTCONTEXT_DESC = fELEMENTCONTEXT_DESC;
		}
	}
	public String getFELEMENT_DETAIL() {
		return FELEMENT_DETAIL;
	}
	
	public void setFELEMENT_DETAIL(String fELEMENT_DETAIL) {
		if (fELEMENT_DETAIL == null) {
			FELEMENT_DETAIL = "";
		} else {
			FELEMENT_DETAIL = fELEMENT_DETAIL;
		}
	}
	public String getFADDMORE() {
		return FADDMORE;
	}
	
	public void setFADDMORE(String fADDMORE) {
		if (fADDMORE == null) {
			FADDMORE = "";
		} else {
			FADDMORE = fADDMORE;
		}
	}
	public String getFCREATEDBY() {
		return FCREATEDBY;
	}

	public void setFCREATEDBY(String fCREATEDBY) {
		if (FCREATEDBY == null) {
			FCREATEDBY = "";
		} else {
			FCREATEDBY = fCREATEDBY;
		}
	}
	public String getFLASTCHANGEDBY() {
		return FLASTCHANGEDBY;
	}
	
	public void setFLASTCHANGEDBY(String fLASTCHANGEDBY) {
		if (FLASTCHANGEDBY == null) {
			FLASTCHANGEDBY = "";
		} else {
			FLASTCHANGEDBY = fLASTCHANGEDBY;
		}
	}
	public int getFLANGUAGEID() {
		return FLANGUAGEID;
	}
	
	public void setFLANGUAGEID(int fLANGUAGEID) {
		FLANGUAGEID = fLANGUAGEID;
	}
	public String getFLANGUAGE_DESC() {
		return FLANGUAGE_DESC;
	}
	
	public void setFLANGUAGE_DESC(String fLANGUAGE_DESC) {
		if (FLANGUAGE_DESC == null) {
			FLANGUAGE_DESC = "";
		} else {
			FLANGUAGE_DESC = fLANGUAGE_DESC;
		}
	}
	public int getFLOCALE_IND() {
		return FLOCALE_IND;
	}
	
	public void setFLOCALE_IND(int fLOCALE_IND) {
		FLOCALE_IND = fLOCALE_IND;
	}
	public int getFCOMPSTATUS() {
		return FCOMPSTATUS;
	}
	
	public void setFCOMPSTATUS(int fCOMPSTATUS) {
		FCOMPSTATUS = fCOMPSTATUS;
	}
	public String getFCOMPSTATUS_DESC() {
		return FCOMPSTATUS_DESC;
	}
	
	public void setFCOMPSTATUS_DESC(String fCOMPSTATUS_DESC) {
		if (FCOMPSTATUS_DESC == null) {
			FCOMPSTATUS_DESC = "";
		} else {
			FCOMPSTATUS_DESC = fCOMPSTATUS_DESC;
		}
	}

	public int getFVERSIONID() {
		return FVERSIONID;
	}

	public void setFVERSIONID(int fVERSIONID) {
		FVERSIONID = fVERSIONID;
	}
}
