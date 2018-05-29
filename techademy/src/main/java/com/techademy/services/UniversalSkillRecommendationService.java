package com.techademy.services;

import java.util.List;

import com.techademy.common.LMSException;
import com.techademy.domain.Skill;
import com.techademy.domain.UniversalSkillRecommendation;

public interface UniversalSkillRecommendationService extends BaseService{

	public void saveOrUpdate(UniversalSkillRecommendation	recommendation) throws LMSException;
	
	public UniversalSkillRecommendation getUniqueUniversalSkillRecommendation(String organizationIdentifier,String universalSkill );
	
	 public void delete(Long id) throws LMSException;
	 
	public UniversalSkillRecommendation findByOrganizationIdentifierAndUniversalSkill(String organizationIdentifier,String universalSkill);

	public List<UniversalSkillRecommendation> findAll();
}
