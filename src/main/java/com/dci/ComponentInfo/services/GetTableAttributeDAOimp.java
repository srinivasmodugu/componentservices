package com.dci.ComponentInfo.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dci.ComponentInfo.beans.Book2Flexible;
import com.dci.ComponentInfo.beans.FlexibleDefaultCol;
import com.dci.ComponentInfo.beans.TableAttribute;

/**
 * Implementation class for GetTableAttributeDAO service
 * 
 * Parameters:
 * 			clientid;
 * 			bookinstanceid;
 * 
 * Stored procedures involved:
 * 
 * 	sp_dtgetbook2flexible :  get all of the table info within the document, including location data
 * 	sp_dtgetflexibledefaultcol : get the default attribute for all of the flexible tables
 * 
 * @author Lu Dong
 * @creation_date 08/13/2015
 * @version 1.0
 */
public class GetTableAttributeDAOimp implements GetTableAttributeDAO {
	
	// Get Logger
	private static final Logger logger = LoggerFactory.getLogger(GetTableAttributeDAOimp.class);

	private DataSource ds;
	
	@Override
	public TableAttribute getTableAttribute(int clientid, int bookinstanceid) {
		logger.warn("Entering Method : getTableAttribute().");
		InitialContext ctx = null;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		@SuppressWarnings("rawtypes")
		Hashtable parms = new Hashtable();
		TableAttribute result = new TableAttribute();
		ArrayList<Book2Flexible> book2Flexiblearray = new ArrayList<Book2Flexible>();
		ArrayList<FlexibleDefaultCol> flexibleDefaultColarray = new ArrayList<FlexibleDefaultCol>();
		
		try {
			ctx = new InitialContext(parms);
			ds = (javax.sql.DataSource) ctx.lookup("jdbc/docubuildrp");
			conn = ds.getConnection("DCIAPPUSER", "DCIUSER");
			cs = conn.prepareCall("{call sp_dtgetbook2flexible(?, ?)}");
			cs.setInt(1, clientid);
			cs.setInt(2, bookinstanceid);
			logger.warn("call sp_dtgetbook2flexible("+ clientid + "," + bookinstanceid + ")");
			rs = cs.executeQuery();
			while (rs.next()) {
				Book2Flexible book2Flexible = new Book2Flexible();
				book2Flexible.setFbookinstanceid(rs.getInt(1));
				book2Flexible.setFelementinstanceid(rs.getInt(2));
				book2Flexible.setFbookdetailid(rs.getInt(3));
				book2Flexible.setFqualdata_tabletype(rs.getString(4));
				book2Flexible.setFcolattribute(rs.getString(5));
				book2Flexiblearray.add(book2Flexible);
			}
			
			logger.warn("Getting result for Book2Flexible and size is " + book2Flexiblearray.size());
			// close the result set and statement to prevent from messing up data
			cs.close();
			rs.close();
			
			cs = conn.prepareCall("{call sp_dtgetflexibledefaultcol(?, ?)}");
			cs.setInt(1, clientid);
			cs.setInt(2, bookinstanceid);
			logger.warn("call sp_dtgetflexibledefaultcol("+ clientid + "," + bookinstanceid +")");
			rs = cs.executeQuery();
			while (rs.next()) {
				FlexibleDefaultCol flexibleDefaultCol = new FlexibleDefaultCol();
				flexibleDefaultCol.setFtableidentityid(rs.getInt("ftableidentityid"));
				flexibleDefaultCol.setFcolumn(rs.getInt("fcolumn"));
				flexibleDefaultCol.setFcolumn_description(rs.getString("fcolumn_description"));
				flexibleDefaultCol.setFcolumn_description(rs.getString("fcolumn_attribute"));
				flexibleDefaultCol.setFbook_type(rs.getString("fbook_type"));
				flexibleDefaultCol.setFqualdata_tabletype(rs.getString("fqualdata_tabletype"));
				flexibleDefaultColarray.add(flexibleDefaultCol);
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
		
		logger.warn("Getting result for FlexibleDefaultCol and the size is: " + flexibleDefaultColarray.size());
		result.setBook2Flexible(book2Flexiblearray.toArray(new Book2Flexible[book2Flexiblearray.size()]));
		result.setFlexibleDefaultCol(flexibleDefaultColarray.toArray(new FlexibleDefaultCol[flexibleDefaultColarray.size()]));
		logger.warn("Exiting Method : getTableAttribute()");
		return result;
	}

}
