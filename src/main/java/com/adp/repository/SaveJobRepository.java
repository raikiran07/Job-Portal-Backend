package com.adp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adp.model.SaveJob;

public interface SaveJobRepository extends JpaRepository<SaveJob, Integer>{
	
	List<SaveJob> findAllByCandidate_CandidateId(int candidateId);	
	Optional<SaveJob> findAllByJobPost_JobId( int jobid);
	

}
