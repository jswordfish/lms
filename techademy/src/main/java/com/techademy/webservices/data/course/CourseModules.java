package com.techademy.webservices.data.course;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseModules {
	 private List<CourseModuleData> data;

	 @JsonProperty("data")
	  public List<CourseModuleData> getData() { return this.data; }

	  public void setData(List<CourseModuleData> data) { this.data = data; }
}
