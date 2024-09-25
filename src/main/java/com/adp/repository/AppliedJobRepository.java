package com.adp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.adp.model.AppliedJob;
import com.adp.model.Candidate;
import com.adp.model.JobPost;


public interface AppliedJobRepository extends JpaRepository<AppliedJob, Integer> {

	List<AppliedJob> findAllByCandidate_CandidateId( int candidateId);
	List<AppliedJob> findAllByJobPost_JobId( int jobId);
	Optional<AppliedJob> findByCandidateCandidateIdAndJobPostJobId( int candidateId, int jobId);
	List<AppliedJob> findAllByJobPost_JobIdAndJobPost_HrDetails_HrId(int jobId, int hrId);
    List<AppliedJob> findAllByJobPost_HrDetails_HrId(int hrId);
    List<AppliedJob> findAllAppliedCandidatesByJobPost_HrDetails_HrId(int hrId);

}
