package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.CourseStatistics;
import com.techademy.domain.User;

public interface CourseStatisticsService extends BaseService{

	public void saveOrUpdate(CourseStatistics	course) throws LMSException;

	public CourseStatistics getCourseStatistic(String orgIdentity, String courseIdentifier);

	public void delete(Long id) throws LMSException;

	public CourseStatistics findByOrganizationIdentifierAndcourseIdentifier(String orgIdentity, String courseIdentifier);

	public CourseStatistics findByOrganizationIdentifier(String orgIdentity);

}
