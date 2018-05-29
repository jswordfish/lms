package com.techademy.webservices.data.course;

import java.util.Date;

public class Attributes {
	private String courseType;

	  public String getCourseType() { return this.courseType; }

	  public void setCourseType(String courseType) { this.courseType = courseType; }

	  private Date dateCreated;

	  public Date getDateCreated() { return this.dateCreated; }

	  public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

	  private Date dateUpdated;

	  public Date getDateUpdated() { return this.dateUpdated; }

	  public void setDateUpdated(Date dateUpdated) { this.dateUpdated = dateUpdated; }

	  private String description;

	  public String getDescription() { return this.description; }

	  public void setDescription(String description) { this.description = description; }

	  private int effectivenessIndex;

	  public int getEffectivenessIndex() { return this.effectivenessIndex; }

	  public void setEffectivenessIndex(int effectivenessIndex) { this.effectivenessIndex = effectivenessIndex; }

	  private String enrollmentType;

	  public String getEnrollmentType() { return this.enrollmentType; }

	  public void setEnrollmentType(String enrollmentType) { this.enrollmentType = enrollmentType; }

	  private boolean moduleOrderEnforced;

	  public boolean getModuleOrderEnforced() { return this.moduleOrderEnforced; }

	  public void setModuleOrderEnforced(boolean moduleOrderEnforced) { this.moduleOrderEnforced = moduleOrderEnforced; }

	  private String name;

	  public String getName() { return this.name; }

	  public void setName(String name) { this.name = name; }

	  private String overview;

	  public String getOverview() { return this.overview; }

	  public void setOverview(String overview) { this.overview = overview; }

	  private String state;

	  public String getState() { return this.state; }

	  public void setState(String state) { this.state = state; }
}
