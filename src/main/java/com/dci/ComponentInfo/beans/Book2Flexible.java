package com.dci.ComponentInfo.beans;

public class Book2Flexible {
	
	private int fbookinstanceid;
	private int felementinstanceid;
	private int fbookdetailid;
	private String fqualdata_tabletype;
	private String fcolattribute;
	
	public int getFbookinstanceid() {
		return fbookinstanceid;
	}
	public void setFbookinstanceid(int fbookinstanceid) {
		this.fbookinstanceid = fbookinstanceid;
	}
	public int getFelementinstanceid() {
		return felementinstanceid;
	}
	public void setFelementinstanceid(int felementinstanceid) {
		this.felementinstanceid = felementinstanceid;
	}
	public int getFbookdetailid() {
		return fbookdetailid;
	}
	public void setFbookdetailid(int fbookdetailid) {
		this.fbookdetailid = fbookdetailid;
	}
	public String getFqualdata_tabletype() {
		return fqualdata_tabletype;
	}
	public void setFqualdata_tabletype(String fqualdata_tabletype) {
		if (fqualdata_tabletype == null) {
			this.fqualdata_tabletype = "";
		} else {
			this.fqualdata_tabletype = fqualdata_tabletype;
		}
	}
	public String getFcolattribute() {
		return fcolattribute;
	}
	public void setFcolattribute(String fcolattribute) {
		if (fcolattribute == null) {
			this.fcolattribute = "";
		} else {
			this.fcolattribute = fcolattribute;
		}
	}
	
	
}
