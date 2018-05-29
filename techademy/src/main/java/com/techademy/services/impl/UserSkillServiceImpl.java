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
import com.techademy.dao.UserSkillDao;
import com.techademy.domain.PointMatrix;
import com.techademy.domain.Skill;
import com.techademy.domain.UniversalSkillRecommendation;
import com.techademy.domain.UserSkill;
import com.techademy.services.UserSkillService;

@Service("userSkillService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class UserSkillServiceImpl extends BaseServiceImpl<Long, UserSkill> implements UserSkillService{
	@Autowired
    protected UserSkillDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(UserSkillServiceImpl.class);
	
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
	public void saveOrUpdate(UserSkill userSkill) throws LMSException {
		// TODO Auto-generated method stub
		if(userSkill.getUserName()==null || userSkill.getOrganizationIdentifier()==null || userSkill.getSkills()==null)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(userSkill.getUserName().trim().length()==0 || userSkill.getOrganizationIdentifier().trim().length()==0 || !userSkill.getSkills().isEmpty())
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		UserSkill userSkill2=getUniqueUserSkill(userSkill.getUserName(),userSkill.getOrganizationIdentifier());
	
		
		if(userSkill2==null)
		{
			dao.persist(userSkill);
		}
		else 
		{
			Mapper mapper=new DozerBeanMapper();
			userSkill.setId(userSkill2.getId());
			mapper.map(userSkill,userSkill2);
			dao.merge(userSkill2);
	}
	}

	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		
		UserSkill skill=dao.findById(id);
		dao.remove(skill);
		
	}

	@Override
	public UserSkill getUniqueUserSkill(String userName, String organizationIdentifier) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("userName",userName);
		//queryParams.put("skill",skill.getSkillName());
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
		
	}

	@Override
	public List<UserSkill> getAllUserSkillByOrganization(String organizationIdentifier) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier",organizationIdentifier);
		
		List<UserSkill> userSkills = findByNamedQueryAndNamedParams("UserSkill.getAllUserSkillByOrganization", queryParams);
		if(userSkills.size() == 0) {
			return null;
		}
		else {
			return userSkills;
		}
	}
	
			@Override
			public List<UserSkill> findAll()
			{
				return dao.findAll();
			}
	}

	



	

