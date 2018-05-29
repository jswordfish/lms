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
import com.techademy.dao.JPADAO;
import com.techademy.dao.UniversalSkillRecommendationDao;
import com.techademy.domain.UniversalSkillRecommendation;
import com.techademy.services.UniversalSkillRecommendationService;

@Service("universalSkillRecommendationService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class UniversalSkillRecommendationServiceImpl extends BaseServiceImpl<Long, UniversalSkillRecommendation> implements UniversalSkillRecommendationService{
	@Autowired
    protected UniversalSkillRecommendationDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(UniversalSkillRecommendationServiceImpl.class);
	
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
	public void saveOrUpdate(UniversalSkillRecommendation recommendation) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In Method Save-Update of UniversalSkillRecommendation ");
		
		if(recommendation.getOrganizationIdentifier()==null || recommendation.getUniversalSkill()== null)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		if(recommendation.getOrganizationIdentifier().trim().length()==0 || recommendation.getUniversalSkill().trim().length()==0)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		UniversalSkillRecommendation recommendation2=getUniqueUniversalSkillRecommendation(recommendation.getOrganizationIdentifier(),recommendation.getUniversalSkill());
		
		if(recommendation2==null)
		{
			dao.persist(recommendation);
		}
		else
		{
			Mapper mapper=new DozerBeanMapper();
			recommendation.setId(recommendation2.getId());
			mapper.map(recommendation,recommendation2);
			dao.merge(recommendation2);
		}
				
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		UniversalSkillRecommendation skillRecommendation=dao.findById(id);
		dao.remove(skillRecommendation);
		
	}

	@Override
	public UniversalSkillRecommendation getUniqueUniversalSkillRecommendation(String organizationIdentifier,
			String universalSkill) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier",organizationIdentifier);
		queryParams.put("universalSkill",universalSkill);
		
		List<UniversalSkillRecommendation> skillRecommendations = findByNamedQueryAndNamedParams("UniversalSkillRecommendation.getUniqueUniversalSkillRecommendation", queryParams);
		if(skillRecommendations.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(skillRecommendations.size() == 0) {
			return null;
		}
		else {
			return skillRecommendations.get(0);
		}
		
	}

	@Override
	public UniversalSkillRecommendation findByOrganizationIdentifierAndUniversalSkill(String organizationIdentifier,
			String universalSkill) {
		// TODO Auto-generated method stub
		UniversalSkillRecommendation u=getUniqueUniversalSkillRecommendation(organizationIdentifier, universalSkill);
		if(u!=null)
		{
			return u;
		}
		
		return null;
	}


	@Override
	public List<UniversalSkillRecommendation> findAll()
	{
		return dao.findAll();
	}
	
/*	@Override
	public UserSkill getUniqueUserSkill(String userName, String skill, String organizationIdentifier) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("userName",userName );
		queryParams.put("skill",skill);
		queryParams.put("organizationIdentifier",organizationIdentifier);
		
		List<UserSkill> userSkills = findByNamedQueryAndNamedParams("UserSkill.getUniqueUserSkill", queryParams);
		if(userSkills.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(userSkills.size() == 0) {
			return null;
		}
		else {
			return userSkills.get(0);
		}
	}*/
}
