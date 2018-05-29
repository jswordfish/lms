package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Groups;
import com.techademy.domain.Organization;
import com.techademy.domain.User;

public interface OrganizationService extends BaseService{

	public void saveOrUpdate(Organization notes) throws LMSException;
	
	public Organization getUniqueOrganization(String orgIdentity);
	
	 public void delete(Long id) throws LMSException;
	 
	 public Organization searchbyOrgIdentity(String orgIdentity);
		
	 
	
}
