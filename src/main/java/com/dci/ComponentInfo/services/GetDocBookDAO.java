package com.dci.ComponentInfo.services;

import java.util.List;

import com.dci.ComponentInfo.beans.DocBookElement;

/**
 * Get docbookxml service interface
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
public interface GetDocBookDAO {
	//Get the entire docbookxml by passing the bookinstanceid
	List<DocBookElement> getDocBook(int bookinstanceid);
}
