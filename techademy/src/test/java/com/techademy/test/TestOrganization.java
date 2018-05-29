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

import com.techademy.domain.Organization;
import com.techademy.services.OrganizationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestOrganization {

	@Autowired
	OrganizationService organizationService;

	@Test
	@Rollback(value=false)
	public void testCreateOrgnization()
	{
		Organization organization=new Organization();
		organization.setCreatedBy("Me");
		organization.setCreatedDate(new Date());
		organization.setOrganizationIdentifier("v345");
		organization.setLastModifiedBy("Ashu");
		organization.getGroups();
		organizationService.saveOrUpdate(organization);
		
	}
	
	/*@Test
	@Rollback(value=false)
	public void testDelete()
	{
		organizationService.delete(6);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void testsearch()
	{
		organizationService.searchbyOrgIdentity("v345");
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void findAll()
	{
		List organization =organizationService.findAll();;
		System.out.println(organization);
	}*/

	
	
}
