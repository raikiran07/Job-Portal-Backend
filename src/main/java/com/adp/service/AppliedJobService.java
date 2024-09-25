package com.adp.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.adp.dto.AppliedIdCandidateDTO;
import com.adp.model.AppliedJob;
import com.adp.model.Candidate;
import com.adp.model.JobPost;

public interface AppliedJobService {
	List<AppliedJob> getAllAppliedJobs();
	AppliedJob getByAppliedJobId(int appliedId);
	String addCandidateAppliedJob(AppliedJob appliedJob);
	String updateCandidateAppliedJob(int appliedId, AppliedJob appliedJob);
	String deleteCandidateAppliedJob(int appliedId);
	List<AppliedJob> getAllByCandidateId(int candidateId);
	List<AppliedJob> getAllByJobId(int jobId);
	List<AppliedIdCandidateDTO> getCandidatesByJobIdAndHrId(int jobId, int hrId);
    List<Candidate> getCandidatesByHrId(int hrId);
    List<AppliedIdCandidateDTO> getAllCandidatesByHrId(int hrId);
}
