package com.bridgeLabz.RedisTemplateProject.myExeption;

public class InternalServerError extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerError(String s){
		System.out.println("internal server error, please try after some time.");
	}

}
