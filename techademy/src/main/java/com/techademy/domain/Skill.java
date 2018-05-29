package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="Skill.getUniqueSkill", 
			query="SELECT sk FROM Skill sk WHERE sk.organizationIdentifier=:organizationIdentifier AND sk.skillName=:skillName"),
	@NamedQuery(name="Skill.getByOrganizationIdentifier",
			query="SELECT sk FROM Skill sk WHERE sk.organizationIdentifier=:organizationIdentifier")
})
public class Skill extends Base{

	String organizationIdentifier;
	
	String skillName;
	
	Integer skillLevel;

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Integer getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}

	@Override
	public String toString() {
		return "Skill [organizationIdentifier=" + organizationIdentifier + ", skillName=" + skillName + ", skillLevel="
				+ skillLevel + "]";
	}
	
	
}
