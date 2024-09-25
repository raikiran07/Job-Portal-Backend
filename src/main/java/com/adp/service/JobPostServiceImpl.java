package com.adp.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adp.exception.FieldNotNullException;
import com.adp.exception.JobPostNotFoundException;
import com.adp.model.AppliedJob;
import com.adp.model.JobPost;
import com.adp.repository.JobPostRepository;

@Service
public class JobPostServiceImpl implements JobPostService{
	private JobPostRepository jobPostRepository;
	

	public JobPostServiceImpl(JobPostRepository jobPostRepository) {
		super();
		this.jobPostRepository = jobPostRepository;
	}

	@Override
	public List<JobPost> getAllJobPosts() {		
		return jobPostRepository.findAll()
				.stream().sorted(Comparator.comparingInt(JobPost::getJobId)
						.reversed()).toList();
	}

	@Override
	public JobPost getByJobId(int jobId) {
		return jobPostRepository.findById(jobId).orElseThrow(()-> new JobPostNotFoundException
				("Job Post with Id "+ jobId+" not found"));
	}

	@Override
	public String addJobPost(JobPost jobPost) {
		if(jobPost.getDescription()==null || jobPost.getJobTitle()==null || jobPost.getIndustry()==null
				||jobPost.getEmployementType()==null || jobPost.getJobExperience()==0 || jobPost.getJobQualification()==null
				|| jobPost.getLocation()==null ||jobPost.getNoOfOpening()==0 || jobPost.getPreferredSkill()==null
				|| jobPost.getRequiredSkills()==null || jobPost.getRolesResponsibility()==null ||jobPost.getSalary()==null 
				) {
			throw new FieldNotNullException(" Field can't be empty");
		}
		long millis=System.currentTimeMillis();  
	      
	    // creating a new object of the class Date  
	    java.sql.Date date = new java.sql.Date(millis);  
	    jobPost.setPostedDate(date);
	    jobPost.setStatus("Open");
	    
		jobPostRepository.save(jobPost);
		return "Job has been posted successfully";
	}

	@Override
	public String updateJobPost(int jobId, JobPost jobPost) {
		Optional<JobPost> jobOptional = jobPostRepository.findById(jobId);
		if(jobOptional.isEmpty()) {
			throw new JobPostNotFoundException("Job Post with Id "+ jobId+" not found");
		}
		JobPost job = jobOptional.get();
		if(jobPost.getJobTitle()!=null) {
			job.setJobTitle(jobPost.getJobTitle());
		}
		if(jobPost.getDescription()!=null) {
			job.setDescription(jobPost.getDescription());
		}
		if(jobPost.getIndustry()!=null) {
			job.setIndustry(jobPost.getIndustry());
		}
		if(jobPost.getEmployementType()!=null) {
			job.setEmployementType(jobPost.getEmployementType());
		}
		if(jobPost.getJobExperience()!=0) {
			job.setJobExperience(jobPost.getJobExperience());
		}
		if(jobPost.getJobQualification()!=null) {
			job.setJobQualification(jobPost.getJobQualification());
		}
		if(jobPost.getLocation()!=null) {
			job.setLocation(jobPost.getLocation());
		}
		if(jobPost.getNoOfOpening()!=0) {
			job.setNoOfOpening(jobPost.getNoOfOpening());
		}
		if(jobPost.getPostedDate()!=null) {
			job.setPostedDate(jobPost.getPostedDate());
		}
		if(jobPost.getRequiredSkills()!=null) {
			job.setRequiredSkills(jobPost.getRequiredSkills());
		}
		if(jobPost.getPreferredSkill()!=null) {
			job.setPreferredSkill(jobPost.getPreferredSkill());
		}
		if(jobPost.getRolesResponsibility()!=null) {
			job.setRolesResponsibility(jobPost.getRolesResponsibility());
		}
		if(jobPost.getSalary()!=null) {
			job.setSalary(jobPost.getSalary());
		}
		if(jobPost.getStatus()!=null) {
			job.setStatus(jobPost.getStatus());
		}
		if(jobPost.getCompanyLogo()!=null) {
			job.setCompanyLogo(jobPost.getCompanyLogo());
		}
		jobPostRepository.save(job);
		return "updated successfully";
	}

	@Override
	public String deleteJobPost(int jobId) {
		Optional<JobPost> jobOptional = jobPostRepository.findById(jobId);
		if(jobOptional.isEmpty()) {
			throw new JobPostNotFoundException("Job Post with Id "+ jobId+" not found");
		}
		jobPostRepository.deleteById(jobId);
		return "Job Post with "+jobId+" deleted successfully";
	}
	@Override
	public List<JobPost> getAllJobPostByHrId(int hrId) {
		return jobPostRepository.findAllByHrDetails_HrId(hrId)
				.stream().sorted(Comparator.comparingInt(JobPost::getJobId)
						.reversed()).toList();
	}

}
