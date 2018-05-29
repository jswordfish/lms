package com.techademy.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.slf4j.Logger;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.BadgeDao;
import com.techademy.dao.JPADAO;
import com.techademy.domain.Badge;
import com.techademy.services.BadgeService;

//@Service("badgeService")
@Component("moduleService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class BadgeServiceImpl extends BaseServiceImpl<Long, Badge> implements BadgeService{
	
	@Autowired
    protected BadgeDao dao;
	
	
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(BadgeServiceImpl.class);
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) dao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    
    @Override
	public void saveOrUpdate(Badge badge) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method save Or Update");
		
		if(badge.getBadgeName()==null||badge.getOrganizationIdentifier()==null)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		if(badge.getBadgeName().trim().length()==0||badge.getOrganizationIdentifier().trim().length()==0)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		Badge badge2=getUniqueBadge(badge.getOrganizationIdentifier(), badge.getBadgeName());
		if(badge2 == null) {
			//create
			dao.persist(badge);
			
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			badge.setId(badge2.getId());
			mapper.map(badge, badge2);
			
		}
	}
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }


	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method delete");
		Badge badge=dao.findById(id);
		dao.remove(badge);
	}

	@Override
	public Badge getUniqueBadge(String orgIdentity, String badgeName) 
	{
		logger.info("In the method getUniqueUser ");
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("badgeName", badgeName);
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Badge> badge = findByNamedQueryAndNamedParams("Badge.getUniqueBadge", queryParams);
		
		if(badge.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(badge.size() == 0) {
			return null;
		}
		else {
			return badge.get(0);
		}
	}

	@Override
	public Badge SearchByOrganizationIdentifierAndBadgeName(String orgIdentity, String badgeName) {
		// TODO Auto-generated method stub
		logger.info("In the method SearchByOrganizationIdentifierAndBadgeName");
		Badge b=getUniqueBadge(orgIdentity, badgeName);
		if(b!=null)
		{
			return b;
		}
		return null;
	}

	@Override
	public Badge findByOrganizationIdentifier(String orgIdentity) {
		// TODO Auto-generated method stub
		logger.info("In the method SearchByOrganizationIdentifierAndBadgeName");
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Badge> badge = findByNamedQueryAndNamedParams("Badge.findByOrganizationIdentifier", queryParams);
		if(badge.size() == 0) {
			return null;
		}
		else {
			return badge.get(0);
		}
	}

	


}
