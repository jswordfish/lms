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

import com.techademy.domain.Course;
import com.techademy.services.CourseService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCourse {
	
	@Autowired
	CourseService courseService;
	
	/*@Test
	@Rollback(value=false)
	public void testCreateCourse() 
	{
		//test course
		Course course=new Course();
		course.setOrganizationIdentifier("Technical");
		course.setCourseIdentifier("ct42");
		course.setCourseName("java");
		course.setCourseDurationInhours(250);
		course.setCourseDescription("Core java");
		course.setCoursePrerequisites("each level shhould completed");
		course.setCourseCreatedOn(new Date());
		course.setCreatedBy("vishal");
		course.setCreatedDate(new Date());
		course.setDeprecatedBy("mohan");
		course.setDeprecatedDate(new Date());
		course.setDescription("good content");
		course.setLastModifiedDate(new Date());
		courseService.saveOrUpdate(course);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void testDeleteCourse()
	{
		courseService.delete(6);
	}*/
	
	@Test
	@Rollback
	public void findCourse()
	{
		Course c=courseService.findByOrganizationIdentifierAndCourseName("Technical", "java");
		System.out.println(c.toString());
	}
	
	@Test
	@Rollback
	public void TestSearchByOrganizationIdentifier()
	{
		Course c=courseService.findByOrganizationIdentifier("Technical");
		System.out.println(c.toString());
	}
	
	@Test
	@Rollback
	public void testfindAll()
	{
		List<Course> c=courseService.findAll();
		System.out.println(c);
	}
}
