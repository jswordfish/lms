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

import com.techademy.domain.HighlyEffectiveSkillRecommendation;
import com.techademy.services.HighlyEffectiveSkillRecommendationService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestHighlyEffectiveSkillRecommendation {
	
	@Autowired
	HighlyEffectiveSkillRecommendationService highlyEffectiveSkillRecommendationService;
	
	/*@Test
	@Rollback(value=false)
	public void testCreateHighlyEffectiveSkillRecommendation()
	{
		HighlyEffectiveSkillRecommendation highlyEffectiveSkillRecommendation = new HighlyEffectiveSkillRecommendation();
		highlyEffectiveSkillRecommendation.setCreatedBy("ashutosh");
		highlyEffectiveSkillRecommendation.setCreatedDate(new Date());
		highlyEffectiveSkillRecommendation.setHighlyEffectiveSkill("java");
		highlyEffectiveSkillRecommendation.setOrganizationIdentifier("v3");
		highlyEffectiveSkillRecommendationService.saveOrUpdate(highlyEffectiveSkillRecommendation);
	}

	@Test
	@Rollback(value=false)
	public void testDelete()
	{
		highlyEffectiveSkillRecommendationService.delete(5);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void testsearch()
	{
		highlyEffectiveSkillRecommendationService.searchbyOrganizationIdentifierAndHighlyEffectiveSkill("v3", "java");
	}*/
	
	
	@Test
	@Rollback(value=false)
	public void testsearchorganization()
	{
		HighlyEffectiveSkillRecommendation h= highlyEffectiveSkillRecommendationService.findbyOrganizationIdentifier("v3");
		System.out.println(h.toString());
	}
	
	@Test
	@Rollback(value=false)
	public void findAll()
	{
		List highlyEffectiveSkillRecommendation=highlyEffectiveSkillRecommendationService.findAll();;
		System.out.println(highlyEffectiveSkillRecommendation);
	}
	
}
