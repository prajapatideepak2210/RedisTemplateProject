package com.bridgeLabz.RedisTemplateProject.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeLabz.RedisTemplateProject.extractServices.KeyWordExtractor;
import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.redisServices.RedisServiceImpl;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;
import com.textrazor.annotations.AnalyzedText;
import com.textrazor.annotations.Entity;

@Service
public class ServicesImpl implements Services {
	
	@Autowired
	private RedisServiceImpl redisServiceImpl;
	private final String KEY = "UserData";

	@Override
	public long addUser(User user) {
		return redisServiceImpl.add(KEY+user.getCity(), "email:"+user.getEmail(), "contact:"+user.getContact(), "name:"+user.getName(), "address:"+user.getAddress());
	}

	@Override
	public Set<String> getUserData(String value, String city) throws NetworkException, AnalysisException {
		Set<String> setForReturn = new HashSet<String>();
		value = value.toUpperCase();
		AnalyzedText analyzedText = KeyWordExtractor.getKeyWords(value);
		
		for (Entity entity : analyzedText.getResponse().getEntities()) {
			System.out.println("Matched Entity: " + entity.getEntityId().toLowerCase());
			value  = "*:*" + entity.getEntityId().toLowerCase() + "*";
			List<String> list = redisServiceImpl.search(KEY+city, value);
			
			for(String data : list){
				System.out.println("Data :"+data );
				setForReturn.add(data);
			}
		}
		return setForReturn;
	}
	
}
