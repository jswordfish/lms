package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="UniversalSkillRecommendation.getUniqueUniversalSkillRecommendation", 
			query="SELECT usr FROM UniversalSkillRecommendation usr WHERE usr.universalSkill=:universalSkill AND usr.organizationIdentifier=:organizationIdentifier"),
})
public class UniversalSkillRecommendation extends Base{
	String organizationIdentifier;
	
	String universalSkill;
	
	String resourceIdentifier;
	
	String resourceType;

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getUniversalSkill() {
		return universalSkill;
	}

	public void setUniversalSkill(String universalSkill) {
		this.universalSkill = universalSkill;
	}

	public String getResourceIdentifier() {
		return resourceIdentifier;
	}

	public void setResourceIdentifier(String resourceIdentifier) {
		this.resourceIdentifier = resourceIdentifier;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Override
	public String toString() {
		return "UniversalSkillRecommendation [organizationIdentifier=" + organizationIdentifier + ", universalSkill="
				+ universalSkill + ", resourceIdentifier=" + resourceIdentifier + ", resourceType=" + resourceType
				+ "]";
	}
	
	

}
