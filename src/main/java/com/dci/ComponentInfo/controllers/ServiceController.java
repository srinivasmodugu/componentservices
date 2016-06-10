package com.dci.ComponentInfo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dci.ComponentInfo.beans.ComponentList_ID;
import com.dci.ComponentInfo.beans.Component_ID;
import com.dci.ComponentInfo.beans.Component_Search;
import com.dci.ComponentInfo.beans.ComponentList_Search;
import com.dci.ComponentInfo.beans.DocBookElement;
import com.dci.ComponentInfo.beans.TableAttribute;
import com.dci.ComponentInfo.beans.Variable;
import com.dci.ComponentInfo.services.GetCompInfoDAO;
import com.dci.ComponentInfo.services.GetDocBookDAO;
import com.dci.ComponentInfo.services.GetTableAttributeDAO;
import com.dci.ComponentInfo.services.GetVariableDAO;
import com.dci.ComponentInfo.services.SaveComponentDAO;

/**
 * Service controller class. Manage all of the services mapping
 * @author Lu Dong
 * @creation_date 07/01/2015
 * @version 1.0
 */
@Controller
public class ServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

	//Component related service
	@Autowired
	private GetCompInfoDAO service;
	
	//Docbook related service
	@Autowired
	private GetDocBookDAO docbookservice;
	
	//variable related service
	@Autowired
	private GetVariableDAO variableservice;
	
	//save component service
	@Autowired
	private SaveComponentDAO savecompservice;
	
	//table attributes related service
	@Autowired
	private GetTableAttributeDAO tableattributeservice;
	
	/**
	 * Search component service
	 * @param keyword: the keyword included in the components
	 * @param compType: An array of all of the component types
	 * @return a list of component(component_search)
	 */
	@ResponseBody
	@RequestMapping(value="/services/getcomponentinfo", method=RequestMethod.GET)
	public ComponentList_Search getRequest(@RequestParam(value="keyword") String keyword, 
						@RequestParam(value="componentType") String[] compType) {
		logger.warn("Entering /services/getcomponentinfo service.");
		logger.debug("Keyword: " + keyword);
		logger.debug("component types number: " + compType.length);
		List<Component_Search> result = service.getComponent(keyword, compType);
		ComponentList_Search list = new ComponentList_Search();
//		list.setComponents(result);
		list.component = result;
		logger.warn("Exiting /services/getcomponentinfo service.");
		return list;
	}
	
	/**
	 * Update component by comparing version IDs
	 * @param elementId: the id of the component
	 * @param versionId: the versiond of the component
	 * @return a list of component(component_id)
	 */
	@ResponseBody
	@RequestMapping(value="/services/getcomponentbyid/{elementId}/{versionId}", method=RequestMethod.GET)
	public ComponentList_ID getVersionId(@PathVariable String elementId, @PathVariable String versionId) {
		logger.warn("Entering /services/getcomponentbyid/{elementId}/{versionId} service.");
		logger.debug("Elementinstance ID: " + elementId);
		logger.debug("Version ID: " + versionId);
		if (!checkIfNum(elementId)) {
			return null;
		} else if (!checkIfNum(versionId)) {
			ComponentList_ID result = new ComponentList_ID();
			List<Component_ID> comp = service.getComponentById(elementId);
			result.component = comp;
			logger.warn("Exiting /services/getcomponentbyid service "
											+ "and returning latest component!");
			return result;
		} else {
			ComponentList_ID result = new ComponentList_ID();
			List<Component_ID> comp = service.getCompByVersionId(elementId, versionId);
			result.component = comp;
			logger.warn("Exiting /services/getcomponentbyid service "
											+ "and returning component with version: " + versionId);
			return result;
		}		
	}
	
	/**
	 * Get component by component instance id
	 * @param elementId: the id of the component
	 * @return The target component, which may include the embedded components 
	 */
	@ResponseBody
	@RequestMapping(value="/services/getcomponentbyid/{elementId}", method=RequestMethod.GET)
	public ComponentList_ID getElementId(@PathVariable String elementId) {
		logger.warn("Entering /services/getcomponentby//{elementId}");
		logger.debug("Elementinstance ID: " + elementId);
		if (checkIfNum(elementId)) {
			ComponentList_ID result = new ComponentList_ID();
			List<Component_ID> comp = service.getComponentById(elementId);
			result.component = comp;
			logger.warn("Exiting /services/getcomponentbyid/{elementId} service");
			return result;
		} else {
			logger.warn("Exiting /services/getcomponentbyid/{elementId} "
										+ "with NULL: elementId is not a number");
			return null;
		}
	}
	
	/**
	 * Get the entire docbook xml information by providing the bookinstanceid
	 * @param bookinstanceid: the id of the target book instance
	 * @return a list of the docbook elements, which combine to the entire docbookxml
	 */
	@ResponseBody
	@RequestMapping(value="/services/docbook/getdocbook/{bookinstanceid}", method=RequestMethod.GET)
	public List<DocBookElement> getDocBook(@PathVariable String bookinstanceid) {
		logger.warn("Entering /services/docbook/getdocbook/{bookinstanceid} service.");
		logger.debug("Bookinstance ID: " + bookinstanceid);
		if (checkIfNum(bookinstanceid)) {
			List<DocBookElement> docbook = docbookservice.getDocBook(Integer.parseInt(bookinstanceid));
			logger.warn("Exiting /services/docbook/getdocbook/{bookinstanceid} service.");
			return docbook;
		} else {
			logger.warn("Exiting /services/docbook/getdocbook/{bookinstanceid} service"
					+ "Bookinstanceid is null");
			return null;
		}
	}
	
	/**
	 * Get the list of variable, which is related to specific book
	 * @param bookinstanceid
	 * @return a list of variables
	 */
	@ResponseBody
	@RequestMapping(value="/services/docbook/variables/{bookinstanceid}", method=RequestMethod.GET)
	public List<Variable> getVarList(@PathVariable String bookinstanceid) {
		logger.warn("Entering /services/docbook/variables/{bookinstanceid} service.");
		logger.debug("Bookinstance ID: " + bookinstanceid);
		if (checkIfNum(bookinstanceid)) {
			List<Variable> varList = variableservice.getVarList(Integer.parseInt(bookinstanceid));
			logger.warn("Exiting /services/docbook/variables/{bookinstanceid} service.");
			return varList;
		} else {
			logger.warn("Exiting /services/docbook/variables/{bookinstanceid} service "
					+ "bookinstance id is null");
			return null;
		}
	}
	
//	@RequestMapping(value="/services/sendComponentInfo", method=RequestMethod.POST)
//	public ResponseEntity<Component_ID> sendComponent(@RequestBody Component_ID comp) {
//		return new ResponseEntity<Component_ID>(comp, HttpStatus.OK);
//	}
	
	@RequestMapping(value="/services/gettableattributes/{clientid}/{bookinstanceid}")
	public ResponseEntity<TableAttribute> getTableAttribute(@PathVariable String clientid, @PathVariable String bookinstanceid) {
		logger.warn("Entering /services/gettableattributes/{clientid}/{bookinstanceid} service.");
		logger.debug("Client ID:" + clientid);
		logger.debug("Bookinstanceid" + bookinstanceid);
		if (checkIfNum(clientid) && checkIfNum(bookinstanceid)) {
			TableAttribute tableAttribute = tableattributeservice.getTableAttribute(Integer.parseInt(clientid), Integer.parseInt(bookinstanceid));
			ResponseEntity<TableAttribute> result = new ResponseEntity<TableAttribute>(tableAttribute, HttpStatus.OK);
			logger.warn("Exiting /services/gettableattributes/{clientid}/{bookinstanceid} with success response.");
			return result;
		} else {
			logger.warn("Exiting /services/gettableattributes/{clientid}/{bookinstanceid} with bad request.");
			return new ResponseEntity<TableAttribute>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/services/sendComponentInfo", method=RequestMethod.POST)
	public ResponseEntity<String> sendComponent(@RequestBody Component_ID comp) {
		logger.warn("Entering /services/sendComponentInfo service.");
		String data = "";
		if (comp.getFELEMENTINSTANCEID() == 0) {
			data = savecompservice.createComponent(comp);
		} else {
			data = savecompservice.saveComponent(comp);
		}
		
		ResponseEntity<String> result = null;
		if (data.equalsIgnoreCase("PARAERROR")) {
			result = new ResponseEntity<String>("Please check parameters", HttpStatus.BAD_REQUEST);
		} else if (data.equalsIgnoreCase("INTERNALERROR")) {
			result = new ResponseEntity<String>("Please contact Leo", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			result = new ResponseEntity<String>(data, HttpStatus.OK);
		}
		logger.warn("Exiting /services/sendComponentInfo service.");
		return result;
	}
	
	/**
	 * Testing mapping
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/services/test", method=RequestMethod.GET)
	public String getName() {
		return service.getName();
	}
	
	/**
	 * Check if the input parameter is a integer type. If it is not or it is null, 
	 * then return false, otherwire return true
	 * @param num: the number wanted to be checked
	 * @return if the input is a number or not
	 */
	public boolean checkIfNum(String num) {
		boolean flag = false;
		
		if (num == null) {
			return flag;
		}
		
		try {
			Integer.parseInt(num);
			flag = true;
		} catch (NumberFormatException e) {
			flag = false;
		}
		
		return flag;
	}
}
