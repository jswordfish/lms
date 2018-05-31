package com.techademy.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.lucene.codecs.compressing.GrowableByteArrayDataOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.techademy.domain.Groups;
import com.techademy.services.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestGroup {
	
	@Autowired
	GroupService groupService;
	
	@Test
	@Rollback(value=false)
	public void testCreateGroup() {
	//groups	
		Groups group=new Groups();
		group.setCreatedDate(new Date());
		group.setGroupName("IT");
		group.setOrganizationIdentifier("B");
		group.setDescription("done All");
		group.setCreatedBy("ashu");
		//group.getUsers();
		groupService.saveOrUpdate(group);
		
	}
	/*
	@Test
	@Rollback(value=false)
	public void testDelete()
	{
		groupService.delete(8);
	}*/
	
	
	/*@Test
	@Rollback(value=false)
	public void testsearch()
	{
		groupService.searchbyOrgIdentityAndGroupName("B", "IT");
	}*/
	
/*	@Test
	@Rollback(value=false)
	public void testsearchorganization()
	{
		Group G= groupService.findbyOrganizationIdentifier("B");
		System.out.println(G.toString());
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void findAll()
	{
		List group=groupService.findAll();;
		System.out.println(group);
	}*/
}
