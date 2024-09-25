package com.adp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.adp.model.Candidate;
import com.adp.model.HRDetails;

@EnableJpaRepositories
public interface HrRepository extends JpaRepository<HRDetails, Integer>{
	Optional<HRDetails> findByHrEmail(String email);

}
