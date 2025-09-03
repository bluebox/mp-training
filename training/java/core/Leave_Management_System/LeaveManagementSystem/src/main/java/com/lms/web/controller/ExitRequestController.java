package com.lms.web.controller;

import com.lms.web.model.ExitRequest;
import com.lms.web.service.ExitRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/exit-requests")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ExitRequestController {

	@Autowired
    private ExitRequestService service;

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createExitRequest(
	        @RequestBody ExitRequest request,
	        @RequestParam(defaultValue = "false") boolean cancelFutureLeaves
	) {
	    Map<String, Object> response = service.createExitRequest(request, cancelFutureLeaves);
	    return ResponseEntity.ok(response);
	}

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateExitRequest(@RequestBody ExitRequest request) {
       
            Map<String, Object> response = service.updateExitRequest(request);
            return ResponseEntity.ok(response);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Map<String, Object>> cancelExitRequest(@PathVariable int id) {
                    Map<String, Object> response = service.cancelExitRequest(id);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<Map<String, Object>> getMyExitRequest(@PathVariable int employeeId) {
            Map<String, Object> response = service.getExitRequest(employeeId);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllExitRequests() {
            Map<String, Object> response = service.getAllExitRequests();
            return ResponseEntity.ok(response);
    }
    
    @GetMapping("/active/{employeeId}")
    public ResponseEntity<Map<String, Boolean>> hasActiveExitRequest(@PathVariable int employeeId) {
        boolean active = service.hasActiveExitRequest(employeeId);
        return ResponseEntity.ok(Map.of("active", active));
    }

}