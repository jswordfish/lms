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
import com.techademy.domain.User;
import com.techademy.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestUser {
	
	@Autowired
	UserService userService;
	
	@Test
	@Rollback(value=false)
	public void testCreateUser() {
		User user = new User();
		user.setCreatedBy("Test");
		user.setCreatedDate(new Date());
		user.setOrganizationIdentifier("Company 1");
		user.setGroupName("IT");
		user.setUserName("User 1");
		user.setPassword("password");
		userService.saveOrUpdate(user);
		
	}
	
	/*@Test
	@Rollback(value=false)
	public void testDelete()
	{
		userService.delete(9);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void testsearch()
	{
		userService.searchbyOrgIdentityAndUserName("Company 1", "User 1");
	}
	*/

	@Test
	@Rollback(value=false)
	public void testsearchorganization()
	{
		User u= userService.findbyOrganizationIdentifier("Company 1");
		System.out.println(u.toString());
	}
	
	
	/*@Test
	@Rollback(value=false)
	public void findAll()
	{
		List user =userService.findAll();;
		System.out.println(user);
	}*/
}


