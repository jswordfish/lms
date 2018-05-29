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

import com.techademy.domain.PointMatrixAtCourseModuleLevel;
import com.techademy.services.PointMatrixAtCourseModuleLevelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestPCML 
{
	@Autowired
	private PointMatrixAtCourseModuleLevelService atCourseModuleLevelService;
	
	@Test
	@Rollback(value=false)
	public void testCreatePointMatrixCMLevel()
	{
		PointMatrixAtCourseModuleLevel courseModuleLevel=new PointMatrixAtCourseModuleLevel();
		courseModuleLevel.setCreatedBy("Pratik Gholap");
		courseModuleLevel.setCreatedDate(new Date());
		courseModuleLevel.setOrganizationIdentifier("Squad Infotech");
		courseModuleLevel.setCourseIdentifier("Android COURSE");
		courseModuleLevel.setModuleName("Basic Learning");
		atCourseModuleLevelService.saveOrUpdate(courseModuleLevel);
	}
	
	/*@Test
	@Rollback(value=false)
	public void testDeletePointMatrix()
	{
		atCourseModuleLevelService.delete(1);
	}
	
	
	@Test
	@Rollback(value=false)
	public void findAll()
	{
		List<PointMatrixAtCourseModuleLevel> atCourseModuleLevels  =atCourseModuleLevelService.findAll();
		System.out.println(atCourseModuleLevels);
	}
	*/
	/*@Test
	@Rollback(value=false)
	public void findAllByOrganization()
	{
		atCourseModuleLevelService.getUniquePointMatrixCModuleLevelByOrganizationIdentifier("TEst Company");
	}*/
}
