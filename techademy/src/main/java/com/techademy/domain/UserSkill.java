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
	@NamedQuery(name="UserSkill.getUniqueUserSkill", 
			query="SELECT usk FROM UserSkill usk WHERE  usk.organizationIdentifier=:organizationIdentifier AND usk.userName=:userName "),
	@NamedQuery(name="UserSkill.getAllUserSkillByOrganization",
			query="SELECT usk FROM UserSkill usk WHERE  usk.organizationIdentifier=:organizationIdentifier")
})
public class UserSkill  extends Base{
	String organizationIdentifier;
	
	String userName;

	//Skill skill;

	@OneToMany(targetEntity=Skill.class,fetch=FetchType.EAGER)
	List<Skill> skills=new ArrayList<>();
	
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

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "UserSkill [organizationIdentifier=" + organizationIdentifier + ", userName=" + userName + ", skills="
				+ skills + "]";
	}

	
	
	/*public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}*/
	
	
}
