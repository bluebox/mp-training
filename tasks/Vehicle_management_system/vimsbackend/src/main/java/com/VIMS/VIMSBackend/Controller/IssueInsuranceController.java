package com.VIMS.VIMSBackend.Controller;

import com.VIMS.VIMSBackend.Model.IssueInsuranceModel;
import com.VIMS.VIMSBackend.Model.IssueInsuranceStatus;
import com.VIMS.VIMSBackend.Service.IssueInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/issueInsurance")
public class IssueInsuranceController {

    @Autowired
    private IssueInsuranceService issueInsuranceService;
 
    @PostMapping("/issue/{insuranceId}/{adminid}/{userid}")
    public ResponseEntity<Boolean> issueInsurance(
            @PathVariable int insuranceId,
            @PathVariable int adminid,
            @PathVariable int userid,
            @RequestBody IssueInsuranceModel issueInsuranceModel) {
        boolean result = issueInsuranceService.issueInsurance(issueInsuranceModel, insuranceId, adminid,userid);
        return ResponseEntity.ok(result);
    }

   
    @GetMapping("/all")
    public ResponseEntity<List<IssueInsuranceModel>> getAllIssuedInsurances() {
        return ResponseEntity.ok(issueInsuranceService.viewAllInsurances());
    }

    
    @GetMapping("/byid/{id}")
    public ResponseEntity<IssueInsuranceModel> getIssueInsuranceById(@PathVariable int id) {
        return ResponseEntity.ok(issueInsuranceService.getIssueInsuranceById(id));
    }

    
    @PostMapping("/upgrade/{insuranceId}/{IssueInsuranceId}")
    public ResponseEntity<Boolean> upgradeIssueInsurance(
            @PathVariable int insuranceId,
            @PathVariable int IssueInsuranceId) {
        boolean result = issueInsuranceService.upgradeIssueInsurance( insuranceId,IssueInsuranceId);
        return ResponseEntity.ok(result);
    }

    
    @PostMapping("/claim/{proof}/{claimAmount}/{IssueInsuranceId}")
    public ResponseEntity<Boolean> updateClaim(
            @PathVariable int IssueInsuranceId,
            @PathVariable String proof,
            @PathVariable double claimAmount) {
        boolean result = issueInsuranceService.updateClaim(proof, claimAmount,IssueInsuranceId);
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<IssueInsuranceModel>> getAllByStatus(@PathVariable IssueInsuranceStatus status) {
        return ResponseEntity.ok(issueInsuranceService.getallIssueInsurancesbystatus(status));
    }
    
    
    @GetMapping("/requests/{adminId}")
    public ResponseEntity<List<IssueInsuranceModel>> getNotAcceptedRequests(@PathVariable int adminId) {
        return ResponseEntity.ok(issueInsuranceService.getNotAcceptedRequestsByAdmin(adminId));
    }


    @PostMapping("/accept/{issueInsuranceId}")
    public ResponseEntity<Boolean> acceptRequest(@PathVariable int issueInsuranceId) {
        IssueInsuranceModel issue = issueInsuranceService.getIssueInsuranceById(issueInsuranceId);
        if (issue == null) {
            return ResponseEntity.notFound().build();
        }
        if (issue.getIssueStatus() != IssueInsuranceStatus.NOTACCEPTED) {
            return ResponseEntity.badRequest().body(false);
        }

        issue.setIssueStatus(IssueInsuranceStatus.ACTIVE);
        issue.setStartDate(Date.valueOf(LocalDate.now()));
        issueInsuranceService.updateIssueInsurance(issue);
        return ResponseEntity.ok(true);
    }

}

