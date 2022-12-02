package com.mobiloitte.com.serviceimpl;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mobiloitte.com.dao.CrudApiDao;
import com.mobiloitte.com.dto.CrudApiDto;
import com.mobiloitte.com.model.CrudApiModel;
import com.mobiloitte.com.service.CrudApiService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

@Service
public class CrudApiServiceimpl implements CrudApiService{
	@Autowired
	private CrudApiDao crudApiDao;
	
	@Override
	public ResponseEntity<String> save(CrudApiDto crudApiDto) {
    int max=0;
	int min=10000;
		Long id=(long)(Math.random()*(max-min+1)+min);
		
		
		
		CrudApiModel crudApiModel=new CrudApiModel();
		crudApiModel.setId(id);
		
		crudApiModel.setFullname(crudApiDto.getFullname());
		crudApiModel.setFirstname(crudApiDto.getFirstname());
		crudApiModel.setLastname(crudApiDto.getLastname());
		crudApiModel.setEmail(crudApiDto.getEmail());
		crudApiModel.setPhonenumber(crudApiDto.getPhonenumber());
		
		crudApiDao.save(crudApiModel);
	return new ResponseEntity<>("Successfully registered",HttpStatus.OK);
	
	}
	
@Override
	public List<CrudApiModel> listAll() {
		return crudApiDao.findAll();
		}


public ResponseEntity<String>deleteuser(Long userid){
	crudApiDao.deleteById(userid);
	return new ResponseEntity<>("user data deleted successfully...",HttpStatus.OK);
}
@Override
public ResponseEntity<String> deleteUser(Long userid) {
	crudApiDao.deleteById(userid);
	
	
	return new ResponseEntity<>("user data deleted successfully...",HttpStatus.OK);
}

@Override
public ResponseEntity<String> updateuser(Long userid, CrudApiDto crudApiDto) {
	
	
	ResponseEntity<String>msg=new ResponseEntity<>("",HttpStatus.OK);
	Optional<CrudApiModel>value=crudApiDao.findById(userid);
    if (value.isPresent()) {
		CrudApiModel crudApiModel = crudApiDao.getByUserid(userid);
		crudApiModel.setFullname(crudApiDto.getFullname());
		crudApiModel.setFirstname(crudApiDto.getFirstname());
		crudApiModel.setLastname(crudApiDto.getLastname());
		crudApiModel.setEmail(crudApiDto.getEmail());
		crudApiDao.save(crudApiModel);
		msg = new ResponseEntity<>("User Data Updated Successfully... ", HttpStatus.OK);
} else {
	msg = new ResponseEntity<>("User Not Exist...", HttpStatus.OK);
	}
	return msg;
}

@Value("${app.twillio.accountSID}")
private String ACCOUNT_SID;

@Value("${app.twillio.authToken}")
private String AUTH_TOKEN;

public void sendSms(String to,String from) {
	int min=10000000;
	int max=99999999;
	long l=(long)(Math.random()*(max-min+1)+min);
	String msg="One Time Password is"+l;

   try {
   		 Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
   	        Message message = Message.creator( new PhoneNumber(to), new PhoneNumber(from),msg) // to:to which no  you want to send sms           
   	            .setMediaUrl(Arrays.asList(URI.create("https://demo.twilio.com/owl.png")))     // from: twillio given phone no
   	            .setStatusCallback(URI.create("http://postb.in/1234abcd"))                      // body : text message
   	            .create();

   	        System.out.println(message);
   	        System.out.println(message.getSid());

   		}catch(Exception e) {
   			
   			e.printStackTrace();
   			
   		}
   		
   	}
}

	
	
	


