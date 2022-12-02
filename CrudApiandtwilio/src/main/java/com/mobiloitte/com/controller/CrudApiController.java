package com.mobiloitte.com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobiloitte.com.dto.CrudApiDto;

import com.mobiloitte.com.model.CrudApiModel;
import com.mobiloitte.com.service.CrudApiService;

@RestController
public class CrudApiController {
	@Autowired
  private  CrudApiService crudApiService;
	@PostMapping ("/crud")
	public ResponseEntity<String> save(@RequestBody CrudApiDto crudApiDto) {
		return crudApiService.save(crudApiDto);
	}
	
	//get all user data
	@GetMapping("/users")
public List<CrudApiModel> listAll()
{
		return crudApiService.listAll();
}
	
	//update user details
	@PutMapping("/update/{userid}")
public ResponseEntity<String>updateuser(@PathVariable("userid")Long userid,@RequestBody CrudApiDto crudApiDto){
	return crudApiService.updateuser(userid,crudApiDto);
		
	}
	
	//delete user details
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String>deleteUser(@PathVariable("userid")Long userid){
		return crudApiService.deleteUser(userid);
}
	
	@Value("${app.twillio.fromPhoneNo}")//@value=to access application properties
	private String from;
	
	@Value("${app.twillio.toPhoneNo}")
	private String to;
	
	@GetMapping("/send")
	public String sendSms() {
		crudApiService.sendSms(to,from);
		return "Message sent Successfully";
		
	}
	
}
