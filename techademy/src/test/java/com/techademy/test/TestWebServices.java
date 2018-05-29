package com.techademy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techademy.adobe.services.V2Services;
import com.techademy.security.AccessToken;
import com.techademy.webservices.data.badge.Badges;
import com.techademy.webservices.data.course.Courses;
import com.techademy.webservices.data.skill.Skills;
import com.techademy.webservices.v2.data.users.User;
import com.techademy.webservices.v2.data.users.Users;

import sun.misc.BASE64Encoder;

public class TestWebServices {
	
	V2Services v2Services = new V2Services();
	
	//@Test
	public AccessToken getOAuth() throws IOException {
		String url = "https://captivateprime.adobe.com/oauth/token/refresh";
//		String append = "redirect_uri=http://thev2technologies.com&state=dummy&scope=learner&response_type=CODE";
//		append = URLEncoder.encode(append);
//		url = url + append;
		URL url2 = new URL(url);
	     HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	     conn.setRequestMethod( "POST" );
	     conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
	    
	     String clientKey = "0f410287-d413-4aed-b867-babda4616ecd";
	     String secret = "ff44af7d-455a-4b4f-871d-b8ed5fd10f03";
	     String refreshToken = "af2b8e916cf1cfc5db07131d6c809ad0";
	     StringBuilder postData = new StringBuilder();
	     postData.append('&');
	     postData.append(URLEncoder.encode("client_id", "UTF-8"));
	     postData.append("=");
	     postData.append(URLEncoder.encode(clientKey, "UTF-8"));
	     
	     postData.append('&');
	     postData.append(URLEncoder.encode("client_secret", "UTF-8"));
	     postData.append("=");
	     postData.append(URLEncoder.encode(secret, "UTF-8"));
	     
	     postData.append('&');
	     postData.append(URLEncoder.encode("refresh_token", "UTF-8"));
	     postData.append("=");
	     postData.append(URLEncoder.encode(refreshToken, "UTF-8"));
	     String post = postData.toString();
	     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	     conn.setDoOutput(true);
	     conn.getOutputStream().write(postDataBytes);
	     String data = getResponse(conn);
	     ObjectMapper mapper = new ObjectMapper();
	     AccessToken token = mapper.readValue(data, AccessToken.class);
	     conn.disconnect();
	     return token;
	}
	
	public AccessToken getOAuth_learnerRole() throws IOException {
		String url = "https://captivateprime.adobe.com/oauth/token/refresh";
//		String append = "redirect_uri=http://thev2technologies.com&state=dummy&scope=learner&response_type=CODE";
//		append = URLEncoder.encode(append);
//		url = url + append;
		URL url2 = new URL(url);
	     HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	     conn.setRequestMethod( "POST" );
	     conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
	   
	     String clientKey = "0f410287-d413-4aed-b867-babda4616ecd";
	     String secret = "ff44af7d-455a-4b4f-871d-b8ed5fd10f03";
	     String refreshToken = "bb2f3bebddcd97ee0782ed5df5b418e8";
	     StringBuilder postData = new StringBuilder();
	     postData.append('&');
	     postData.append(URLEncoder.encode("client_id", "UTF-8"));
	     postData.append("=");
	     postData.append(URLEncoder.encode(clientKey, "UTF-8"));
	     
	     postData.append('&');
	     postData.append(URLEncoder.encode("client_secret", "UTF-8"));
	     postData.append("=");
	     postData.append(URLEncoder.encode(secret, "UTF-8"));
	     
	     postData.append('&');
	     postData.append(URLEncoder.encode("refresh_token", "UTF-8"));
	     postData.append("=");
	     postData.append(URLEncoder.encode(refreshToken, "UTF-8"));
	     String post = postData.toString();
	     byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	     conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	     conn.setDoOutput(true);
	     conn.getOutputStream().write(postDataBytes);
	     String data = getResponse(conn);
	     ObjectMapper mapper = new ObjectMapper();
	     AccessToken token = mapper.readValue(data, AccessToken.class);
	     conn.disconnect();
	     return token;
	}
	
	private String getResponse(HttpsURLConnection con) {
		if(con!=null){
			
			try {
				
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
						
			   String input;
			   String output="";
						
			   while ((input = br.readLine()) != null){
				   output +=input;
			   }
			   br.close();
			   return output;
						
			} catch (IOException e) {
			   e.printStackTrace();
			}
					
		       }
				
		   return null;
	}
	
	@Test
	public void testGetUserv2() throws Exception{
		User user = v2Services.getUser("3445778");
		System.out.println(user.getId());
	}
	
	@Test
	public void testGetUserBadges() throws Exception{
		//
		AccessToken token = getOAuth_learnerRole();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v2/users/3443929/userBadges?page[offset]=0&page[limit]=10&sort=dateAchieved";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    System.out.println(data);
	}
	
	@Test
	public void testGetUserCalendar() throws Exception{
		AccessToken token = getOAuth_learnerRole();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v2/users/3443929/calendar";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    System.out.println(data);
	}
	
	@Test
	public void testGetUsersv2() throws Exception{
		AccessToken token = getOAuth_learnerRole();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v2/users?page[offset]=0&page[limit]=10&sort=id";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    BASE64Encoder encoder = new BASE64Encoder();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    ObjectMapper mapper = new ObjectMapper();
	    Users users = mapper.readValue(data, Users.class);
	    for(User user : users.getData()) {
	    	System.out.println(user.getId());
	    }
	}
	
	@Test
	public void testGetCatalogv2() throws Exception {
		AccessToken token = getOAuth_learnerRole();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v2/catalogs?page[offset]=0&page[limit]=10&sort=name";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    BASE64Encoder encoder = new BASE64Encoder();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    System.out.println(data);
	}
	
	@Test
	public void testGetSkills() throws Exception {
		//https://captivateprime.adobe.com/primeapi/v2/skills?page[offset]=0&page[limit]=10&sort=name
		AccessToken token = getOAuth();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v1/skills?page[offset]=0&page[limit]=10&sort=name";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    BASE64Encoder encoder = new BASE64Encoder();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    ObjectMapper mapper = new ObjectMapper();
	    Skills skills = mapper.readValue(data, Skills.class);
	    System.out.println(skills.getData().size());
	    conn.disconnect();
	}
	
	@Test
	public void testGetSkillsv2() throws Exception {
		//https://captivateprime.adobe.com/primeapi/v2/skills?page[offset]=0&page[limit]=10&sort=name
		AccessToken token = getOAuth_learnerRole();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v2/skills?page[offset]=0&page[limit]=10&sort=name";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    BASE64Encoder encoder = new BASE64Encoder();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    System.out.println(data);
	}
	
	@Test
	public void testGetCourses() throws IOException {
		AccessToken token = getOAuth();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v1/courses?page[offset]=0&page[limit]=10&sort=dateUpdated";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    BASE64Encoder encoder = new BASE64Encoder();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    ObjectMapper mapper = new ObjectMapper();
	    Courses course = mapper.readValue(data, Courses.class);
	    System.out.println(course.getData().size());
	    conn.disconnect();
	}
	
	@Test
	public void testGetBadges() throws IOException {
		AccessToken token = getOAuth();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v1/badges?page[offset]=0&page[limit]=10&sort=name";
		URL url2 = new URL(url);
	    HttpsURLConnection conn = (HttpsURLConnection)url2.openConnection();
	    conn.setRequestMethod( "GET" );
	    conn.setRequestProperty( "Accept", "application/vnd.api+json"); 
	    
	    String accessToken = token.getAccess_token();
	    BASE64Encoder encoder = new BASE64Encoder();
	    conn.setRequestProperty( "Authorization", "oauth "+accessToken); 
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	    String data = getResponse(conn);
	    ObjectMapper mapper = new ObjectMapper();
	    Badges badges = mapper.readValue(data, Badges.class);
	    System.out.println(badges.getData().size());
	    conn.disconnect();
	}

	
	
	private void print_content(HttpsURLConnection con){
		if(con!=null){
				
		try {
			
		   System.out.println("****** Content of the URL ********");			
		   BufferedReader br = 
			new BufferedReader(
				new InputStreamReader(con.getInputStream()));
					
		   String input;
					
		   while ((input = br.readLine()) != null){
		      System.out.println(input);
		   }
		   br.close();
					
		} catch (IOException e) {
		   e.printStackTrace();
		}
				
	       }
			
	   }
	
	

}
