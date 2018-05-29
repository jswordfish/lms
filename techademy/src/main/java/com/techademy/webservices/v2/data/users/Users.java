package com.techademy.webservices.v2.data.users;

import java.util.List;

import com.techademy.webservices.data.course.Links;

public class Users {
	private Links links;

	  public Links getLinks() { return this.links; }

	  public void setLinks(Links links) { this.links = links; }

	  private List<User> data;

	  public List<User> getData() { return this.data; }

	  public void setData(List<User> data) { this.data = data; }
}
