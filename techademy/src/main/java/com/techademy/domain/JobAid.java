package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="JobAid.getUniqueJobAid", 
			query="SELECT j FROM JobAid j WHERE j.jobAidTag=:jobAidTag AND j.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="JobAid.getUniqueOrganizationIdentifier",
	query="SELECT j FROM JobAid j WHERE j.organizationIdentifier=:organizationIdentifier"
	)
})
public class JobAid extends Base{
String organizationIdentifier;
	
	String userName;
	
	String jobAidTag;
	
	
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

	

	public String getJobAidTag() {
		return jobAidTag;
	}

	public void setJobAidTag(String jobAidTag) {
		this.jobAidTag = jobAidTag;
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

	@Override
	public String toString() {
		return "JobAid [organizationIdentifier=" + organizationIdentifier + ", userName=" + userName + ", jobAidTag="
				+ jobAidTag + ", additional=" + additional + ", url=" + url + "]";
	}
	
	
	
	
}
