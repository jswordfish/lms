package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="Notes.getUniqueNotes", 
			query="SELECT u FROM Notes u WHERE u.userName=:userName AND u.organizationIdentifier=:organizationIdentifier AND u.noteTag=:noteTag"),
	
	@NamedQuery(name="Notes.findByOrganizationIdentifier", 
	query="SELECT u FROM Notes u WHERE  u.organizationIdentifier=:organizationIdentifier")
})
public class Notes extends Base {
	String organizationIdentifier;
	
	String userName;
	
	String noteTag;
	
	String note;
	
	String additional;
	
	String url;

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNoteTag() {
		return noteTag;
	}

	public void setNoteTag(String noteTag) {
		this.noteTag = noteTag;
	}

	@Override
	public String toString() {
		return "Notes [organizationIdentifier=" + organizationIdentifier + ", userName=" + userName + ", noteTag="
				+ noteTag + ", note=" + note + ", additional=" + additional + ", url=" + url + "]";
	}
	
	
	
}
