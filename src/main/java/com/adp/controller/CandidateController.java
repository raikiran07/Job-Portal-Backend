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

import com.adp.model.Candidate;
import com.adp.service.CandidateService;


import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

	private CandidateService candidateService;

	public CandidateController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<Candidate>> getAll(){
		List<Candidate> candidates= candidateService.getAllCandidates();
		return ResponseEntity.ok(candidates);
	}
	
	@GetMapping("/{candidateId}")
	public ResponseEntity<Candidate> getById(@PathVariable int candidateId){
		Candidate candidate = candidateService.getByCandidateId(candidateId);
		return ResponseEntity.ok(candidate);
	}
	
	@PostMapping
	public ResponseEntity<String> addCandidate(@Valid @RequestBody Candidate candidate){
		String result = candidateService.addCandidate(candidate);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{candidateId}")
	public ResponseEntity<String> updateEmployee(@PathVariable int candidateId, @RequestBody Candidate candidate){
		String result = candidateService.updateCandidate(candidateId, candidate);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{candidateId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int candidateId){
		String result = candidateService.deleteCandidate(candidateId);
		return ResponseEntity.ok(result);
	}
	
}
