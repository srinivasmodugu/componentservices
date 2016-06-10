package com.dci.ComponentInfo.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dci.ComponentInfo.beans.Component_ID;
import com.dci.ComponentInfo.utilities.HelperClass;

public class SaveComponentDAOimp implements SaveComponentDAO {

	// Get logger
	private static final Logger logger = LoggerFactory.getLogger(SaveComponentDAOimp.class);
	
	private DataSource ds;
	private int clientId;

	@SuppressWarnings("unused")
	@Override
	public String saveComponent(Component_ID comp) {
		logger.warn("Entering Method : saveComponent().");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		Statement stmt = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		String lastChangedTime = "";
		String lastChangedBy = "";
		
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			stmt = conn.createStatement();
			String sql = "SELECT FQUALDATAID FROM TELEMENTINSTANCE WHERE FELEMENTINSTANCEID = " + comp.getFELEMENTINSTANCEID();
			rs = stmt.executeQuery(sql);
			int qualdataid = Integer.MIN_VALUE;
			while (rs.next()) {
				qualdataid = rs.getInt("FQUALDATAID");
			}
			cs = conn.prepareCall("{call SP4_ITSETELEMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, comp.getFLASTCHANGEDBY());
			cs.setBigDecimal(2, HelperClass.getBigDecimal(comp.getFELEMENTINSTANCEID()));
			cs.setBigDecimal(3, HelperClass.getBigDecimal(qualdataid));
			cs.setNull(4, Types.BIGINT);
			cs.setNull(5, Types.BIGINT);
			if (HelperClass.stringToDate(comp.getFEFFECTIVEDATE()) != null)
				cs.setDate(6,HelperClass.stringToDate(comp.getFEFFECTIVEDATE()));
			else 
				cs.setNull(6, Types.DATE);
			if (HelperClass.stringToDate(comp.getFEXPIRATIONDATE()) != null)
				cs.setDate(7, HelperClass.stringToDate(comp.getFEXPIRATIONDATE()));
			else
				cs.setNull(7, Types.DATE);
			cs.setString(8, comp.getFDATA_DESC());
			cs.setNull(9, Types.VARCHAR);
			cs.setNull(10, Types.VARCHAR);
			cs.setNull(11, Types.VARCHAR);
			cs.setString(12, comp.getFDATA());
			cs.setNull(13, Types.VARCHAR);
			cs.setNull(14, Types.NULL);
			cs.setNull(15, Types.NULL);
			cs.setNull(16, Types.VARCHAR);
			cs.registerOutParameter(17, java.sql.Types.TIMESTAMP);
			cs.registerOutParameter(18, java.sql.Types.VARCHAR);
			logger.warn("call SP4_ITSETELEMENT("+ comp.getFLASTCHANGEDBY() 
					+","+ HelperClass.getBigDecimal(comp.getFELEMENTINSTANCEID()) +","
					+ HelperClass.getBigDecimal(qualdataid) +",null,null,null,null,"
					+ comp.getFDATA_DESC() +",null,null,null,"+ comp.getFDATA() +",null,null,null,"
							+ "null,?,?)");
			cs.executeUpdate();
			if (cs.getTimestamp(17) != null) {
				lastChangedTime = cs.getTimestamp(17).toString();
			}
			if (cs.getString(18) != null) {
				lastChangedBy = cs.getString(18);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Exiting Method : saveComponent() || Para error!");
			return "PARAERROR";
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
		logger.warn("Exiting Method : saveComponent() || save successfully!");
		return Integer.toString(comp.getFELEMENTINSTANCEID());
	}

	@Override
	public String createComponent(Component_ID comp) {
		logger.warn("Entering Method : createComponen()");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		List<String> ids = new ArrayList<String>();

		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{call SP5_ITISVALIDNAME(?,?,?,?)}");
			cs.setString(1, comp.getFCREATEDBY());
			cs.setInt(2, clientId);
			cs.setNull(3, Types.BIGINT);
			cs.setString(4, comp.getFCOMPSTATUS_DESC());
			logger.warn("call SP5_ITISVALIDNAME("+ comp.getFCREATEDBY() +","+ clientId +",null,"
						+ comp.getFCOMPSTATUS_DESC() +")");
			rs = cs.executeQuery();
			while (rs != null && rs.next()) {
				ids.add(rs.getString(1));
			}
			
			// Close the first statement and the first result set
			cs.close();
			rs.close();
			
			if (ids != null && ids.size() > 0) {
				logger.warn("Exiting Method : createComponen() || component name is not valid.");
				return "exits";
			}
			
			cs = conn.prepareCall("{call sp_itputElementQual3(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, comp.getFCREATEDBY());
			cs.setInt(2, clientId);
			Date effDate = HelperClass.stringToDate(comp.getFEFFECTIVEDATE());
			if (effDate == null) {
				cs.setNull(3, Types.DATE);
			} else {
				cs.setDate(3, effDate);
			}
			Date expDate = HelperClass.stringToDate(comp.getFEXPIRATIONDATE());
			if (expDate == null) {
				cs.setNull(4, Types.DATE);
			} else {
				cs.setDate(4, expDate);
			}
			cs.setString(5, comp.getFELEMENT_ID());
			cs.setNull(6, Types.BIGINT);
			cs.setNull(7, Types.BIGINT);
			cs.setNull(8, Types.INTEGER);
			cs.setString(9, comp.getFDATA_DESC());
			cs.setNull(10, Types.VARCHAR);
			cs.setNull(11, Types.VARCHAR);
			cs.setNull(12, Types.VARCHAR);
			cs.setString(13, comp.getFDATA());
			cs.registerOutParameter(14, java.sql.Types.BIGINT);
			cs.registerOutParameter(15, java.sql.Types.BIGINT);
			logger.warn("call sp_itputElementQual3("+ comp.getFCREATEDBY() +","+ clientId +",null,null,"
					+ comp.getFELEMENT_ID() +",null,null,null,"+ comp.getFDATA_DESC() +",null,"
					+ "null,null,"+ comp.getFDATA() +",?,?)");
			cs.executeUpdate();
			long elementInstanceId = cs.getLong(15);
			
			if (elementInstanceId > 0) {
				logger.warn("Exiting Method : createComponen() || creation is successful.");
				return String.valueOf(elementInstanceId);
			}
			logger.warn("Exiting Method : createComponent() || Internal Error.");
			return "INTERNALERROR";
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Exiting Method: createComponent() || Para Error");
			return "PARAERROR";
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
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
}
