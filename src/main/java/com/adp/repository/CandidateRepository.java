package com.adp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.adp.model.Candidate;


@EnableJpaRepositories
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	Optional<Candidate> findByCandidateEmail(String email);
}
