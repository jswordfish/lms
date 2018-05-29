package com.techademy.webservices.data.course;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Courses {
	 private Links links;

	  public Links getLinks() { return this.links; }

	  public void setLinks(Links links) { this.links = links; }
	 
	  private List<Course> data;

	  @JsonProperty("data")
	  public List<Course> getData() { return this.data; }

	  public void setData(List<Course> data) { this.data = data; }
}
