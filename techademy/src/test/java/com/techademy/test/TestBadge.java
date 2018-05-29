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
	
	
	@Autowired
	BadgeService badgeService;
	
	@Test
	@Rollback(value=false)
	public void TestBadgeHolder()
	{	//badge test
		Badge badge=new Badge();
		badge.setOrganizationIdentifier("infosys");
		badge.setUserName("sagar");
		badge.setBadgeName("excellent");
		badge.setBadgeDescription("Excellent");
		badge.setCreatedBy("Mr.amol");
		badge.setCreatedDate(new Date());
		badge.setDeprecatedBy("Mr.das");
		badge.setDeprecatedDate(new Date());
		badge.setTenantId("101");
		badge.setDescription("good");
		badge.setLastModifiedBy("amit");
		badge.setLastModifiedDate(new Date());
		badge.setVersion(1);
		badgeService.saveOrUpdate(badge);
		
	}
	
	/*@Test
	@Rollback(value=false)
	public void TestDeleteBadge()
	{
		badgeService.delete(4);
	}*/
	
	/*@Test
	@Rollback
	public void TestSearchBadgeByOrganizationIdentifierAndBadgeName()
	{
		Badge b=badgeService.SearchByOrganizationIdentifierAndBadgeName("infosys", "excellent");
		System.out.println(b.toString());
	}*/
	
	/*@Test
	@Rollback
	public void searchByOrganizationIdentifier()
	{
		Badge b=badgeService.findByOrganizationIdentifier("infosys");
		System.out.println(b.toString());
	}*/
	
	@Test
	@Rollback
	public void TestFindAll()
	{
		List<Badge> b= badgeService.findAll();
		System.out.println(b);
	}
	
}
