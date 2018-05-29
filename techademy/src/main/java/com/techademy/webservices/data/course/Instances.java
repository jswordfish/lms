package com.techademy.webservices.data.course;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Instances {
	private List<InstanceData> data;
	 @JsonProperty("data")
	  public List<InstanceData> getData() { return this.data; }

	  public void setData(List<InstanceData> data) { this.data = data; }
}
