package com.EventManagement.EMS_Backend.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EventManagement.EMS_Backend.Model.ModelUser;
import com.EventManagement.EMS_Backend.Model.RegistrationEnum;
import com.EventManagement.EMS_Backend.Model.UserEnum;
import com.EventManagement.EMS_Backend.Service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/Registration")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationservice;
	
	@RequestMapping(value="/userregistration",method=RequestMethod.POST)
	public ResponseEntity<String> userregistration(@Valid @RequestBody ModelUser user) {
	
		
		System.out.println(" this is user "+user);
		if(registrationservice.userRegister(user)!=0) {
			return ResponseEntity.ok("user registered");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("user not registered");
		}
		
	}
	@RequestMapping(value="/viewregstudents",method=RequestMethod.GET)
	public ResponseEntity<List<ModelUser>> viewallregstudentscontroller(@RequestParam int eventid) {
		return  ResponseEntity.ok(registrationservice.ViewallregStudents(eventid));
		//return ""+bookdao.viewallBooks();
	}
	
	@RequestMapping(value="/cancelregister",method=RequestMethod.POST)
	public ResponseEntity<String> cancelevent(@RequestBody ObjectNode json  ) {
		int res=0;
		String userid=json.get("userid").asText();
		int eventid=json.get("eventid").asInt();
		res=registrationservice.cancelRegister(userid,eventid);
		if(res==1) {
			return  ResponseEntity.ok("deleted successfully");
			
		}
		else
		return ResponseEntity.badRequest().body("not deleted ");
		
	}
	
	
	
	@RequestMapping(value="/eventregister",method=RequestMethod.POST)
	public ResponseEntity<String> Registerevent(@Valid @RequestBody ObjectNode json) {
		
		String userid=json.get("userid").asText();
		int eventid=json.get("eventid").asInt();
		UserEnum usertype=UserEnum.getUsertype(json.get("usertype").asText());
		//userid=String.valueOf(usertype.getType().charAt(0))+userid;
		System.out.println(userid+eventid+usertype);
		if(registrationservice.eventRegister(usertype,userid,eventid)!=0){ 
			return ResponseEntity.ok("event added");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("event not added");
		}
		
		
	}
	
	@RequestMapping(value="/regusersforattd/{eventid}",method=RequestMethod.GET)
	public ResponseEntity<List<ModelUser>> Registerevent(@PathVariable int eventid) {	
		return ResponseEntity.ok(registrationservice.getReguserstomarkattd(eventid));
	}
	
	
	

}
