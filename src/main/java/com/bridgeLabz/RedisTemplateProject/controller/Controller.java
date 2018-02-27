package com.bridgeLabz.RedisTemplateProject.controller;

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
	
	/*@RequestMapping(value="getKey/{key}", method=RequestMethod.GET)
	public ResponseEntity<String> saveUser(@PathVariable String key){
		String result = redisServiceImpl.getKey(key);
		if(result!=null){
			return new ResponseEntity<String >(result,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="deleteKey/{key}", method=RequestMethod.DELETE)
	public ResponseEntity<Response> deleteKey(@PathVariable String key){
		Response response = new Response();
		long result = redisServiceImpl.deleteKey(key);
		System.out.println(result);
		if(result==1){
			response.setMessage("Key deleted.");
			return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
		}
		response.setMessage("Key is not deleted.");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/pipeline/{key}", method=RequestMethod.GET)
	public ResponseEntity<String> pipeline(@PathVariable String key){
		System.out.println("controller : "+key);
		String response = redisServiceImpl.pipeline(key);
		System.out.println(response);
		if(response!=null){
			return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
	}*/
}
