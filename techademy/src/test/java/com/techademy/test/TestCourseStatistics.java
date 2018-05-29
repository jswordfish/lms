package com.techademy.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.techademy.domain.CourseStatistics;
import com.techademy.services.CourseStatisticsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCourseStatistics {
	
	@Autowired
	CourseStatisticsService courseStatisticsService;
	
	@Test
	@Rollback(value=false)
	public void testCreateCourseStatistics()
	{
		//test coursestatistics
		CourseStatistics cs=new CourseStatistics();
		cs.setOrganizationIdentifier("Test Statistics");
		cs.setCourseIdentifier("ct40");
		cs.setCourseName("java");
		cs.setNumberOfLearnersEnrolled(12);
		cs.setAverageFeedbackRating(7);
		cs.setBadgesAvailable("primary");
		cs.setLevelOfCourse("easy");
		cs.setCreatedBy("abhijit");
		cs.setCreatedDate(new Date());
		cs.setDeprecatedBy("abc");
		cs.setDeprecatedDate(new Date());
		
		courseStatisticsService.saveOrUpdate(cs);
	}
	
	/*@Test
	@Rollback(value=false)
	public void testDeleteCourseStatistics()
	{
		courseStatisticsService.delete(3);
	}*/
	
	/*@Test
	@Rollback
	public void searchCourseStatistics()
	{
		CourseStatistics c=courseStatisticsService.findByOrganizationIdentifierAndcourseIdentifier("Test Statistics", "ct40");
		System.out.println(c.toString());
	}*/
	
	/*@Test
	@Rollback
	public void searchByOrganizationIdentifier()
	{
		CourseStatistics c=courseStatisticsService.findByOrganizationIdentifier("Test Statistics");
		System.out.println(c.toString());
	}*/
	
	/*@Test
	@Rollback
	public void testfindAll()
	{
		List<CourseStatistics> c=courseStatisticsService.findAll();
		System.out.println(c);
	}*/

}
