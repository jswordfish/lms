package com.techademy.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.techademy.base.Base;

@Entity
@NamedQueries({
	@NamedQuery(name="CourseStatistics.getCourseStatistics", 
			query="SELECT u FROM CourseStatistics u WHERE u.courseIdentifier=:courseIdentifier AND u.organizationIdentifier=:organizationIdentifier"),
	
	@NamedQuery(name="CourseStatistics.findByOrganizationIdentifier", 
	query="SELECT u FROM CourseStatistics u WHERE u.organizationIdentifier=:organizationIdentifier")
})
public class CourseStatistics extends Base{
	
	String organizationIdentifier;
	
	String courseIdentifier;
	
	String courseName;
	
	Integer numberOfLearnersEnrolled;
	
	Integer averageFeedbackRating;
	
	String badgesAvailable;
	
	String levelOfCourse;

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

	public Integer getNumberOfLearnersEnrolled() {
		return numberOfLearnersEnrolled;
	}

	public void setNumberOfLearnersEnrolled(Integer numberOfLearnersEnrolled) {
		this.numberOfLearnersEnrolled = numberOfLearnersEnrolled;
	}

	public Integer getAverageFeedbackRating() {
		return averageFeedbackRating;
	}

	public void setAverageFeedbackRating(Integer averageFeedbackRating) {
		this.averageFeedbackRating = averageFeedbackRating;
	}

	public String getBadgesAvailable() {
		return badgesAvailable;
	}

	public void setBadgesAvailable(String badgesAvailable) {
		this.badgesAvailable = badgesAvailable;
	}

	public String getLevelOfCourse() {
		return levelOfCourse;
	}

	public void setLevelOfCourse(String levelOfCourse) {
		this.levelOfCourse = levelOfCourse;
	}

	@Override
	public String toString() {
		return "CourseStatistics [organizationIdentifier=" + organizationIdentifier + ", courseIdentifier="
				+ courseIdentifier + ", courseName=" + courseName + ", numberOfLearnersEnrolled="
				+ numberOfLearnersEnrolled + ", averageFeedbackRating=" + averageFeedbackRating + ", badgesAvailable="
				+ badgesAvailable + ", levelOfCourse=" + levelOfCourse + "]";
	}
	
	
	
	
}
