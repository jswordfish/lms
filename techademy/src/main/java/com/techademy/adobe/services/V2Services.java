package com.techademy.adobe.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techademy.security.AccessToken;
import com.techademy.webservices.v2.data.users.User;
import com.techademy.webservices.v2.data.users.Users;

import sun.misc.BASE64Encoder;

public class V2Services {

	public AccessToken getOAuth_learnerRole() throws IOException {
		String url = "https://captivateprime.adobe.com/oauth/token/refresh";

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
	
	public Users getAllUsers() throws Exception{
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
	    return users;
	}
	
	public User getUser(String userId) throws Exception{
		AccessToken token = getOAuth_learnerRole();
		System.out.println(token.getAccess_token());
		String url = "https://captivateprime.adobe.com/primeapi/v2/users/"+userId;
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
	    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    User user = mapper.readValue(data, User.class);
	    return user;
	}
	
	
}
