package com.techademy.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.JPADAO;
import com.techademy.dao.NotesDao;
import com.techademy.domain.Notes;
import com.techademy.domain.User;
import com.techademy.services.NotesService;

@Service("notesService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class NotesServiceImpl extends BaseServiceImpl<Long, Notes> implements NotesService{
	
	@Autowired
    protected NotesDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(NotesServiceImpl.class);
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) dao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(Notes notes) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("In the method saveOrUpdate");
		/**
		 * step 0 - perform validations whether user object has all mandatory attributes
		 */
		if(notes.getUserName() == null || notes.getOrganizationIdentifier() == null|| notes.getNoteTag()==null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(notes.getUserName().trim().length() == 0 || notes.getOrganizationIdentifier().trim().length() == 0 || notes.getNoteTag().length()==0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		Notes notes2 = getUniqueNotes(notes.getOrganizationIdentifier(), notes.getUserName(),notes.getNoteTag());
		//step 2
		//for save-create
		if(notes2 == null) {
			//create
			dao.persist(notes);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			notes.setId(notes2.getId());
			mapper.map(notes, notes2);
			dao.merge(notes2);
		}
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		
		Notes notes=dao.findById(id);
		dao.remove(notes);
		
	}

	@Override
	public Notes getUniqueNotes(String orgIdentity, String userName, String noteTag) {
		
		logger.info("In the method getUniqueNotes ");
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("userName", userName);
		queryParams.put("organizationIdentifier",orgIdentity);
		queryParams.put("noteTag", noteTag);
		
		List<Notes> notes = findByNamedQueryAndNamedParams(
				"Notes.getUniqueNotes", queryParams);
		if(notes.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(notes.size() == 0) {
			return null;
		}
		else {
			return notes.get(0);
		}
	}

	@Override
	public Notes SearchNotesByOrganizationIdentifierAndUserNameAndNoteTag(String orgIdentity, String userName,
			String noteTag) {
		
		logger.info("In the method SearchNotesByOrganizationIdentifierAndUserNameAndNoteTag ");
		Notes n=getUniqueNotes(orgIdentity, userName, noteTag);
		if(n!=null)
		{
			System.out.println(n);
			return n;
		}
		return null;
	}

	@Override
	public Notes findByOrganizationIdentifier(String orgIdentity) {
		// TODO Auto-generated method stub
		
		logger.info("In the method findByOrganizationIdentifier ");
		Map<String,String> queryParams=new HashMap<String,String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Notes> notes = findByNamedQueryAndNamedParams(
				"Notes.findByOrganizationIdentifier", queryParams); 
		
		if(notes.size() == 0) {
			return null;
		}
		else {
			return notes.get(0);
		}
	}

}


