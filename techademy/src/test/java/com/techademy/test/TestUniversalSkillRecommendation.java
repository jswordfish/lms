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

import com.techademy.domain.UniversalSkillRecommendation;
import com.techademy.services.UniversalSkillRecommendationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUniversalSkillRecommendation 
{
	
	@Autowired
	private UniversalSkillRecommendationService uSkillRecommendService;
	
	@Test
	@Rollback(value=false)
	public void CreateTestUniversalSkillRecommend()
	{
		
		UniversalSkillRecommendation recommendation2=new UniversalSkillRecommendation();
		recommendation2.setCreatedBy("Jayesh Bhoir");
		recommendation2.setCreatedDate(new Date());
		recommendation2.setUniversalSkill("Java");
		recommendation2.setOrganizationIdentifier("Squad Infotech");
		recommendation2.setLastModifiedDate(new Date());
		recommendation2.setVersion(4);
		
		uSkillRecommendService.saveOrUpdate(recommendation2);
	}
	
	/*@Test
	@Rollback(value=false)
	public void deleteUniversalSkill()
	{
		uSkillRecommendService.delete(2);
	}
	
	@Test
	@Rollback
	public void searchUniversalskillRecommend()
	{
		UniversalSkillRecommendation u=uSkillRecommendService.findByOrganizationIdentifierAndUniversalSkill("V2 Technolgy", "SKill Java");
		System.out.println(u);
	}
	
	@Test
	@Rollback(value=false)
	public void findAll()
	{
		List<UniversalSkillRecommendation> recommendations=uSkillRecommendService.findAll();
		System.out.println(recommendations);
 	}
	*/
	
}
