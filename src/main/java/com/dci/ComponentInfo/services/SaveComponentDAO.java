package com.dci.ComponentInfo.services;

import com.dci.ComponentInfo.beans.Component_ID;

public interface SaveComponentDAO {

	String saveComponent(Component_ID comp);
	
	String createComponent(Component_ID comp);
}
