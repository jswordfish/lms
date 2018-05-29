package com.techademy.webservices.data.badge;

import com.techademy.webservices.data.course.Attributes;
import com.techademy.webservices.data.course.Relationships;

public class Badge {
	private String id;

	  public String getId() { return this.id; }

	  public void setId(String id) { this.id = id; }

	  private String type;

	  public String getType() { return this.type; }

	  public void setType(String type) { this.type = type; }

	  private com.techademy.webservices.data.badge.Attributes attributes;

	  public com.techademy.webservices.data.badge.Attributes getAttributes() { return this.attributes; }

	  public void setAttributes(com.techademy.webservices.data.badge.Attributes attributes) { this.attributes = attributes; }

	  private Relationships relationships;

	  public Relationships getRelationships() { return this.relationships; }

	  public void setRelationships(Relationships relationships) { this.relationships = relationships; }
}
