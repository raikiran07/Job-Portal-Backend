package com.adp.service;

import java.util.List;
import java.util.Optional;

import com.adp.model.Candidate;

public interface CandidateService  {
	List<Candidate> getAllCandidates();
	Candidate getByCandidateId(int candidateId);
	String addCandidate(Candidate candidate);
	String updateCandidate(int candidateId, Candidate candidate);
	String deleteCandidate(int candidateId);
	Optional<Candidate> getCandidateByEmail(String email);

}
