package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="Badge.getUniqueBadge",
			query="SELECT u FROM Badge u WHERE u.badgeName=:badgeName AND u.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="Badge.findByOrganizationIdentifier",
	query="SELECT b FROM Badge b WHERE b.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="Badge.findAll",query="SELECT bd FROM Badge bd")})
public class Badge extends Base{
	
	String organizationIdentifier;
	
	String userName;
	
	String badgeName;
	
	String badgeDescription;

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

	public String getBadgeName() {
		return badgeName;
	}

	

	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}

	public String getBadgeDescription() {
		return badgeDescription;
	}

	public void setBadgeDescription(String badgeDescription) {
		this.badgeDescription = badgeDescription;
	}
	
	@Override
	public String toString() {
		return "Badge [organizationIdentifier=" + organizationIdentifier + ", userName=" + userName + ", badgeName="
				+ badgeName + ", badgeDescription=" + badgeDescription + "]";
	}
	
	

}
