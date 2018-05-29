package com.techademy.webservices.data.skill;

import java.util.List;

import com.techademy.webservices.data.course.Links;

public class Skills {
	private Links links;

	  public Links getLinks() { return this.links; }

	  public void setLinks(Links links) { this.links = links; }

	  private List<Skill> data;

	  public List<Skill> getData() { return this.data; }

	  public void setData(List<Skill> data) { this.data = data; }
}
