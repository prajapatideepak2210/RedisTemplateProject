package com.bridgeLabz.RedisTemplateProject.services;

import java.util.HashSet;
import java.util.Iterator;
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

	@Override
	public long addUser(User user) {
		return redisServiceImpl.addUser(user);
	}

	@Override
	public Set<String> getUserData(String value, String city) throws NetworkException, AnalysisException {
		Set<String> setForReturn = new HashSet<String>();
		value = value.toUpperCase();
		System.out.println("value for uper :"+ value);
		AnalyzedText analyzedText = KeyWordExtractor.getKeyWords(value);
		
		for (Entity entity : analyzedText.getResponse().getEntities()) {
			System.out.println("Matched Entity: " + entity.getEntityId().toLowerCase());
			value  = "*:*" + entity.getEntityId().toLowerCase() + "*";
			System.out.println("value : "+value);
			List<String> list = redisServiceImpl.getUserData(value, city);
			Iterator<?> iterator = list.iterator();
			while (iterator.hasNext()) {
				String name = (String) iterator.next();
				System.out.println("list : "+name);
			}
			
			for(String data : list){
				System.out.println("Data :"+data );
				setForReturn.add(data);
			}
		}
		return setForReturn;
	}
	
}
