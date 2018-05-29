package com.techademy.webservices.data.badge;

import java.util.ArrayList;

import com.techademy.webservices.data.course.Course;
import com.techademy.webservices.data.course.Links;

public class Badges {
	private Links links;

	  public Links getLinks() { return this.links; }

	  public void setLinks(Links links) { this.links = links; }

	  private ArrayList<Badge> data;

	  public ArrayList<Badge> getData() { return this.data; }

	  public void setData(ArrayList<Badge> data) { this.data = data; }
}
