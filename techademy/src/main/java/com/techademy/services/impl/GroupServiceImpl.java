
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.GroupDao;
import com.techademy.dao.JPADAO;
import com.techademy.domain.Groups;
import com.techademy.domain.User;
import com.techademy.services.GroupService;

@Service("groupService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class GroupServiceImpl extends BaseServiceImpl<Long, Groups> implements GroupService{
	
	@Autowired
    protected GroupDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(GroupServiceImpl.class);
	
	
	
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
	public void saveOrUpdate(Groups group) throws LMSException {
		// TODO Auto-generated method stub
		if(group.getGroupName() == null || group.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(group.getGroupName().trim().length() == 0 || group.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		Groups group2 = getUniqueGroup(group.getOrganizationIdentifier(), group.getGroupName());
		//step 2
		//for save-create
		if(group2 == null) {
			//create
			dao.persist(group);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			group.setId(group2.getId());
			mapper.map(group, group2);
			dao.merge(group2);
		}
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method delete id passed "+id);
		Groups group = dao.findById(id);
		
		dao.remove(group);
	}

	@Override
	public Groups getUniqueGroup(String orgIdentity, String groupName) {
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("groupName", groupName);
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Groups> groups = findByNamedQueryAndNamedParams(
				"Groups.getUniqueGroup", queryParams);
		if(groups.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(groups.size() == 0) {
			return null;
		}
		else {
			return groups.get(0);
		}
	}

	@Override
	public Groups searchbyOrgIdentityAndGroupName(String orgIdentity, String groupName) {
		Groups g=getUniqueGroup(orgIdentity, groupName);
		if(g!=null)
		{
			System.out.println(g);
			return g;
		}
		
		
		return null;
	}

	@Override
	public Groups findbyOrganizationIdentifier(String orgIdentity) {
		
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Groups> groups = findByNamedQueryAndNamedParams("Groups.getUniqueOrganizationIdentifier", queryParams);
		
		
		if(groups.size() == 0) {
			return null;
		}
		else {
			return groups.get(0);
		}
	}

	@Override
	public Groups findAll(Groups group) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	}

	
	
    
	
	


