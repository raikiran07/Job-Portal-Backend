package com.adp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.model.AppliedJob;
import com.adp.model.SaveJob;
import com.adp.service.SaveJobService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/saveJobs")
public class SaveJobController {
	private SaveJobService saveJobService;

	public SaveJobController(SaveJobService saveJobService) {
		super();
		this.saveJobService = saveJobService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<SaveJob>> getAll(){
		List<SaveJob> saveJobs= saveJobService.getallSaveJobs();
		return ResponseEntity.ok(saveJobs);
	}
	
	@PostMapping
	public ResponseEntity<String> addSaveJobCandidates(@Valid @RequestBody SaveJob saveJob){
		String result = saveJobService.addSaveJob(saveJob);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{saveJobId}")
	public ResponseEntity<String> deleteSaveJobCandidate(@PathVariable int saveJobId){
		String result = saveJobService.deleteSaveJob(saveJobId);
		return ResponseEntity.ok(result);
	}
	@GetMapping("/candidate/{candidateId}")
	public ResponseEntity<List<SaveJob>> getallSaveJobsByCandidateId(@PathVariable int candidateId){
		List<SaveJob> saveJobs= saveJobService.getSaveJobsByCandiateId(candidateId);
		return ResponseEntity.ok(saveJobs);
	}
	
	
}
