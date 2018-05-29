package com.techademy.domain;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="Catalog.getUniqueCatalog", 
			query="SELECT u FROM Catalog u WHERE u.catalogName=:catalogName AND u.organizationIdentifier=:organizationIdentifier"),

	@NamedQuery(name="Catalog.findByOrganizationIdentifier", 
	query="SELECT u FROM Catalog u WHERE u.organizationIdentifier=:organizationIdentifier")
})
public class Catalog extends Base{
	String organizationIdentifier;
	
	String catalogName;
	
	String groups;//comma separated
	
	String users;//comma separated
	
	@OneToMany(targetEntity=Course.class, fetch=FetchType.EAGER)
	List<Course> courses;

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
}
