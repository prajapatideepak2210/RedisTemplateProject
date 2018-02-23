package com.bridgeLabz.RedisTemplateProject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeLabz.RedisTemplateProject.model.Response;
import com.bridgeLabz.RedisTemplateProject.model.User;
import com.bridgeLabz.RedisTemplateProject.myExeption.InternalServerError;
import com.bridgeLabz.RedisTemplateProject.services.ServicesImpl;
import com.textrazor.AnalysisException;
import com.textrazor.NetworkException;

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

	@RequestMapping(value="getUser/{city}", method=RequestMethod.GET)
	public ResponseEntity<Set<String>> getDataTag(@RequestHeader String search, @PathVariable String city) throws NetworkException, AnalysisException{

		Set<String> result = serviceImpl.getUserData(search, city);
		if(result!=null){
			return new ResponseEntity<Set<String>>(result, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Set<String>>(result,HttpStatus.BAD_REQUEST);

	}
	
}
