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
import com.techademy.dao.JobAidDao;
import com.techademy.domain.HighlyEffectiveSkillRecommendation;
import com.techademy.domain.JobAid;
import com.techademy.domain.User;
import com.techademy.services.JobAidService;
import com.techademy.services.UserService;

@Service("jobAidService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class JobAidServiceServiceImpl extends BaseServiceImpl<Long, JobAid> implements JobAidService{
	
	@Autowired
    protected JobAidDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(JobAidServiceServiceImpl.class);
	
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
	public void saveOrUpdate(JobAid jobAid) throws LMSException {
		if(jobAid.getJobAidTag() == null || jobAid.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(jobAid.getJobAidTag().trim().length() == 0 || jobAid.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		JobAid jobAid2 = getUniqueJobAid(jobAid.getOrganizationIdentifier(), jobAid.getJobAidTag());
		//step 2
		//for save-create
		if(jobAid2 == null) {
			//create
			dao.persist(jobAid);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			jobAid.setId(jobAid2.getId());
			mapper.map(jobAid, jobAid2);
			dao.merge(jobAid2);
		}
	}

	@Override
	public void delete(Long id) throws LMSException {
		logger.info("In the method delete id passed "+id);
		JobAid jobAid = dao.findById(id);
		dao.remove(jobAid);
		
	}

	@Override
	public JobAid getUniqueJobAid(String orgIdentity, String jobAidTag) {
		Map<String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put("organizationIdentifier", orgIdentity);
		queryParams.put("jobAidTag", jobAidTag);
		
		List<JobAid> jobAids = findByNamedQueryAndNamedParams(
				"JobAid.getUniqueJobAid", queryParams);
		if(jobAids.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(jobAids.size() == 0) {
			return null;
		}
		else {
			return jobAids.get(0);
		}
	}

	@Override
	public JobAid searchbyOrgIdentityAndGobAidTag(String orgIdentity, String jobAidTag) {
		
		JobAid j=getUniqueJobAid(orgIdentity, jobAidTag);
		if(j!=null)
		{
			System.out.println(j);
			return j;
		}
		
		return null;
	}

	@Override
	public JobAid findbyOrganizationIdentifier(String orgIdentity) {
		
		
      Map<String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put("organizationIdentifier", orgIdentity);
		
		
		List<JobAid> jobAids = findByNamedQueryAndNamedParams(
				"JobAid.getUniqueOrganizationIdentifier", queryParams);


		if(jobAids.size() == 0) {
			return null;
		}
		else {
			return jobAids.get(0);
		}
		
		
	}

	
	

	
	
    
	
	

}

