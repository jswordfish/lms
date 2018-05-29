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
import com.techademy.dao.OrganizationDao;

import com.techademy.domain.Organization;
import com.techademy.services.OrganizationService;

@Service("organizationService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class OrganizationServiceImpl extends BaseServiceImpl<Long, Organization> implements OrganizationService{
	@Autowired
    protected OrganizationDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
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
	public void saveOrUpdate(Organization organization) throws LMSException {
		if( organization.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if( organization.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		Organization organization2 = getUniqueOrganization(organization.getOrganizationIdentifier());
		//step 2
		//for save-create
		if(organization2 == null) {
			//create
			dao.persist(organization);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			organization.setId(organization.getId());
			mapper.map(organization, organization2);
			dao.merge(organization2);
		}
		
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		logger.info("In the method delete id passed "+id);
		Organization organization = dao.findById(id);
		
		dao.remove(organization);
		
	}

	@Override
	public Organization getUniqueOrganization(String orgIdentity) {
		
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Organization> organizations = findByNamedQueryAndNamedParams(
				"Organization.getUniqueOrganization", queryParams);
		if(organizations.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(organizations.size() == 0) {
			return null;
		}
		else {
			return organizations.get(0);
		}
	}

	@Override
	public Organization searchbyOrgIdentity(String orgIdentity) {
	
		Organization o=getUniqueOrganization(orgIdentity);
		if(o!=null)
		{
			System.out.println(o);
			return o;
		}
		
		return null;
	}
	}

