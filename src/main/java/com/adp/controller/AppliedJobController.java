package com.adp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.dto.AppliedIdCandidateDTO;
import com.adp.model.AppliedJob;
import com.adp.model.Candidate;
import com.adp.service.AppliedJobService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/appliedJob")
public class AppliedJobController {
	private AppliedJobService appliedJobService;

	public AppliedJobController(AppliedJobService appliedJobService) {
		super();
		this.appliedJobService = appliedJobService;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<AppliedJob>> getAll(){
		List<AppliedJob> appliedJobs= appliedJobService.getAllAppliedJobs();
		return ResponseEntity.ok(appliedJobs);
	}
	
	@GetMapping("/{appliedId}")
	public ResponseEntity<AppliedJob> getById(@PathVariable int appliedId){
		AppliedJob appliedJob = appliedJobService.getByAppliedJobId(appliedId);
		return ResponseEntity.ok(appliedJob);
	}
	
	@PostMapping
	public ResponseEntity<String> addAppliedJobCandidates(@Valid @RequestBody AppliedJob appliedJob){
		String result = appliedJobService.addCandidateAppliedJob(appliedJob);
		return ResponseEntity.ok(result);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{appliedId}")
	public ResponseEntity<String> updateAppliedJobCandidate(@PathVariable int appliedId, @RequestBody AppliedJob appliedJob){
		String result = appliedJobService.updateCandidateAppliedJob(appliedId, appliedJob);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{appliedId}")
	public ResponseEntity<String> deleteAppliedJobCandidate(@PathVariable int appliedId){
		String result = appliedJobService.deleteCandidateAppliedJob(appliedId);
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("/candidate/{candidateId}")
	public  ResponseEntity<List<AppliedJob>>  getAllByCandidateId(@PathVariable int candidateId) {
		List<AppliedJob> appliedJobs= appliedJobService.getAllByCandidateId(candidateId);
		return ResponseEntity.ok(appliedJobs);
	}

	@GetMapping("/jobPost/{jobId}")
	public  ResponseEntity<List<AppliedJob>>  getAllByjobId(@PathVariable int jobId) {
		List<AppliedJob> appliedJobs= appliedJobService.getAllByJobId(jobId);
		return ResponseEntity.ok(appliedJobs);
	}
	
	   @GetMapping("/jobPost/{jobId}/hr/{hrId}")
	    public  ResponseEntity<List<AppliedIdCandidateDTO>>  getCandidatesByJobIdAndHrId(@PathVariable int jobId, @PathVariable int hrId) {
	        List<AppliedIdCandidateDTO> appliedJobs= appliedJobService.getCandidatesByJobIdAndHrId(jobId, hrId);
	        return ResponseEntity.ok(appliedJobs);
	    }
	    
	    @GetMapping("/candidate/hr/{hrId}")
	    public ResponseEntity<List<Candidate>> getAppliedcandidatesByHrId (@PathVariable int hrId){
	        List<Candidate> candidates = appliedJobService.getCandidatesByHrId(hrId);
	        return new ResponseEntity<List<Candidate>>(candidates, HttpStatus.OK);
	    }
	    
		   @GetMapping("/candidates/hr/{hrId}")
		    public  ResponseEntity<List<AppliedIdCandidateDTO>>  getAllCandidatesByHrId( @PathVariable int hrId) {
		        List<AppliedIdCandidateDTO> appliedJobs= appliedJobService.getAllCandidatesByHrId(hrId);
		        return ResponseEntity.ok(appliedJobs);
		    }
}
