package com.bridgeLabz.RedisTemplateProject.contreoller;

import java.util.Map;

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
import com.bridgeLabz.RedisTemplateProject.services.RedisServiceImpl;

@RestController
public class RedisController {

	@Autowired
	private RedisServiceImpl redisServiceImpl;

	@RequestMapping(value="saveUser", method=RequestMethod.POST)
	public ResponseEntity<Response> saveUser(@RequestBody User user) throws InternalServerError{
		Response response = new Response();
		int checkResult = redisServiceImpl.addUser(user);
		if(checkResult==1){
			response.setMessage("User successfully inserted.");
			return new ResponseEntity<Response >(response,HttpStatus.ACCEPTED);
		}
		response.setMessage("User is not inserted.");
		return new ResponseEntity<Response >(response,HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value="getUser/{name}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, String>> getDataTag(@PathVariable String name){

		Map<String, String> result = redisServiceImpl.getUserData(name);
		if(result!=null){
			return new ResponseEntity<Map<String,String>>(result, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Map<String, String>>(result,HttpStatus.BAD_REQUEST);

	}
	
	@RequestMapping(value="getKey/{key}", method=RequestMethod.GET)
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
}
