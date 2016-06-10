package com.dci.ComponentInfo.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.dci.ComponentInfo.beans.Component_ID;
import com.dci.ComponentInfo.beans.Component_Search;
import com.dci.ComponentInfo.utilities.ComponentMapper;

/**
 * The implementation class for interface GetCompInfoDAO. 
 * Not being used for now, since the JNDI lookup in the as400 server
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public class GetCompInforDAOimp implements GetCompInfoDAO {

	//It is used, so weird!
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private String compTypeAll;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void setCompTypeAll(String compTypeAll) {
		this.compTypeAll = compTypeAll;
	}

	@Override
	public List<Component_Search> getComponent(String key, String[] componentType) {
		String keyword = key;
		String compType = null;

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
		
		String sql = "call SP7_SEARCHCOMPONENTS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Component_Search> result = jdbcTemplateObject.query(sql, new ComponentMapper(), new Object[]{"dcisupportabf",65,compType,null,null,null,keyword,null,null,null,null,null,"n","n",20,null,null,null,null,null,null});
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Component_ID> getComponentById(String elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Component_ID> getCompByVersionId(String elementId, String versionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
