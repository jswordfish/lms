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

import com.techademy.domain.Catalog;
import com.techademy.services.CatalogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCatalog {
	
	@Autowired
	CatalogService catalogService;
	
	
	//nothing
	/*@Test
	@Rollback(value=false)
	public void testCatalog()
	{
		Catalog catalog=new Catalog();
		//test catalog
		catalog.setCatalogName("Catalog");
		catalog.setOrganizationIdentifier("Employee");
		catalog.setGroups("employee");
		catalog.setUsers("Admin");
		catalog.setCreatedDate(new Date());
		catalog.setCreatedBy("vishal");
		catalog.setDeprecatedBy("pankaj");
		catalog.setDeprecatedDate(new Date());
		catalog.setDescription("good content");
		catalog.setLastModifiedBy("rajaram");
		catalog.setLastModifiedDate(new Date());
		catalog.setTenantId("c1");
	
		catalogService.saveOrUpdate(catalog);
		
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void testDeleteCatalog()
	{
		catalogService.delete(5);
	}*/
	
	@Test
	@Rollback
	public void testSearchCatalog()
	{
		Catalog c=catalogService.findByOrganizationIdentifierAndCatalogName("Emp1Catalog", "EmployeeCatalog");
		System.out.println("SearchCatalog "+c.toString());
	}
	
	@Test
	@Rollback
	public void testSearchByOrganizationIdentifier()
	{
		Catalog c=catalogService.findByOrganizationIdentifier("Emp1Catalog");
		System.out.println("SearchByOrganizationIdentifier "+c.toString());
	}
	
	@Test
	@Rollback
	public void testfindAll()
	{
		List<Catalog> c=catalogService.findAll();
		System.out.println("findAll "+c);
	}
	
	
	
	
	

}
