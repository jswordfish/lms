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
import com.techademy.dao.LearningAidDao;
import com.techademy.domain.LearningAid;
import com.techademy.domain.User;
import com.techademy.services.LearningAidService;

@Service("learningAidService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class LearningAidServiceImpl extends BaseServiceImpl<Long, LearningAid> implements LearningAidService{
	@Autowired
    protected LearningAidDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(LearningAidServiceImpl.class);
	
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
	public void saveOrUpdate(LearningAid jobAid) throws LMSException {
		if(jobAid.getTag() == null || jobAid.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(jobAid.getTag().trim().length() == 0 || jobAid.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		LearningAid learningAid2 = getUniqueLearningAid(jobAid.getOrganizationIdentifier(), jobAid.getTag());
		//step 2
		//for save-create
		if(learningAid2 == null) {
			//create
			dao.persist(jobAid);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			jobAid.setId(learningAid2.getId());
			mapper.map(jobAid, learningAid2);
			dao.merge(learningAid2);
		}
		
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		logger.info("In the method delete id passed "+id);
		LearningAid learningAid = dao.findById(id);
		dao.remove(learningAid);
		
	}

	@Override
	public LearningAid getUniqueLearningAid(String orgIdentity, String tag) {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		queryParams.put("tag", tag);
		
		
		List<LearningAid> learningAids = findByNamedQueryAndNamedParams(
				"LearningAid.getUniqueLearningAid", queryParams);
		if(learningAids.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(learningAids.size() == 0) {
			return null;
		}
		else {
			return learningAids.get(0);
		}
	}

	@Override
	public LearningAid searchbyOrgIdentityAndTag(String orgIdentity, String tag) {
		LearningAid l=getUniqueLearningAid(orgIdentity, tag);
		if(l!=null)
		{
			System.out.println(l);
			return l;
		}
		return null;
	}

	@Override
	public LearningAid findbyOrganizationIdentifier(String orgIdentity) {
	
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
	
		
		
		List<LearningAid> learningAids = findByNamedQueryAndNamedParams(
				"LearningAid.getUniqueOrganizationIdentifier", queryParams);
		if(learningAids.size() == 0) {
			return null;
		}
		else {
			return learningAids.get(0);
		}
	}


}


