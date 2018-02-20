package com.bridgeLabz.RedisTemplateProject.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.RedisTemplateProject.model.Response;
import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.myExeption.InternalServerError;
import com.bridgeLabz.RedisTemplateProject.services.ServicesImpl;

@RestController
public class Controller {

/*	@Autowired
	private RedisServiceImpl redisServiceImpl;
*/	
	@Autowired
	private ServicesImpl serviceImpl;

	@RequestMapping(value="saveUser", method=RequestMethod.POST)
	public ResponseEntity<Response> saveUser(@RequestBody User user) throws InternalServerError{
		Response response = new Response();
		long checkResult = serviceImpl.addUser(user);
		if(checkResult>0){
			response.setMessage("User successfully inserted.");
			return new ResponseEntity<Response >(response,HttpStatus.ACCEPTED);
		}
		response.setMessage("User is not inserted.");
		return new ResponseEntity<Response >(response,HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value="getUser/{name}/{city}", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getDataTag(@PathVariable String name, @PathVariable String city){

		List<String> result = serviceImpl.getUserData(name, city);
		if(result!=null){
			return new ResponseEntity<List<String>>(result, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<List<String>>(result,HttpStatus.BAD_REQUEST);

	}
	
	@RequestMapping(value="/getUserDetails/{details}/{city}", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getUserDetail(@PathVariable String details, @PathVariable String city) throws IOException{
		
		System.out.println("Details : "+details);
		System.out.println("city : "+city);
		List<CardKeyword> keywordsList = KeywordsExtractor.getKeywordsList(details);
		Iterator<?> iterator = keywordsList.iterator();
		while (iterator.hasNext()) {
			CardKeyword key = (CardKeyword) iterator.next();
			System.out.println(key.getStem());
		}
		
		/*String keyword = TestLibrary.stem(details);
		System.out.println("Keywords : "+keyword);*/
		return null;
	}
	
	
}
