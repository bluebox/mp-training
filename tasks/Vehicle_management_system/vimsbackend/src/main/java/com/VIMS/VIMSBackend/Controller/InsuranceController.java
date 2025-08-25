
package com.VIMS.VIMSBackend.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.VIMS.VIMSBackend.Model.InsuranceModel;
import com.VIMS.VIMSBackend.Model.InsuranceStatus;
import com.VIMS.VIMSBackend.Service.InsuranceServiceImplementation;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="/api/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceServiceImplementation service;

    @GetMapping("/getInsurances")
    public ResponseEntity<List<InsuranceModel>> getAllInsurances() throws Exception {
        System.out.println("reached me");
        List<InsuranceModel> insurances = service.getAllInsurances();
        return ResponseEntity.ok().body(insurances);
    }

    @GetMapping("/getInsurances/{type}")
    public ResponseEntity<List<InsuranceModel>> getInsurances(@PathVariable String type) throws Exception {
        System.out.println("reacged me");
        List<InsuranceModel> insurances = service.getAllInsurances();
        List<InsuranceModel> typeInsurances = insurances.stream()
                .filter(ins -> ins.getVehicleType().getType().equals(type))
                .collect(Collectors.toList());
        System.out.println("reacged me" + typeInsurances.size());
        return ResponseEntity.ok().body(typeInsurances);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getInsurance/{id}")
    public ResponseEntity<InsuranceModel> getInsuranceById(@PathVariable int id) {
        InsuranceModel insurance = service.getInsuranceById(id);
        return ResponseEntity.ok().body(insurance);
    }

    @PostMapping("/Createinsurance")
    public ResponseEntity<String> CreateInsurance(@Valid @RequestBody InsuranceModel insurance) throws Exception {
        int value = service.CreateInsurance(insurance);
        if (value > 0) {
            return ResponseEntity.ok().body("Added New Insurance Policy");
        } else {
            throw new Exception("Error while Creating the Insurance Policy");
        }
    }

    @PostMapping("/UpdatePolicy")
    public ResponseEntity<String> updateInsurance(@Valid @RequestBody InsuranceModel insurance) throws Exception {
        int value = service.updateInsurance(insurance);
        if (value > 0) {
            return ResponseEntity.ok().body("updated the given Insurance Policy");
        } else {
            throw new Exception("Error while Updating the Insurance Policy");
        }
    }

    @PostMapping("/DeactivateInsurancePolicy/{id}")
    public ResponseEntity<String> inactiveInsurance(@PathVariable int id) throws Exception {
        InsuranceModel insurance = service.getInsuranceById(id);
        if (insurance.getInsuranceStatus() == InsuranceStatus.ACTIVE) {
            insurance.setInsuranceStatus(InsuranceStatus.INACTIVE);
        } else {
            insurance.setInsuranceStatus(InsuranceStatus.ACTIVE);
        }
        int value = service.inactiveInsurance(insurance);
        if (value > 0) {
            return ResponseEntity.ok().body("updated the given Insurance Policy");
        } else {
            throw new Exception("Error while Updating the Insurance Policy");
        }
    }
}
