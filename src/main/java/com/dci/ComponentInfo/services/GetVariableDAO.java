package com.dci.ComponentInfo.services;

import java.util.List;

import com.dci.ComponentInfo.beans.Variable;

/**
 * Get docbookxml related variables service
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public interface GetVariableDAO {
	// return all of the variable values of the related docbook
	List<Variable> getVarList(int bookinstanceid);
}
