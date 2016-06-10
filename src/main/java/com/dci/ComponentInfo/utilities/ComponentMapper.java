package com.dci.ComponentInfo.utilities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.dci.ComponentInfo.beans.Component_Search;

/**
 * Spring resultset row mapper. Not being used, since the JNDI issue
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class ComponentMapper implements RowMapper<Component_Search>{
	
	@Override
	public Component_Search mapRow(ResultSet rs, int rowNum) throws SQLException {
		Component_Search component = new Component_Search();
		component.setFELEMENTINSTANCEID(rs.getInt("FELEMENTINSTANCEID"));
		component.setFELEMENT_ID(rs.getString("FELEMENT_ID"));
		component.setFQUALDATA_DESC("FQUALDATA_DESC");
		component.setFEFFECTIVEDATE(this.setDate(rs.getDate("FEFFECTIVEDATE")));
		component.setFEXPIRATIONDATE(this.setDate(rs.getDate("FEXPIRATIONDATE")));
		component.setFELEMENTCONTEXT_DESC(rs.getString("FELEMENTCONTEXT_DESC"));
		component.setFELEMENT_DETAIL(rs.getString("FELEMENT_DETAIL"));
		component.setFADDMORE(rs.getString("FADDMORE"));
		component.setFCREATEDBY(rs.getString("FCREATEDBY"));
		component.setFLASTCHANGEDBY(rs.getString("FLASTCHANGEDBY"));
		component.setFLANGUAGEID(rs.getInt("FLANGUAGEID"));
		component.setFLANGUAGE_DESC(rs.getString("FLANGUAGE_DESC"));
		component.setFLOCALE_IND(rs.getInt("FLOCALE_IND"));
		component.setFCOMPSTATUS(rs.getInt("FCOMPSTATUS"));
		component.setFCOMPSTATUS_DESC(rs.getString("FCOMPSTATUS_DESC"));
		return component;
	}
	
	private String setDate(Date date) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		if (date == null) {
			return "";
		} else {
			return df.format(date);
		}
	}

}
