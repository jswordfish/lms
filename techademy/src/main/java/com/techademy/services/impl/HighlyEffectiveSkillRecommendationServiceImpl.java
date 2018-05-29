package com.techademy.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.HighlyEffectiveSkillRecommendationDao;
import com.techademy.dao.JPADAO;
import com.techademy.domain.HighlyEffectiveSkillRecommendation;
import com.techademy.domain.User;
import com.techademy.services.HighlyEffectiveSkillRecommendationService;

@Service("highlyEffectiveSkillRecommendationService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class HighlyEffectiveSkillRecommendationServiceImpl extends BaseServiceImpl<Long, HighlyEffectiveSkillRecommendation> implements HighlyEffectiveSkillRecommendationService{
	
	@Autowired
    protected HighlyEffectiveSkillRecommendationDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(HighlyEffectiveSkillRecommendationServiceImpl.class);
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) dao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(HighlyEffectiveSkillRecommendation effectiveSkillRecommendation) throws LMSException {
		if(effectiveSkillRecommendation.getHighlyEffectiveSkill() == null || effectiveSkillRecommendation.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(effectiveSkillRecommendation.getHighlyEffectiveSkill().trim().length() == 0 || effectiveSkillRecommendation.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		HighlyEffectiveSkillRecommendation highlyEffectiveSkillRecommendation2 = getUniqueHighlyEffectiveSkillRecommendation(effectiveSkillRecommendation.getOrganizationIdentifier(), effectiveSkillRecommendation.getHighlyEffectiveSkill());
		//step 2
		//for save-create
		if(highlyEffectiveSkillRecommendation2 == null) {
			//create
			dao.persist(effectiveSkillRecommendation);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			effectiveSkillRecommendation.setId(highlyEffectiveSkillRecommendation2.getId());
			mapper.map(effectiveSkillRecommendation, highlyEffectiveSkillRecommendation2);
			dao.merge(highlyEffectiveSkillRecommendation2);
		}
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		logger.info("In the method delete id passed "+id);
		HighlyEffectiveSkillRecommendation effectiveSkillRecommendation = dao.findById(id);
		dao.remove(effectiveSkillRecommendation);
		
	}

	@Override
	public HighlyEffectiveSkillRecommendation getUniqueHighlyEffectiveSkillRecommendation(String organizationIdentifier,
			String highlyEffectiveSkill) {
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("highlyEffectiveSkill", highlyEffectiveSkill);
		queryParams.put("organizationIdentifier", organizationIdentifier);
		
		List<HighlyEffectiveSkillRecommendation> highlyEffectiveSkillRecommendations = findByNamedQueryAndNamedParams(
				"HighlyEffectiveSkillRecommendation.getUniqueHighlyEffectiveSkillRecommendation", queryParams);
		if(highlyEffectiveSkillRecommendations.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(highlyEffectiveSkillRecommendations.size() == 0) {
			return null;
		}
		else {
			return highlyEffectiveSkillRecommendations.get(0);
		}
	}

	@Override
	public HighlyEffectiveSkillRecommendation searchbyOrganizationIdentifierAndHighlyEffectiveSkill(
			String organizationIdentifier, String highlyEffectiveSkill) {
		
		HighlyEffectiveSkillRecommendation h=getUniqueHighlyEffectiveSkillRecommendation(organizationIdentifier, highlyEffectiveSkill);
		if(h!=null)
		{
			System.out.println(h);
			return h;
		}
		
		return null;
	}

	@Override
	public HighlyEffectiveSkillRecommendation findbyOrganizationIdentifier(String orgIdentity) {
	
		Map<String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<HighlyEffectiveSkillRecommendation> highlyEffectiveSkillRecommendations = findByNamedQueryAndNamedParams(
				"HighlyEffectiveSkillRecommendation.getUniqueOrganizationIdentifier", queryParams);
	
		if(highlyEffectiveSkillRecommendations.size() == 0) {
			return null;
		}
		else {
			return highlyEffectiveSkillRecommendations.get(0);
		}
	}

	

	
	
    
	
	

}

