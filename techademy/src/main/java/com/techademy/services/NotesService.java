package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Notes;

public interface NotesService extends BaseService{

	public void saveOrUpdate(Notes notes) throws LMSException;

	public Notes getUniqueNotes(String orgIdentity, String userName,String noteTag);

	public void delete(Long id) throws LMSException;

	public Notes SearchNotesByOrganizationIdentifierAndUserNameAndNoteTag(String orgIdentity, String userName,String noteTag);
	
	public Notes findByOrganizationIdentifier(String orgIdentity);

}
