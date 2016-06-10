package com.dci.ComponentInfo.services;

import java.util.List;

import com.dci.ComponentInfo.beans.Component_ID;
import com.dci.ComponentInfo.beans.Component_Search;

/**
 * Interface incldues all of the component component related services
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public interface GetCompInfoDAO {
	
	//search component by keyword and component type
	public List<Component_Search> getComponent(String key, String[] componentType);
	
	//search component by component id
	public List<Component_ID> getComponentById(String elementId);
	
	// test serivce
	public String getName();
	
	//search component by providing version id as well
	public List<Component_ID> getCompByVersionId(String elementId, String versionId);
}
