package com.adp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.adp.model.AppliedJob;
import com.adp.model.JobPost;

@EnableJpaRepositories
public interface JobPostRepository extends JpaRepository<JobPost, Integer>{
	
	List<JobPost> findAllByHrDetails_HrId(int hrId);

}
