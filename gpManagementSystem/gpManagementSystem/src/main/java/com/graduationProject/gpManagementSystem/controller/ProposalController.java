package com.graduationProject.gpManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.dto.ProposalRequest;
import com.graduationProject.gpManagementSystem.model.Proposal;
import com.graduationProject.gpManagementSystem.service.ProposalService;
import com.graduationProject.gpManagementSystem.utils.JsonUtil;

import org.springframework.core.io.Resource;
import java.io.IOException;



@RestController
@RequestMapping("/api/proposals")
public class ProposalController {
    
    @Autowired
    private ProposalService proposalService;



    //  Submit Proposal with pdf file
    @PostMapping(value = "/submit" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Proposal>> submitProposal(@RequestPart("proposal") String proposalJson , @RequestPart("file") MultipartFile file ) {
         ProposalRequest request = JsonUtil.convertJsonToObject(proposalJson, ProposalRequest.class);

     proposalService.submitProposal(request , file);
     ApiResponse<Proposal> response = new ApiResponse<>(
        "success" ,
        "Proposal sended successfully"
     );
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }






    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewPdf(@PathVariable String filename) throws IOException {
        return proposalService.getPdf(filename);
    }






    // ✅ Approve Proposal → Moves Proposal to Project
    @PutMapping("/{doctorId}/approve/{proposalId}")
    public ResponseEntity<ApiResponse<Void>> approveProposal(@PathVariable Long doctorId, @PathVariable Long proposalId) {
       proposalService.approveProposal(doctorId, proposalId);
       
       ApiResponse<Void> response = new ApiResponse<>(
        "success",
        "Proposal approved and moved to a project successfully."
    );
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }







    // ✅ Reject Proposal → Keeps it in Proposals Table
    @PutMapping("/{doctorId}/reject/{proposalId}")
    public ResponseEntity<ApiResponse<Void>> rejectProposal(@PathVariable Long doctorId, @PathVariable Long proposalId) {
        proposalService.rejectProposal(doctorId, proposalId);
        ApiResponse<Void> response = new ApiResponse<>(
            "success",
            "Proposal rejected "
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    // ✅ Get Pending Proposals for a Doctor
    @GetMapping("/pending/{doctorId}")
    public ResponseEntity<List<Proposal>> getPendingProposals(@PathVariable Long doctorId) {
        List<Proposal> pendingProposals = proposalService.getPendingProposals(doctorId);
        return ResponseEntity.ok(pendingProposals);
    }







}
