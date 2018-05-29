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
	@NamedQuery(name="PointMatrix.getUniquePointMatrix", 
			query="SELECT pm FROM PointMatrix pm WHERE pm.pointMatrixFor=:pointMatrixFor AND pm.organizationIdentifier=:organizationIdentifier AND pm.identifier=:identifier"),	
	@NamedQuery(name="PointMatrix.getByOrganizationIdentifier",
			query="SELECT pm FROM PointMatrix pm WHERE pm.organizationIdentifier=:organizationIdentifier"),
	@NamedQuery(name="PointMatrixAtCourseModuleLevel.findAll",
	query="SELECT pc FROM PointMatrixAtCourseModuleLevel pc"),
})

public class PointMatrix extends Base{
			
	String organizationIdentifier;
	
	String pointMatrixFor ;
	
	String identifier;//can be course or certification identifier
	
	//String 
	
	Integer points;
	
	@OneToMany(targetEntity=PointMatrixAtCourseModuleLevel.class ,fetch=FetchType.EAGER)
	List<PointMatrixAtCourseModuleLevel> courseModuleLevels = new ArrayList<>();

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getPointMatrixFor() {
		return pointMatrixFor;
	}

	public void setPointMatrixFor(String pointMatrixFor) {
		this.pointMatrixFor = pointMatrixFor;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public List<PointMatrixAtCourseModuleLevel> getCourseModuleLevels() {
		return courseModuleLevels;
	}

	public void setCourseModuleLevels(List<PointMatrixAtCourseModuleLevel> courseModuleLevels) {
		this.courseModuleLevels = courseModuleLevels;
	}

	@Override
	public String toString() {
		return "PointMatrix [organizationIdentifier=" + organizationIdentifier + ", pointMatrixFor=" + pointMatrixFor
				+ ", identifier=" + identifier + ", points=" + points + ", courseModuleLevels=" + courseModuleLevels
				+ "]";
	}
	
	
	
}
