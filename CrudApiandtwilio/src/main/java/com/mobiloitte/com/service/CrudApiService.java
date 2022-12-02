package com.mobiloitte.com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mobiloitte.com.dto.CrudApiDto;
import com.mobiloitte.com.model.CrudApiModel;

public interface CrudApiService {

	ResponseEntity<String> save(CrudApiDto crudApiDto);

	List<CrudApiModel> listAll();

	ResponseEntity<String> deleteUser(Long userid);

	//ResponseEntity<String> deleteuser(Long userid);

	ResponseEntity<String> updateuser(Long userid, CrudApiDto crudApiDto);

	void sendSms(String to, String from);
	



	



}
