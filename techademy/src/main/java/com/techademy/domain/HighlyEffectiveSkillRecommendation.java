package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="HighlyEffectiveSkillRecommendation.getUniqueHighlyEffectiveSkillRecommendation", 
			query="SELECT h FROM HighlyEffectiveSkillRecommendation h WHERE h.highlyEffectiveSkill=:highlyEffectiveSkill AND h.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="HighlyEffectiveSkillRecommendation.getUniqueOrganizationIdentifier",
	query="SELECT h FROM HighlyEffectiveSkillRecommendation h WHERE h.organizationIdentifier=:organizationIdentifier"
	)
})
public class HighlyEffectiveSkillRecommendation extends Base{
	String organizationIdentifier;
	
	String highlyEffectiveSkill;
	
	String resourceIdentifier;
	
	String resourceType;

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getHighlyEffectiveSkill() {
		return highlyEffectiveSkill;
	}

	public void setHighlyEffectiveSkill(String highlyEffectiveSkill) {
		this.highlyEffectiveSkill = highlyEffectiveSkill;
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
	
	
}
