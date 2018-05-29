package com.techademy.webservices.v2.data.users;

import java.util.ArrayList;

public class Attributes {
	private String avatarUrl;

	  public String getAvatarUrl() { return this.avatarUrl; }

	  public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

	  private String email;

	  public String getEmail() { return this.email; }

	  public void setEmail(String email) { this.email = email; }

	  private String name;

	  public String getName() { return this.name; }

	  public void setName(String name) { this.name = name; }

	  private int pointsEarned;

	  public int getPointsEarned() { return this.pointsEarned; }

	  public void setPointsEarned(int pointsEarned) { this.pointsEarned = pointsEarned; }

	  private String profile;

	  public String getProfile() { return this.profile; }

	  public void setProfile(String profile) { this.profile = profile; }

	  private ArrayList<String> roles;

	  public ArrayList<String> getRoles() { return this.roles; }

	  public void setRoles(ArrayList<String> roles) { this.roles = roles; }

	  private String state;

	  public String getState() { return this.state; }

	  public void setState(String state) { this.state = state; }

	  private String userType;

	  public String getUserType() { return this.userType; }

	  public void setUserType(String userType) { this.userType = userType; }
}
