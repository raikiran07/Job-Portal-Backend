package com.adp.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adp.dto.AppliedIdCandidateDTO;
import com.adp.exception.AlreadyAppliedJobPException;
import com.adp.exception.AppliedJobNotFoundException;
import com.adp.model.AppliedJob;
import com.adp.model.Candidate;
import com.adp.model.JobPost;
import com.adp.repository.AppliedJobRepository;

@Service
public class AppliedJobServiceImpl implements AppliedJobService {
	private AppliedJobRepository appliedJobRepository;
	

	public AppliedJobServiceImpl(AppliedJobRepository appliedJobRepository) {
		super();
		this.appliedJobRepository = appliedJobRepository;
	}

	@Override
	public List<AppliedJob> getAllAppliedJobs() {
		return appliedJobRepository.findAll()
				.stream().sorted(Comparator.comparingInt(AppliedJob::getAppliedId)
						.reversed()).toList();
	}

	@Override
	public AppliedJob getByAppliedJobId(int appliedId) {
		return appliedJobRepository.findById(appliedId).orElseThrow(()-> new AppliedJobNotFoundException
				("Applied job with Id "+appliedId+" not found"));
	}

	@Override
	public String addCandidateAppliedJob(AppliedJob appliedJob) {
		Optional<AppliedJob> apOptional = appliedJobRepository.findByCandidateCandidateIdAndJobPostJobId
				(appliedJob.getCandidate().getCandidateId(), appliedJob.getJobPost().getJobId());
		if(apOptional.isPresent()) {
			throw new AlreadyAppliedJobPException("Already applied for the same job");
		}
		appliedJob.setStatus("Applied");
		appliedJobRepository.save(appliedJob);
		return "Job Applied successfully";
	}

	@Override
	public String updateCandidateAppliedJob(int appliedId, AppliedJob appliedJob) {
		Optional<AppliedJob> apOptional = appliedJobRepository.findById(appliedId);
		if(apOptional.isEmpty()) {
			throw new AppliedJobNotFoundException();
		}
		AppliedJob applied = apOptional.get();
		
		if(appliedJob.getStatus()!=null) {
			applied.setStatus(appliedJob.getStatus());
		}
		if(appliedJob.getPdf()!=null) {
			applied.setPdf(appliedJob.getPdf());
		}
		appliedJobRepository.save(applied);
		return "updated successfully";
	}

	@Override
	public String deleteCandidateAppliedJob(int appliedId) {
		Optional<AppliedJob> apOptional = appliedJobRepository.findById(appliedId);
		if(apOptional.isEmpty()) {
			throw new AppliedJobNotFoundException();
		}
		appliedJobRepository.deleteById(appliedId);
		return "deleted successfully";
	}

	@Override
	public List<AppliedJob> getAllByCandidateId(int candidateId) {	
		return appliedJobRepository.findAllByCandidate_CandidateId(candidateId)
				.stream().sorted(Comparator.comparingInt(AppliedJob::getAppliedId)
						.reversed()).toList();
	}

	@Override
	public List<AppliedJob> getAllByJobId(int jobId) {
		return appliedJobRepository.findAllByJobPost_JobId(jobId)
				.stream().sorted(Comparator.comparingInt(AppliedJob::getAppliedId)
				.reversed()).toList();
	}

	@Override
    public List<AppliedIdCandidateDTO> getCandidatesByJobIdAndHrId(int jobId, int hrId) {

        List<AppliedJob> appliedJobs = appliedJobRepository.findAllByJobPost_JobIdAndJobPost_HrDetails_HrId(jobId, hrId);
        List<AppliedIdCandidateDTO> candidates = appliedJobs.stream().map(job -> new AppliedIdCandidateDTO(job.getCandidate(), job.getAppliedId(), job.getStatus())).toList();
    
        return candidates.stream().sorted(Comparator.comparingInt(AppliedIdCandidateDTO::getAppliedId)
				.reversed()).toList();
    }

    @Override
    public List<Candidate> getCandidatesByHrId(int hrId) {
        List<AppliedJob> appliedJobs = appliedJobRepository.findAllByJobPost_HrDetails_HrId(hrId);
        List<Candidate> candidates = appliedJobs.stream().map(jobs -> jobs.getCandidate()).toList();
        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }
        return candidates.stream().sorted(Comparator.comparingInt(Candidate::getCandidateId)
				.reversed()).toList();
    }

	@Override
	public List<AppliedIdCandidateDTO> getAllCandidatesByHrId(int hrId) {
		 List<AppliedJob> appliedJobs = appliedJobRepository.findAllAppliedCandidatesByJobPost_HrDetails_HrId(hrId);
	        List<AppliedIdCandidateDTO> candidates = appliedJobs.stream().map(job -> new AppliedIdCandidateDTO(job.getCandidate(), job.getAppliedId(), job.getStatus())).toList();
	    
	        return candidates.stream().sorted(Comparator.comparingInt(AppliedIdCandidateDTO::getAppliedId)
					.reversed()).toList();
	}

}
