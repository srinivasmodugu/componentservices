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

import com.dci.ComponentInfo.beans.DocBookElement;

/**
 * Implementaion class for GetDocBookDao service interface
 * Simple implementation for java jdbc
 * 
 * --Stored procedure included
 * 
 * ----sp5_getrenderdoc() : return the entired docbookxml data
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class GetDocBookDAOimp implements GetDocBookDAO {
	
	// Get logger
	private static final Logger logger = LoggerFactory.getLogger(GetDocBookDAOimp.class);
	
	private DataSource ds;
	
	@Override
	public List<DocBookElement> getDocBook(int bookinstanceid) {
		logger.warn("Entering Method : getDocBook()");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		List<DocBookElement> result = new ArrayList<DocBookElement>();
		
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{call sp5_getrenderdoc(?,?,?,?,?,?,?,?,?)}");
			cs.setInt(1, bookinstanceid);
			cs.registerOutParameter(2, java.sql.Types.DATE);
			cs.registerOutParameter(3, java.sql.Types.DATE);
			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
			cs.registerOutParameter(6, java.sql.Types.VARCHAR);
			cs.registerOutParameter(7, java.sql.Types.VARCHAR);
			cs.registerOutParameter(8, java.sql.Types.VARCHAR);
			cs.registerOutParameter(9, java.sql.Types.VARCHAR);
			logger.warn("call sp5_getrenderdoc("+ bookinstanceid +",?,?,?,?,?,?,?,?)");
			rs = cs.executeQuery();
			while (rs.next()) {
				DocBookElement docBookElement = new DocBookElement();
				docBookElement.setFELEMENT_ID(rs.getString("FELEMENT_ID"));
				docBookElement.setFELEMENTINSTANCEID(rs.getInt("FELEMENTINSTANCEID"));
				docBookElement.setFDATA(rs.getString("FQUALDATA"));
				docBookElement.setFGLOBALORDINAL(String.valueOf(rs.getBigDecimal("FGLOBALORDINAL")));
				docBookElement.setFCOMPSTATUS(rs.getInt("FCOMPONENT_STATUS"));
				docBookElement.setFLANGUAGE(rs.getInt("FLANGUAGEID"));
				result.add(docBookElement);
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
		logger.warn("Exiting Method : getDocBook() || result size : " + result.size());
		return result;
	}

}
