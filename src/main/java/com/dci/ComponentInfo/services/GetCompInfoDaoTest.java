package com.dci.ComponentInfo.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dci.ComponentInfo.beans.Component_ID;
import com.dci.ComponentInfo.beans.Component_Search;

/**
 * Implementation class for GetCompInfoDAO interface. It is currently being used.
 * Since the service is deployed on the AS400 server, which uses JNDI lookup for datasource, 
 * so the implementation class doesn't use any spring datasource, or dependency injection
 * 
 * --Stored procedures includes:
 * 
 * ----SP6_SEARCHCOMPUPDATE() : updated version of SP7_SEARCHCOMPONENTS. (include versionid)
 * ----SP6_GETCOMPONENTBYID(?) : input component instanceid, and return component information,
 * 								 including embedded component information
 * ----SP6_GETCOMPLATESTVERSION(?) : Get the latest version id of the specific component
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class GetCompInfoDaoTest implements GetCompInfoDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(GetCompInfoDaoTest.class);
	
	private DataSource ds;
	private String compTypeAll;
	private int clientId;
	
	//Dependency Injection
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setCompTypeAll(String compTypeAll) {
		this.compTypeAll = compTypeAll;
	}

	/**
	 * Get a list of component_search by calling the proc
	 */
	@Override
	public List<Component_Search> getComponent(String key, String[] componentType) {
		logger.warn("Entering Method : getComponent()");
		String keyword = key;
		String compType = null;
		List<Component_Search> result = new ArrayList<Component_Search>();

		// make the component type string as one of the input parameters of the proc
		if (componentType.length == 1) {
			if (componentType[0].equalsIgnoreCase("selectall")) {
				compType = compTypeAll;
			} else {
				compType = componentType[0];
			}
		} else {
			for (int i = 0; i < componentType.length - 1; i++) {
				compType = compType + componentType[i] + ",";
			}
			compType = compType + componentType[componentType.length - 1];
		}
		
		//Default setting referenced from the docubuilder application
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
	
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{CALL SP6_SEARCHCOMPUPDATE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, "dcisupportabf");
			cs.setInt(2, clientId);
			cs.setString(3, compType);
			cs.setString(4, null);
			cs.setString(5, null);
			cs.setString(6, null);
			cs.setString(7, keyword);
			cs.setDate(8, null);
			cs.setDate(9, null);
			cs.setString(10, null);
			cs.setString(11, null);
			cs.setString(12, null);
			cs.setString(13, "n");
			cs.setString(14, "n");
			cs.setInt(15, 20);
			cs.setString(16, null);
			cs.setString(17, null);
			cs.setString(18, null);
			cs.setString(19, null);
			cs.setNull(20, Types.NULL);
			cs.setNull(21, Types.NULL);
			logger.warn("Call SP6_SEARCHCOMPUPDATE ('dcisupportabf',"+ clientId +","+ compType +",null,null,null"
					+ keyword + ",null,null,null,null,null,n,n,20,null,null,null,null,null,null)}");
			rs = cs.executeQuery();
			while (rs.next()) {
				Component_Search component = new Component_Search();
				component.setFELEMENTINSTANCEID(rs.getInt("FELEMENTINSTANCEID"));
				component.setFELEMENT_ID(rs.getString("FELEMENT_ID"));
				component.setFQUALDATA_DESC(rs.getString("FQUALDATA_DESC"));
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
				component.setFVERSIONID(rs.getInt("FQUALDATAVERSION"));
				result.add(component);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (cs != null)
					cs.close();
				if (conn != null)
					conn.close();
			} catch(SQLException e) {}
		}

		logger.warn("Getting the resultset and the size is: " + result.size());
		logger.warn("Exiting Method : getComponent()");
		return result;
	}
	
	/**
	 * Get specific component, which may include the embedded components
	 */
	@Override
	public List<Component_ID> getComponentById(String elementId) {
		logger.warn("Entering Method : getComponentById()");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		List<Component_ID> result = new ArrayList<Component_ID>();
	
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{CALL SP6_GETCOMPONENTBYID (?)}");
			cs.setInt(1, Integer.parseInt(elementId));
			logger.warn("call sp6_getcomponentbyid("+ elementId +")");
			rs = cs.executeQuery();
			while (rs.next()) {			
				Component_ID component = new Component_ID();
				component.setFELEMENTINSTANCEID(rs.getInt("FELEMENTINSTANCEID"));
				component.setFELEMENT_ID(rs.getString("FELEMENT_ID"));
				component.setFDATA_DESC(rs.getString("FDATA_DESC"));
				component.setFEFFECTIVEDATE(this.setDate(rs.getDate("FEFFECTIVEDATE")));
				component.setFEXPIRATIONDATE(this.setDate(rs.getDate("FEXPIRATIONDATE")));
				component.setFDATA(rs.getString("FDATA"));
				component.setFCREATEDBY(rs.getString("FCREATEDBY"));
				component.setFLASTCHANGEDBY(rs.getString("FLASTCHANGEDBY"));
				component.setFLANGUAGEID(rs.getInt("FLANGUAGEID"));
				component.setFCOMPSTATUS(rs.getInt("FCOMPSTATUS"));
				component.setFVERSIONID(rs.getInt("FVERSIONID"));
				result.add(component);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (cs != null)
					cs.close();
				if (conn != null)
					conn.close();
			} catch(SQLException e) {}
		}
		logger.warn("Getting result and the size is " + result.size());
		logger.warn("Exiting Method : getComponentById()");
		return result;
	}

	/**
	 * Used for checking update exist
	 * Compare the passed in version id with the document current version id. 
	 * If the current doc version id is greater than the passed in version id, then return
	 * the component information. Otherwise, return null
	 */
	@Override
	public List<Component_ID> getCompByVersionId(String elementId, String versionId) {
		logger.warn("Entering Method : getCompByVersionId()");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		int result = -1000;
		int passedVersion = Integer.parseInt(versionId);
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{CALL SP6_GETCOMPLATESTVERSION(?)}");
			cs.setInt(1, Integer.parseInt(elementId));
			logger.warn("call SP6_GETCOMPLATESTVERSION("+ elementId +")");
			rs = cs.executeQuery();
			while (rs.next()) {
				result = rs.getInt("FVERSIONID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (cs != null)
					cs.close();
				if (conn != null)
					conn.close();
			} catch(SQLException e) {}
		}
		
		if (passedVersion == result) {
			logger.warn("Exiting Method : getCompByVersionId() || Already latest null result.");
			return null;
		} else {
			logger.warn("Exiting Method : getCompByVersionId() || Return latest result");
			return this.getComponentById(elementId);
		}
	}

	/**
	 * testing class
	 */
	@Override
	public String getName() {
		
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("select fclient_name from tclient");
			rs = cs.executeQuery();
			while (rs.next()) {
				result = rs.getString("FCLIENT_NAME");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) 
					rs.close();
				if (cs != null)
					cs.close();
				if (conn != null)
					conn.close();
			} catch(SQLException e) {}
		}
		
		return result;
	}
	
	/**
	 * Helper class, which is used to convert the date to date String
	 * date string is used inside the data beans 
	 * @param date
	 * @return
	 */
	private String setDate(Date date) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		if (date == null) {
			return "";
		} else {
			return df.format(date);
		}
	}

}
