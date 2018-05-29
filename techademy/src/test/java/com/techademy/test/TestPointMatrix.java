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

import com.techademy.domain.PointMatrix;
import com.techademy.services.PointMatrixService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestPointMatrix {

	@Autowired
	PointMatrixService matrixService;
	
	@Test
	@Rollback(value=false)
	public void testCreatePointMatrix()
	{
		PointMatrix matrix=new PointMatrix();
		matrix.setCreatedBy("Jayesh Bhoir");
		matrix.setCreatedDate(new Date());
		matrix.setPointMatrixFor("Java");
		matrix.setOrganizationIdentifier("Squad Infotech");
		matrix.setIdentifier("None");
		matrix.setPoints(10);
		matrix.setVersion(1);
		matrix.getCourseModuleLevels();
		System.out.println(matrix.getCourseModuleLevels());
		matrixService.saveOrUpdate(matrix);
	}
	
	
/*	@Test
	@Rollback(value=false)
	public void deletePointMatrix()
	{
		matrixService.delete(8);
	}*/
	
/*	
	@Test
	@Rollback(value=false)
	public void searchPoniMatrixDetailByAll()
	{
		matrixService.findByOrganizationIdentifierAndPointMatrixForAndIdentifier("Test Company","Java", "Your");
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void searchPoniMatrixDetailByOrganization()
	{
		matrixService.findByOrganizationIdentifier("Test Company");
	}*/
	
	@Test
	@Rollback(value=false)
	public void findAll()
	{
		List<PointMatrix> matrix=matrixService.findAll();
		System.out.println(matrix);
	}
	
}

