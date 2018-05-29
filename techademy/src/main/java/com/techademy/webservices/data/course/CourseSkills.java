package com.techademy.webservices.data.course;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseSkills {
	 private List<CourseSkillData> data;
	 
	 @JsonProperty("data")
	  public List<CourseSkillData> getData() { return this.data; }

	  public void setData(List<CourseSkillData> data) { this.data = data; }
}
