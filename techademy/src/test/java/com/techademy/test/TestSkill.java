package com.techademy.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.techademy.domain.Skill;
import com.techademy.services.SkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestSkill {

	@Autowired
	private SkillService skillService;
	
	@Test
	@Rollback(value=false)
	public void testCreateSkill()
	{
		Skill skill=new Skill();
		skill.setCreatedBy("Me");
		skill.setCreatedDate(new Date());
		skill.setOrganizationIdentifier("Squad Infotech");
		skill.setSkillName("Angular JS");
		
		skillService.saveOrUpdate(skill);
	}
	
	/*@Test
	@Rollback(value=false)
	public void testDeleteSkill()
	{
		skillService.delete(10);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void findAll()
	{
		List<Skill> skills=skillService.findAll();
		System.out.println(skills);
	}*/
	
	@Test
	@Rollback(value=false)
	public void findAllByOrganization()
	{
		skillService.getAllByOrganisation("Test Company");
	}

}
