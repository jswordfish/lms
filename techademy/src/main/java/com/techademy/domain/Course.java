package com.techademy.domain;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;


@Entity
@NamedQueries({
	@NamedQuery(name="Course.getUniqueCourse", 
			query="SELECT u FROM Course u WHERE u.courseName=:courseName AND u.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="Course.findByOrganizationIdentifier", 
	query="SELECT u FROM Course u WHERE u.organizationIdentifier=:organizationIdentifier")
	
})
public class Course extends Base{
	
	String organizationIdentifier;
	
	String courseIdentifier;
	
	String courseName;
	
	Integer courseDurationInhours;
	
	String courseDescription;
	
	String coursePrerequisites;
	
	Date courseCreatedOn;
	
	Boolean isObsolete;
	
	String courseSchedule;
	
	
	Date courseDueRetireDate;
	
	Integer numberOfModules;
	
	Date classRoomCourseStartDateTime;
	
	Date classRoomCourseEndDateTime;
	
	String location;
	
	String trainingRoom;
	
	String address;
	
	String instructorName;
	
	String courseCuricullum;
	
	String urlForRegistration;
	
	String authors;
	
	String keyword;//for search
	
	String skills;//comma separated

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCourseDurationInhours() {
		return courseDurationInhours;
	}

	public void setCourseDurationInhours(Integer courseDurationInhours) {
		this.courseDurationInhours = courseDurationInhours;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCoursePrerequisites() {
		return coursePrerequisites;
	}

	public void setCoursePrerequisites(String coursePrerequisites) {
		this.coursePrerequisites = coursePrerequisites;
	}

	public Date getCourseCreatedOn() {
		return courseCreatedOn;
	}

	public void setCourseCreatedOn(Date courseCreatedOn) {
		this.courseCreatedOn = courseCreatedOn;
	}

	public Boolean getIsObsolete() {
		return isObsolete;
	}

	public void setIsObsolete(Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	public String getCourseSchedule() {
		return courseSchedule;
	}

	public void setCourseSchedule(String courseSchedule) {
		this.courseSchedule = courseSchedule;
	}

	public Date getCourseDueRetireDate() {
		return courseDueRetireDate;
	}

	public void setCourseDueRetireDate(Date courseDueRetireDate) {
		this.courseDueRetireDate = courseDueRetireDate;
	}

	public Integer getNumberOfModules() {
		return numberOfModules;
	}

	public void setNumberOfModules(Integer numberOfModules) {
		this.numberOfModules = numberOfModules;
	}

	public Date getClassRoomCourseStartDateTime() {
		return classRoomCourseStartDateTime;
	}

	public void setClassRoomCourseStartDateTime(Date classRoomCourseStartDateTime) {
		this.classRoomCourseStartDateTime = classRoomCourseStartDateTime;
	}

	public Date getClassRoomCourseEndDateTime() {
		return classRoomCourseEndDateTime;
	}

	public void setClassRoomCourseEndDateTime(Date classRoomCourseEndDateTime) {
		this.classRoomCourseEndDateTime = classRoomCourseEndDateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTrainingRoom() {
		return trainingRoom;
	}

	public void setTrainingRoom(String trainingRoom) {
		this.trainingRoom = trainingRoom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getCourseCuricullum() {
		return courseCuricullum;
	}

	public void setCourseCuricullum(String courseCuricullum) {
		this.courseCuricullum = courseCuricullum;
	}

	public String getUrlForRegistration() {
		return urlForRegistration;
	}

	public void setUrlForRegistration(String urlForRegistration) {
		this.urlForRegistration = urlForRegistration;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Course [organizationIdentifier=" + organizationIdentifier + ", courseIdentifier=" + courseIdentifier
				+ ", courseName=" + courseName + ", courseDurationInhours=" + courseDurationInhours
				+ ", courseDescription=" + courseDescription + ", coursePrerequisites=" + coursePrerequisites
				+ ", courseCreatedOn=" + courseCreatedOn + ", isObsolete=" + isObsolete + ", courseSchedule="
				+ courseSchedule + ", courseDueRetireDate=" + courseDueRetireDate + ", numberOfModules="
				+ numberOfModules + ", classRoomCourseStartDateTime=" + classRoomCourseStartDateTime
				+ ", classRoomCourseEndDateTime=" + classRoomCourseEndDateTime + ", location=" + location
				+ ", trainingRoom=" + trainingRoom + ", address=" + address + ", instructorName=" + instructorName
				+ ", courseCuricullum=" + courseCuricullum + ", urlForRegistration=" + urlForRegistration + ", authors="
				+ authors + ", keyword=" + keyword + ", skills=" + skills + "]";
	}
	
	
	
	
	

}
