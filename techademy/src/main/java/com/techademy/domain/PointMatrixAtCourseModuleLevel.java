package com.techademy.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;


@Entity
@NamedQueries({
	@NamedQuery(name="PointMatrixAtCourseModuleLevel.getUniquePointMatrixCModuleLevel", 
			query="SELECT pmcml FROM PointMatrixAtCourseModuleLevel pmcml WHERE pmcml.organizationIdentifier=:organizationIdentifier AND pmcml.courseIdentifier=:courseIdentifier AND pmcml.moduleName=:moduleName"),
	@NamedQuery(name="PointMatrixAtCourseModuleLevel.getUniquePointMatrixCModuleLevelByOrganizationIdentifier",
			query="SELECT pmcml FROM PointMatrixAtCourseModuleLevel pmcml WHERE pmcml.organizationIdentifier=:organizationIdentifier")
})
public class PointMatrixAtCourseModuleLevel extends Base {
	String organizationIdentifier;
	
	String courseIdentifier;
	
	String moduleName;
	
	Integer pointsToAllocateOnCompletion;

	public String getOrganizationIdentifier() {
		return organizationIdentifier;
	}

	public void setOrganizationIdentifier(String organizationIdentifier) {
		this.organizationIdentifier = organizationIdentifier;
	}

	public String getCourseIdentifier() {
		return courseIdentifier;
	}

	public void setCourseIdentifier(String courseIdentifier) {
		this.courseIdentifier = courseIdentifier;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getPointsToAllocateOnCompletion() {
		return pointsToAllocateOnCompletion;
	}

	public void setPointsToAllocateOnCompletion(Integer pointsToAllocateOnCompletion) {
		this.pointsToAllocateOnCompletion = pointsToAllocateOnCompletion;
	}

	@Override
	public String toString() {
		return "PointMatrixAtCourseModuleLevel [organizationIdentifier=" + organizationIdentifier
				+ ", courseIdentifier=" + courseIdentifier + ", moduleName=" + moduleName
				+ ", pointsToAllocateOnCompletion=" + pointsToAllocateOnCompletion + "]";
	}
	
	
	
	
}
