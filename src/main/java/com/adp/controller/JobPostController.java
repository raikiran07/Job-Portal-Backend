package com.adp.controller;

import java.util.List;

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

import com.adp.model.JobPost;
import com.adp.service.JobPostService;

import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/api/jobPost")
public class JobPostController {

	private JobPostService jobPostService;

	public JobPostController(JobPostService jobPostService) {
		super();
		this.jobPostService = jobPostService;
	}
	
	@GetMapping
	public ResponseEntity<List<JobPost>> getAll(){
		List<JobPost> jobPosts= jobPostService.getAllJobPosts();
		return ResponseEntity.ok(jobPosts);
	}
	
	@GetMapping("/{jobId}")
	public ResponseEntity<JobPost> getById(@PathVariable int jobId){
		JobPost jobPost = jobPostService.getByJobId(jobId);
		return ResponseEntity.ok(jobPost);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<String> addJobPost(@Valid @RequestBody JobPost jobPost){
		String result = jobPostService.addJobPost(jobPost);
		return ResponseEntity.ok(result);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{jobId}")
	public ResponseEntity<String> updateJobPost(@PathVariable int jobId, @RequestBody JobPost jobPost){
		String result = jobPostService.updateJobPost(jobId, jobPost);
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{jobId}")
	public ResponseEntity<String> deleteJobPost(@PathVariable int jobId){
		String result = jobPostService.deleteJobPost(jobId);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/hrDetails/{hrId}")
	public  ResponseEntity<List<JobPost>>  getAllJobPostByHrId(@PathVariable int hrId) {
		List<JobPost> postedJobs= jobPostService.getAllJobPostByHrId(hrId);
		return ResponseEntity.ok(postedJobs);
	}
	
}
