package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="LearningAid.getUniqueLearningAid", 
			query="SELECT u FROM LearningAid u WHERE u.tag=:tag AND u.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="LearningAid.getUniqueOrganizationIdentifier", 
	query="SELECT u FROM LearningAid u WHERE  u.organizationIdentifier=:organizationIdentifier"),
	
})
public class LearningAid extends Base{
	String organizationIdentifier;
	
	String tag;
	
	String videoLink;
	
	String blogLink;
	
	String cloudLabLink;
	
	String keyword;//for searching

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getBlogLink() {
		return blogLink;
	}

	public void setBlogLink(String blogLink) {
		this.blogLink = blogLink;
	}

	public String getCloudLabLink() {
		return cloudLabLink;
	}

	public void setCloudLabLink(String cloudLabLink) {
		this.cloudLabLink = cloudLabLink;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
