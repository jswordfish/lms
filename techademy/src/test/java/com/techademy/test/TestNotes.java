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

import com.techademy.domain.Notes;
import com.techademy.services.NotesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestNotes {
	
	@Autowired
	NotesService notesService;
	
/*	@Test
	@Rollback(value=false)
	public void testCreateNotes()
	{
	//test notes
		Notes notes=new Notes();
		notes.setOrganizationIdentifier("test cases");
		notes.setUserName("sameer");
		notes.setNoteTag("xyz");
		notes.setAdditional("BBB");
		notes.setUrl("www.w3.com");
		notes.setCreatedBy("aamir");
		notes.setCreatedDate(new Date());
		notes.setDeprecatedBy("vishal");
		notes.setDeprecatedDate(new Date());
		notes.setDescription("good content");
		notes.setLastModifiedBy("abhi");
		notes.setLastModifiedDate(new Date());
		
		notesService.saveOrUpdate(notes);
	}*/
	
	/*@Test
	@Rollback(value=false)
	public void testDeleteNotes()
	{
		notesService.delete(2);
	}*/
	
	/*@Test
	@Rollback
	public void SearchNotes()
	{
		Notes n=notesService.SearchNotesByOrganizationIdentifierAndUserNameAndNoteTag("test cases", "sameer", "xyz");
		System.out.println(n.toString());
	}*/
	
	@Test
	@Rollback
	public void searchNotesByOrganizationIdentifier()
	{
		Notes n=notesService.findByOrganizationIdentifier("test cases");
		System.out.println(n.toString());
	}
	
	/*@Test
	@Rollback
	public void testFindAll()
	{
		List<Notes> n=notesService.findAll();
		System.out.println(n);
	}*/
	
	
}
