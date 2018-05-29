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
	@NamedQuery(name="Groups.getUniqueGroup", 
query="SELECT g FROM Groups g WHERE g.organizationIdentifier=:organizationIdentifier AND g.groupName=:groupName"),
@NamedQuery(name="Groups.getUniqueOrganizationIdentifier",
query="SELECT u FROM Groups u WHERE u.organizationIdentifier=:organizationIdentifier"
)

})
public class Groups extends Base{
	
	String groupName;
	
	String organizationIdentifier;
	
	@OneToMany(targetEntity=User.class,fetch=FetchType.EAGER)
	List<User> users = new ArrayList<User>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
