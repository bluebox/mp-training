package com.EventManagement.EMS_Backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.EventManagement.EMS_Backend.Exceptions.EventnotRegistered;
import com.EventManagement.EMS_Backend.Model.Attendenceenum;
import com.EventManagement.EMS_Backend.Model.ModelEvent;
import com.EventManagement.EMS_Backend.Service.FacultyService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

@Component

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @PostMapping("/add")
    public String addEvent(@RequestBody ModelEvent event) {
        int result = facultyService.addEvent(event);
        return result > 0 ? "Event added successfully" : "Failed to add event";
    }

    @GetMapping("/events")
    public List<ModelEvent> getAllEvents() {
        return facultyService.getAllEvents();
    }

    @GetMapping("/event/{id}")
    public ModelEvent getEventById(@PathVariable int id) {
        return facultyService.getEventById(id);
    }

    @PutMapping("/update")
    public String updateEvent(@RequestBody ModelEvent event) {
        int result = facultyService.updateEvent(event);
        return result > 0 ? "Event updated successfully" : "Failed to update event";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) {
        int result = facultyService.deleteEvent(id);
        return result > 0 ? "Event deleted successfully" : "Failed to delete event";
    }
    
    @RequestMapping(value="/pendingevents",method=RequestMethod.GET)
    public ResponseEntity<List<ModelEvent>> getpendingevents(){
    	return ResponseEntity.ok(facultyService.getpendingevents());
    }
    
    @RequestMapping(value="/approveevent",method=RequestMethod.POST)
    public String approveEvent(@RequestBody ObjectNode json) {
    	int eventId=json.get("eventid").asInt();
    	String userid=json.get("userid").asText();

        return facultyService.approveEvent(eventId,userid);
    }
    
    @RequestMapping(value="/attendence",method=RequestMethod.POST)
    public int markattendence(@RequestBody ObjectNode json ) throws EventnotRegistered {
    	String userid=json.get("userid").asText();
    	int eventid=json.get("eventid").asInt();
    	String facultyid=json.get("facultyid").asText();
    	Attendenceenum presence=Attendenceenum.getAttendenceenum(json.get("attendence").asText()) ;   
    	String usertype=json.get("usertype").asText();
    	userid=  String.valueOf(usertype.charAt(0))+userid;
    	return facultyService.markattendence(userid, eventid, presence, facultyid);
    }
}
