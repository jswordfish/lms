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
import com.techademy.domain.LearningAid;
import com.techademy.services.LearningAidService;
import com.techademy.services.impl.LearningAidServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestLearningAid {
	
@Autowired	
LearningAidService learningAidService;


@Test
@Rollback(value=false)
public void testCreateLearningAid()
{
	LearningAid learningAid= new LearningAid();
	learningAid.setCreatedBy("me");
	learningAid.setCreatedDate(new Date());
	learningAid.setTag("select");
	learningAid.setOrganizationIdentifier("red");
	learningAidService.saveOrUpdate(learningAid);
}
/*
@Test
@Rollback(value=false)
public void testDelete()
{
	learningAidService.delete(3);
}
*/
/*@Test
@Rollback(value=false)
public void testsearch()
{
	LearningAid l=learningAidService.searchbyOrgIdentityAndTag("red", "select");
	System.out.println(l.toString());
}
*/

/*@Test
@Rollback(value=false)
public void testsearchorganization()
{
	LearningAid l= learningAidService.findbyOrganizationIdentifier("red");
	System.out.println(l.toString());
}*/
/*
@Test
@Rollback(value=false)
public void findAll()
{
	List learningAid=learningAidService.findAll();;
	System.out.println(learningAid);
}*/

}
