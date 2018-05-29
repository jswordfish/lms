package com.techademy.webservices.data.course;

public class Relationships {
	 private CourseModules courseModules;

	  public CourseModules getCourseModules() { return this.courseModules; }

	  public void setCourseModules(CourseModules courseModules) { this.courseModules = courseModules; }

	  private CourseSkills courseSkills;

	  public CourseSkills getCourseSkills() { return this.courseSkills; }

	  public void setCourseSkills(CourseSkills courseSkills) { this.courseSkills = courseSkills; }

	  private Instances instances;

	  public Instances getInstances() { return this.instances; }

	  public void setInstances(Instances instances) { this.instances = instances; }
}
