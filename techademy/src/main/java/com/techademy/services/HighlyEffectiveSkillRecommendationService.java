package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Groups;
import com.techademy.domain.HighlyEffectiveSkillRecommendation;
import com.techademy.domain.User;

public interface HighlyEffectiveSkillRecommendationService extends BaseService{

	public void saveOrUpdate(HighlyEffectiveSkillRecommendation effectiveSkillRecommendation) throws LMSException;
	
	public HighlyEffectiveSkillRecommendation getUniqueHighlyEffectiveSkillRecommendation(String organizationIdentifier, String highlyEffectiveSkill);
	
	 public void delete(Long id) throws LMSException;
	 
	 public HighlyEffectiveSkillRecommendation searchbyOrganizationIdentifierAndHighlyEffectiveSkill(String organizationIdentifier, String highlyEffectiveSkill);
	
	 public HighlyEffectiveSkillRecommendation findbyOrganizationIdentifier(String orgIdentity);
}
