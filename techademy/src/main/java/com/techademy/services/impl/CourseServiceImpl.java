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
import com.techademy.dao.CourseDao;
import com.techademy.dao.JPADAO;
import com.techademy.domain.Badge;
import com.techademy.domain.Course;
import com.techademy.services.CourseService;

@Service("CourseService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class CourseServiceImpl extends BaseServiceImpl<Long, Course> implements CourseService{
	
	@Autowired
	CourseDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(CourseServiceImpl.class);
	
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
	public void saveOrUpdate(Course course) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method saveOrUpdate");
		/**
		 * step 0 - perform validations whether user object has all mandatory attributes
		 */
		if(course.getCourseName() == null || course.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(course.getCourseName().trim().length() == 0 || course.getOrganizationIdentifier().trim().length() == 0) 
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		Course course2 = getUniqueCourse(course.getOrganizationIdentifier(), course.getCourseName());
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
		
		Course course=dao.findById(id);
		dao.remove(course);
		
	}

	@Override
	public Course getUniqueCourse(String orgIdentity, String courseName) {
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("courseName", courseName);
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Course> courses = findByNamedQueryAndNamedParams(
				"Course.getUniqueCourse", queryParams);
		if(courses.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(courses.size() == 0) {
			return null;
		}
		else {
			return courses.get(0);
		}
	}

	@Override
	public Course findByOrganizationIdentifierAndCourseName(String orgIdentity, String courseName) {
		logger.info("In the method findByOrganizationIdentifierAndCourseName");
		Course c=getUniqueCourse(orgIdentity, courseName);
		if(c!=null)
		{
			return c;
		}
		return null;
	}

	@Override
	public Course findByOrganizationIdentifier(String orgIdentity) {
		logger.info("In the method findByOrganizationIdentifier");
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Course> course = findByNamedQueryAndNamedParams("Course.findByOrganizationIdentifier", queryParams);
		if(course.size() == 0) {
			return null;
		}
		else {
			return course.get(0);
		}
	}

}

