package com.bridgeLabz.RedisTemplateProject.services;

import java.util.Set;

import com.bridgeLabz.RedisTemplateProject.model.User;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;

public interface Services {
	
	public long addUser(User user);
	
	public Set<String> getUserData(String value, String city) throws NetworkException, AnalysisException;

}
