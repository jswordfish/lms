package com.techademy.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.techademy.base.Base;
@Entity
@NamedQueries({
	@NamedQuery(name="Organization.getUniqueOrganization", 
			query="SELECT o FROM Organization o WHERE  o.organizationIdentifier=:organizationIdentifier"),
})
public class Organization extends Base{
	
String organizationIdentifier;

@OneToMany(targetEntity=Groups.class,fetch=FetchType.EAGER)
List<Groups> groups = new ArrayList<>();

public String getOrganizationIdentifier() {
	return organizationIdentifier;
}

public void setOrganizationIdentifier(String organizationIdentifier) {
	this.organizationIdentifier = organizationIdentifier;
}

public List<Groups> getGroups() {
	return groups;
}

public void setGroups(List<Groups> groups) {
	this.groups = groups;
}

@Override
public String toString() {
	return "Organization [organizationIdentifier=" + organizationIdentifier + ", groups=" + groups + "]";
}



}
