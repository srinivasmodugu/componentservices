ComponentInfo project

--Overwiew
		
	The application provide five kinds of RESTful web services
	
	1. Searching components by providing keyword and component type and return all of related components
	2. Searching component by providing specific component id (elementinstanceid), and return target component with embedded components
	3. Searching component by providng component id and specific version id. The service will return the component information only if 
		there is a new version of this component.
	4. Return the entire docbookxml of specific the book id (bookinstanceid)
	5. Return all of the variables' values of specifc docbookxml 
	
	The application is implemented by Java Spring framework under eclipse IDE.
	
--IDE

	Eclipse kepler (download from here: https://www.eclipse.org/downloads/packages/release/kepler/sr2)
	Websphere Liberty Profile server (download from eclipse marketplace)
	
	
--Services

  	All of the services can be found in the ServiceController.java file under com.dci.ComponentInfo.controllers package. 
  	
  - Searching components by keyword and component type
  
  	-- URI :  /ComponentInfo/services/getcomponentinfo
  	-- HTTP method : GET
  	-- Parameters: 
  				--	keyword : 
  				--  componentType : could be a single component type or a list of component types
  	-- Return values type: XML
  	-- Return values: A list of component information
  	
  	
  - Searching componet by specific component id
  
    -- URI : /ComponentInfo/services/getcomponentbyid/{elementinstanceid}
    -- HTTP method : GET
    -- Parameter: 
    			--  elementinstanceid : component id (parameter is inside the URL path)
    
    -- Return value type : XML
    -- Return values: A single component information with embedded components
    
  - Search component by component id and version id
  
    -- URI : /ComponentInfo/services/getcomponentbyid/{elementinstanceid}/{versionid}
    -- HTTP method : GET
    -- Parameters :
    			-- elementinstanceid : component id (inside the URL path)
    			-- versiondi : the version of the component (inside the URL path)
    -- Return value type : XML
    -- Return value : A single component information if new version of the component is found]
    					Or null if there is no updated component
    					
  - Get entire docbookxml service
  
    -- URI : /ComponentInfo/services/docbook/getdocbook/{bookinstanceid}
    -- HTTP method : GET
    -- Parameters :
    			-- bookinstanceid : book instance id (inside the URL path)
    -- Return value type : JSON
    -- Return value : entire docbookxml information of a specfic book
    
  - Get variable values of a specific docbook service
  
    -- URI : /ComponentInfo/services/docbook/variables/{bookinstanceid}
    -- HTTP method : GET
    -- Parameters :
    			-- bookinstanceid : book instance id (inside the URL path)
    -- Return value type : JSON
    -- Return value : a list of variable values of a specific book
