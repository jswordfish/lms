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
import com.techademy.dao.SkillDao;
import com.techademy.domain.PointMatrix;
import com.techademy.domain.Skill;
import com.techademy.services.SkillService;

@Service("skillService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class SkillServiceImpl extends BaseServiceImpl<Long, Skill> implements SkillService{
	@Autowired
    protected SkillDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(SkillServiceImpl.class);
	
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
	public void saveOrUpdate(Skill skill) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In Save-Update Method Of Skill");
		
		if(skill.getOrganizationIdentifier()==null || skill.getSkillName()==null)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		if(skill.getOrganizationIdentifier().trim().length()==0 || skill.getSkillName().trim().length()==0)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		Skill skill2=getUniqueSkill(skill.getOrganizationIdentifier(), skill.getSkillName());
		if(skill2==null)
		{
			dao.persist(skill);
		}
		else
		{
			Mapper mapper=new DozerBeanMapper();
			skill.setId(skill.getId());
			mapper.map(skill,skill2);
			dao.merge(skill2);
		}
	}

	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method delete id passed "+id);
		Skill skill= dao.findById(id);
		dao.remove(skill);
	}

	@Override
	public Skill getUniqueSkill(String organizationIdentifier, String skillName) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier",organizationIdentifier);
		queryParams.put("skillName", skillName);
		
		List<Skill> skills = findByNamedQueryAndNamedParams("Skill.getUniqueSkill", queryParams);
		if(skills.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(skills.size() == 0) {
			return null;
		}
		else {
			return skills.get(0);
		}
	}
	
	@Override
	public List<Skill> findAll()
	{
		return dao.findAll();
	}

	@Override
	public Skill getAllByOrganisation(String organizationIdentifier) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", organizationIdentifier);
		List<Skill> skills = findByNamedQueryAndNamedParams("Skill.getByOrganizationIdentifier", queryParams);
		
		return skills.get(0);
	}
	
	
	public Skill FindAllByOrgnisation(String organizationIdentifier)
	{
		Skill skill=getAllByOrganisation(organizationIdentifier);
		if(skill!=null)
		{
			return skill;
		}
		return null;
	}


}
