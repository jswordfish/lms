package com.techademy.services;

import java.util.List;

import com.techademy.common.LMSException;
import com.techademy.domain.Badge;


public interface BadgeService extends BaseService {

	public void saveOrUpdate(Badge module) throws LMSException;
	
	public Badge getUniqueBadge(String orgIdentity, String badgeName);
	
	 public void delete(Long id) throws LMSException;
	 
	 public Badge SearchByOrganizationIdentifierAndBadgeName(String orgIdentity, String badgeName);
	 
	 public Badge findByOrganizationIdentifier(String orgIdentity);
	 
	
}