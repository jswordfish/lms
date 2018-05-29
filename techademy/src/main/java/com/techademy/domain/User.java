
package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;
@Entity
@NamedQueries({
	@NamedQuery(name="User.getUniqueUser", 
			query="SELECT u FROM User u WHERE u.userName=:userName AND u.organizationIdentifier=:organizationIdentifier"),
	@NamedQuery(name="User.getUniqueOrganizationIdentifier", 
	        query="SELECT u FROM User u WHERE  u.organizationIdentifier=:organizationIdentifier"),
})


public class User extends Base{

	String userName;
	
	String password;
	
	String groupName;
	
	String organizationIdentifier;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	
	
}

