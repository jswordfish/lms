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
import com.techademy.dao.CourseStatisticsDao;
import com.techademy.dao.JPADAO;
import com.techademy.domain.Badge;
import com.techademy.domain.CourseStatistics;
import com.techademy.services.CourseStatisticsService;

@Service("CourseStatisticsService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class CourseStatisticsServiceImpl extends BaseServiceImpl<Long, CourseStatistics> implements CourseStatisticsService{
	@Autowired
    protected CourseStatisticsDao dao;
	
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
	public void saveOrUpdate(CourseStatistics course) throws LMSException {
		// TODO Auto-generated method stub
		
		logger.info("In the method saveOrUpdate");
		
		if(course.getCourseIdentifier() == null || course.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(course.getCourseIdentifier().trim().length() == 0 || course.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		CourseStatistics course2 = getCourseStatistic(course.getOrganizationIdentifier(), course.getCourseIdentifier());
		//step 2
		//for save-create
		if(course2 == null) {
			//create
			dao.persist(course);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			course.setId(course2.getId());
			mapper.map(course, course2);
			dao.merge(course2);
		}
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		
		CourseStatistics cs=dao.findById(id);
		
		dao.remove(cs);
		
	}

	@Override
	public CourseStatistics getCourseStatistic(String orgIdentity, String courseIdentifier) {
		
		logger.info("In the method getCourseStatistics ");
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("courseIdentifier", courseIdentifier);
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<CourseStatistics> statistics = findByNamedQueryAndNamedParams(
				"CourseStatistics.getCourseStatistics", queryParams);
		if(statistics.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(statistics.size() == 0) {
			return null;
		}
		else {
			return statistics.get(0);
		}
	}

	@Override
	public CourseStatistics findByOrganizationIdentifierAndcourseIdentifier(String orgIdentity,
			String courseIdentifier) 
	{
		logger.info("In the method findByOrganizationIdentifierAndcourseIdentifier ");
		CourseStatistics c=getCourseStatistic(orgIdentity, courseIdentifier);
		if(c!=null)
		{
			return c;
		}
		return null;
	}

	@Override
	public CourseStatistics findByOrganizationIdentifier(String orgIdentity) {
		
		logger.info("In the method findByOrganizationIdentifier ");
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<CourseStatistics> cs = findByNamedQueryAndNamedParams("CourseStatistics.findByOrganizationIdentifier", queryParams);
		if(cs.size() == 0) {
			return null;
		}
		else {
			return cs.get(0);
		}
	}

	
    
	
	

}

