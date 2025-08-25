package com.EventManagement.EMS_Backend.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EventManagement.EMS_Backend.Model.ModelEvent;
import com.EventManagement.EMS_Backend.Service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController 
@RequestMapping("/Admin")//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {
	
	@Autowired 
	private AdminService adminservice;
	
	@RequestMapping(value="/addevent",method=RequestMethod.POST)
	
	public ResponseEntity<String> addevent(@Valid @RequestBody ObjectNode json) {
        ObjectMapper objectMapper = new ObjectMapper();

		String userid=json.get("userid").asText();
		ModelEvent event=null;
		try {
			
			event = objectMapper.treeToValue(json.get("event"), ModelEvent.class);
		System.out.println("event "+event);
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("event "+event);
		if(adminservice.addEvent(userid,event)!=0){
			return ResponseEntity.ok("event added");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("event not added");
		}
	}
	
	
	@RequestMapping(value="/updateevent",method=RequestMethod.POST)
	
	public ResponseEntity<String> updateevent(@Valid @RequestBody ObjectNode json) {
		  ObjectMapper objectMapper = new ObjectMapper();

			String userid=json.get("userid").asText();
			ModelEvent event=null;
			try {
				event = objectMapper.treeToValue(json.get("event"), ModelEvent.class);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("event "+event);
			if(adminservice.updateEvent(userid,event)!=0){
				return ResponseEntity.ok("event updated");
			}
			else {
				//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
			
			return ResponseEntity.badRequest().body("event not added");
			}
		}
	@RequestMapping(value="/viewevents",method=RequestMethod.GET)
	
	public ResponseEntity<List<ModelEvent>> viewallbookcontroller() {
		
		
		return  ResponseEntity.ok(adminservice.Viewllevents());
		//return ""+bookdao.viewallBooks();
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	
	public ResponseEntity<String> deleteevent(@RequestBody int eventid) {
		int res=0;
		System.out.println("eventid"+eventid);
		res=adminservice.deleteEvent(eventid);
		if(res==1) {
			return  ResponseEntity.ok("deleted successfully");
			
		}
		return ResponseEntity.badRequest().body("not deleted ");
		
	}
	
	
		
	

}
