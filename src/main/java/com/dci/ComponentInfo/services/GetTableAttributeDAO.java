package com.dci.ComponentInfo.services;

import com.dci.ComponentInfo.beans.TableAttribute;

/**
 * Get table attribute service interface
 * @author Lu Dong
 * @creation_date 08/13/2015
 * @version 1.0
 */
public interface GetTableAttributeDAO {
	
	TableAttribute getTableAttribute(int clientid, int bookinstanceid);
	
}
