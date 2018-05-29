package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Groups;
import com.techademy.domain.JobAid;
import com.techademy.domain.LearningAid;
import com.techademy.domain.User;

public interface LearningAidService extends BaseService{

	public void saveOrUpdate(LearningAid jobAid) throws LMSException;
	
	public LearningAid getUniqueLearningAid(String orgIdentity, String tag);
	
	 public void delete(Long id) throws LMSException;
	 
	 public LearningAid searchbyOrgIdentityAndTag(String orgIdentity, String tag);
	 
	 public LearningAid findbyOrganizationIdentifier(String orgIdentity);
}