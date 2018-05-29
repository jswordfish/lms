package com.techademy.services;

import java.util.List;

import com.techademy.common.LMSException;
import com.techademy.domain.Skill;
import com.techademy.domain.UserSkill;

public interface UserSkillService extends BaseService
{

	public void saveOrUpdate(UserSkill	userSkill) throws LMSException;
	
	public UserSkill getUniqueUserSkill(String userName,String organizationIdentifier);
	
	public List<UserSkill> getAllUserSkillByOrganization(String organizationIdentifier);
	
	public List<UserSkill> findAll();
	
	 public void delete(Long id) throws LMSException;
	 
}
