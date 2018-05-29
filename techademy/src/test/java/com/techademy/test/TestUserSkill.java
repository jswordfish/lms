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

import com.techademy.domain.Skill;
import com.techademy.domain.UserSkill;
import com.techademy.services.UserSkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUserSkill 
{
	@Autowired
	private UserSkillService userSkillService;
	
	@Test
	@Rollback(value=false)
	public void testCreateUSerSkill()
	{
		UserSkill userSkill=new UserSkill();
		//Skill skill=new Skill();
		userSkill.setCreatedBy("Me");
		userSkill.setCreatedDate(new Date());
    	userSkill.setOrganizationIdentifier("V2Technologies");
		userSkill.getSkills();
		userSkill.setUserName("Jayesh Bhoir");
		userSkill.setVersion(2);
		
		userSkillService.saveOrUpdate(userSkill);
	}
	
	/*@Test
	@Rollback(value=false)
	public void deleteUserSkill()
	{
		userSkillService.delete(10);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void SearchByOrganization()
	{
		userSkillService.getAllUserSkillByOrganization("V2Technologies");
	}*/
	
	@Test
	@Rollback(value=false)
	public void findAll()
	{
		List<UserSkill> userSkill=userSkillService.findAll();
		System.out.println(userSkill);
	}
	
	
}
