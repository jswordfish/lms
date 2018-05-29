
package com.techademy.services;

import java.util.List;
import java.util.Map;

import com.techademy.common.LMSException;
import com.techademy.domain.Groups;


public interface GroupService extends BaseService{

	public void saveOrUpdate(Groups group) throws LMSException;
	
	
	public Groups getUniqueGroup(String orgIdentity, String groupName);
	 public void delete(Long id) throws LMSException;
	 
	 
		public Groups searchbyOrgIdentityAndGroupName(String orgIdentity, String groupName);
		
		public Groups findbyOrganizationIdentifier(String orgIdentity);
		
		public Groups findAll(Groups group);
		
		public List<Groups> findAll();


	
	
}
