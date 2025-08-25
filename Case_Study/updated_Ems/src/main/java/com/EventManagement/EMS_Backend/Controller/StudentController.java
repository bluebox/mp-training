package com.EventManagement.EMS_Backend.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.EventManagement.EMS_Backend.Model.ModelEvent;
import com.EventManagement.EMS_Backend.Model.ModelFeedback;
import com.EventManagement.EMS_Backend.Service.StudentService;



@Component
 
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register/{userId}/{eventId}")
    public String registerForEvent(@PathVariable int userId, @PathVariable int eventId) {
        int result = studentService.registerForEvent(userId, eventId);
        return result > 0 ? "Registration successful" : "Registration failed";
    }

    @GetMapping("/status/{userId}/{eventId}")
    public String getRegistrationStatus(@PathVariable int userId, @PathVariable int eventId) {
        return studentService.getRegistrationStatus(userId, eventId);
    }

    @PutMapping("/cancel/{userId}/{eventId}")
    public String cancelRegistration(@PathVariable int userId, @PathVariable int eventId) {
        int result = studentService.cancelRegistration(userId, eventId);
        return result > 0 ? "Registration cancelled" : "Cancellation failed";
    }
    
    @RequestMapping(value="/viewregevents",method=RequestMethod.GET)
    public ResponseEntity<List<ModelEvent>> getRegistrationevents(@RequestParam String userid) {
		return  ResponseEntity.ok(studentService.viewallevents(userid));
    }
    
    
    @RequestMapping(value="/feedback",method=RequestMethod.POST)
	public ResponseEntity<String> feedback(@RequestBody ModelFeedback feedback){
    	
    	int res=studentService.feedback(feedback.getEventId()  ,feedback.getUserId(), feedback.getDescription(), feedback.getRating());
    			return res==1?ResponseEntity.ok("feedback sent"):ResponseEntity.badRequest().body("feedback not sent");
}

}
