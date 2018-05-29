package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.User;

public interface UserService extends BaseService{

	public void saveOrUpdate(User	user) throws LMSException;
	
	public User getUniqueUser(String orgIdentity, String userName);
	
	 public void delete(Long id) throws LMSException;
	 
	 public User searchbyOrgIdentityAndUserName(String orgIdentity, String userName);
	 
		public User findbyOrganizationIdentifier(String orgIdentity);
	 
	
}