package com.techademy.webservices.data.course;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
	private String id;

	  public String getId() { return this.id; }

	  public void setId(String id) { this.id = id; }

	  private String type;

	  public String getType() { return this.type; }

	  public void setType(String type) { this.type = type; }

	  
	  private Attributes attributes;
	  
	  @JsonProperty("attributes")
	  public Attributes getAttributes() { return this.attributes; }

	  public void setAttributes(Attributes attributes) { this.attributes = attributes; }

	  private Relationships relationships;

	  @JsonProperty("relationships")
	  public Relationships getRelationships() { return this.relationships; }

	  public void setRelationships(Relationships relationships) { this.relationships = relationships; }
}
