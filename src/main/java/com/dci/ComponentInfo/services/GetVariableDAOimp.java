package com.dci.ComponentInfo.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dci.ComponentInfo.beans.Variable;

/**
 * Implementation class for GetVariableDAO service interface
 * Return all of the resolved variable values of a docbook xml
 * 
 * --Stored procedure included
 * 
 * ----sp5_GETRENDERLEGACYVAR(?, null) : return the legacy variable values within the docbook
 * 			entites (variable) is still maintained, but no more use for new clients
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class GetVariableDAOimp implements GetVariableDAO {
	
	// Get logger
	private static final Logger logger = LoggerFactory.getLogger(GetVariableDAOimp.class);

	private DataSource ds;

	@Override
	public List<Variable> getVarList(int bookinstanceid) {
		logger.warn("Entering Method : getVarList().");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		List<Variable> result = new ArrayList<Variable>();
		
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{call sp5_GETRENDERLEGACYVAR(?, null)}");
			cs.setInt(1, bookinstanceid);
			logger.warn("call sp5_GETRENDERLEGACYVAR(" + bookinstanceid +", null)");
			rs = cs.executeQuery();
			while (rs.next()) {
				Variable var = new Variable();
				var.setVariableId(rs.getInt("FENTITYINSTANCEID"));
				var.setEntityName(rs.getString("ENTITY_NAME"));
				var.setEntityValue(rs.getString("ENTITY_VALUE"));
				result.add(var);
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
		
		logger.warn("Exiting Method : getVarList() || result size: " + result.size());
		return result;
	}

}
