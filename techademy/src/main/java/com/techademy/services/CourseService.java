package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Course;

public interface CourseService extends BaseService{

	public void saveOrUpdate(Course	course) throws LMSException;
	
	public Course getUniqueCourse(String orgIdentity, String courseName);
	
	 public void delete(Long id) throws LMSException;
	 
	 public Course findByOrganizationIdentifierAndCourseName(String orgIdentity, String courseName);
	 
	 public Course findByOrganizationIdentifier(String orgIdentity);
	 
	
}

