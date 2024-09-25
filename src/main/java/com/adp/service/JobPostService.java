package com.adp.service;

import java.util.List;

import com.adp.model.AppliedJob;
import com.adp.model.JobPost;

public interface JobPostService {
	List<JobPost> getAllJobPosts();
	JobPost getByJobId(int jobId);
	String addJobPost(JobPost jobPost);
	String updateJobPost(int jobId, JobPost jobPost);
	String deleteJobPost(int jobId);
	List<JobPost> getAllJobPostByHrId(int hrId);

}
