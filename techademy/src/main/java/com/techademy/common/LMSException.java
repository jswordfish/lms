package com.techademy.common;

public class LMSException extends RuntimeException {
	
	public LMSException(){
		super();
	}
	
	public LMSException(String message){
		super(message);
	}
	
	public LMSException(String message, Throwable t){
		super(message, t);
	}


}

