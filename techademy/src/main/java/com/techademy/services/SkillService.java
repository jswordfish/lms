package com.techademy.services;

import java.util.List;

import com.techademy.common.LMSException;
import com.techademy.domain.Skill;

public interface SkillService extends BaseService{

	public void saveOrUpdate(Skill	skill) throws LMSException;
	
	public Skill getUniqueSkill(String organizationIdentifier,String skillName);
	
	public Skill getAllByOrganisation(String organizationIdentifier);
	
	 public void delete(Long id) throws LMSException;
	 
	 public List<Skill> findAll();
	
}

