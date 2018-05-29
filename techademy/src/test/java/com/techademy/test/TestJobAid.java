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

import com.techademy.domain.Groups;
import com.techademy.domain.JobAid;
import com.techademy.services.JobAidService;
import com.techademy.services.impl.JobAidServiceServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestJobAid {
	
	@Autowired
	JobAidService jobAidService;
	
	@Test
	@Rollback(value=false)
	public void testCreateJobAid()
	{
		JobAid jobAid=new JobAid();
		jobAid.setCreatedDate(new Date());
		jobAid.setCreatedBy("me");
		jobAid.setJobAidTag("j2ee");
		jobAid.setOrganizationIdentifier("abc");
		jobAidService.saveOrUpdate(jobAid);
	}

	/*@Test
	@Rollback(value=false)
	public void testDelete()
	{
		jobAidService.delete(4);
	}*/
	
	/*
	@Test
	@Rollback(value=false)
	public void testsearch()
	{
		jobAidService.searchbyOrgIdentityAndGobAidTag("abc", "j2ee");
	}
	*/
	

	/*@Test
	@Rollback(value=false)
	public void testsearchorganization()
	{
		JobAid j= jobAidService.findbyOrganizationIdentifier("abc");
		System.out.println(j.toString());
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void findAll()
	{
		List jobAid=jobAidService.findAll();;
		System.out.println(jobAid);
	}*/
	
}
