package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Groups;
import com.techademy.domain.JobAid;
import com.techademy.domain.User;

public interface JobAidService extends BaseService{

	public void saveOrUpdate(JobAid jobAid) throws LMSException;
	
	public JobAid getUniqueJobAid(String orgIdentity, String jobAidTag);
	
	 public void delete(Long id) throws LMSException;
	 
	 public JobAid searchbyOrgIdentityAndGobAidTag(String orgIdentity, String jobAidTag);
	 
	 public JobAid findbyOrganizationIdentifier(String orgIdentity);
	
}