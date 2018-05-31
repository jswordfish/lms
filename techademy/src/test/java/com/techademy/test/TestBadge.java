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

import com.techademy.domain.Badge;
import com.techademy.services.BadgeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:appContext.xml"})
@Transactional
public class TestBadge {
	
	//check test cases
	//badge test
	@Autowired
	BadgeService badgeService;
	
	/*@Test
	@Rollback(value=false)
	public void TestBadgeHolder()
	{	//badge test
		Badge badge=new Badge();
		badge.setOrganizationIdentifier("wipro");
		badge.setUserName("vinod");
		badge.setBadgeName("good");
		badge.setBadgeDescription("Excellent");
		badge.setCreatedBy("Mr.patel");
		badge.setCreatedDate(new Date());
		badge.setDeprecatedBy("Mr.wagh");
		badge.setDeprecatedDate(new Date());
		badge.setTenantId("102");
		badge.setDescription("well");
		badge.setLastModifiedBy("anuja");
		badge.setLastModifiedDate(new Date());
		badge.setVersion(1);
		badgeService.saveOrUpdate(badge);
		
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void TestDeleteBadge()
	{
		badgeService.delete(2);
	}*/
	
	@Test
	@Rollback
	public void TestSearchBadgeByOrganizationIdentifierAndBadgeName()
	{
		Badge b=badgeService.SearchByOrganizationIdentifierAndBadgeName("wipro", "good");
		System.out.println(b.toString());
	}
	
	@Test
	@Rollback
	public void searchByOrganizationIdentifier()
	{
		Badge b=badgeService.findByOrganizationIdentifier("wipro");
		System.out.println(b.toString());
	}
	
	@Test
	@Rollback
	public void TestFindAll()
	{
		List<Badge> b= badgeService.findAll();
		System.out.println(b);
	}
	
}
