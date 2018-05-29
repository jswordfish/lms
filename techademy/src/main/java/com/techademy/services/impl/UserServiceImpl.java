package com.techademy.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.apache.log4j.spi.LoggerFactory;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.JPADAO;
import com.techademy.dao.UserDao;
import com.techademy.domain.User;
import com.techademy.services.UserService;
@Service("userService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class UserServiceImpl extends BaseServiceImpl<Long, User> implements UserService{
	@Autowired
    protected UserDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	
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
	public void saveOrUpdate(User user) throws LMSException {
		// TODO Auser.uto-generated method stub
		logger.info("In the method saveOrUpdate");
		/**
		 * step 0 - perform validations whether user object has all mandatory attributes
		 */
		if(user.getUserName() == null || user.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(user.getUserName().trim().length() == 0 || user.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		
		
		//step 1 FGigure out user is for save or update?
		User user2 = getUniqueUser(user.getOrganizationIdentifier(), user.getUserName());
		//step 2
		//for save-create
		if(user2 == null) {
			//create
			dao.persist(user);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			user.setId(user2.getId());
			mapper.map(user, user2);
			dao.merge(user2);
		}
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method delete id passed "+id);
		User user = dao.findById(id);
		dao.remove(user);
	}

	@Override
	public User getUniqueUser(String orgIdentity, String userName) {
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("userName", userName);
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<User> users = findByNamedQueryAndNamedParams(
				"User.getUniqueUser", queryParams);
		if(users.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(users.size() == 0) {
			return null;
		}
		else {
			return users.get(0);
		}
	}

	@Override
	public User searchbyOrgIdentityAndUserName(String orgIdentity, String userName) {
		User u=getUniqueUser(orgIdentity, userName);
		if(u!=null)
		{
			System.out.println(u);
			return u;
		}
		
		
		return null;
	}

	@Override
	public User findbyOrganizationIdentifier(String orgIdentity) {
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
	
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<User> users = findByNamedQueryAndNamedParams(
				"User.getUniqueOrganizationIdentifier", queryParams);
		
		if(users.size() == 0) {
			return null;
		}
		else {
			return users.get(0);
		}
	}


	
}
