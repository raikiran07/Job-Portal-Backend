package com.adp.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adp.dto.AppliedIdCandidateDTO;
import com.adp.model.AppliedJob;
import com.adp.model.Candidate;
import com.adp.model.HRDetails;
import com.adp.model.JobPost;
import com.adp.repository.AppliedJobRepository;
import com.adp.repository.CandidateRepository;
import com.adp.repository.HrRepository;
import com.adp.repository.JobPostRepository;
import com.adp.service.AppliedJobService;
import com.adp.service.CandidateService;
import com.adp.service.HrService;
import com.adp.service.JobPostService;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class AppliedJobServiceTest {
	
	@Autowired
	private AppliedJobService appliedjobService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private JobPostService jobpostService;
	
	@Autowired
	private HrService hrService; 

	@Test
	@Order(3)
	@Disabled
	void testGetAllAppliedJobs() {
		List<AppliedJob> listAppliedJobs = appliedjobService.getAllAppliedJobs();
		assertNotNull(listAppliedJobs);
	}

	@Test
	@Order(2)
	@Disabled
	void testGetByAppliedJobId() {
		assertNotNull(appliedjobService.getAllByJobId(11));
	}

	@Test
	@Order(1)
	@Disabled
	void testAddCandidateAppliedJob() {
		AppliedJob appliedJob = new AppliedJob();
		Candidate candidate = candidateService.getByCandidateId(2);
		appliedJob.setCandidate(candidate);
		JobPost jobPost = jobpostService.getByJobId(103);
		appliedJob.setJobPost(jobPost);
		appliedJob.setStatus("Applied");
//		appliedJob.setPdf("xyz");
		String result = appliedjobService.addCandidateAppliedJob(appliedJob);
		assertNotNull(result,"Job Applied successfully");
	}

	@Test
	@Order(4)
	@Disabled
	void testUpdateCandidateAppliedJob() {
		AppliedJob appliedJob = appliedjobService.getByAppliedJobId(11);
		appliedJob.setStatus("Rejected");
		appliedjobService.updateCandidateAppliedJob(11, appliedJob);
		assertEquals("Rejected",appliedjobService.getByAppliedJobId(11).getStatus());
	}

	@Test
	@Order(10)
	@Disabled
	void testDeleteCandidateAppliedJob() {
		String result = appliedjobService.deleteCandidateAppliedJob(11);
		assertEquals(result,"deleted successfully");
	}

	@Test
	@Order(5)
	@Disabled
	void testGetAllByCandidateId() {
		List<AppliedJob> listByCandidateId = appliedjobService.getAllByCandidateId(2);
		assertThat(listByCandidateId).size().isGreaterThan(0);
	}

	@Test
	@Order(6)
	@Disabled
	void testGetAllByJobId() {
		List<AppliedJob> listByJobId = appliedjobService.getAllByJobId(103);
		assertThat(listByJobId).size().isGreaterThan(0);
	}
	
	@Test
	@Order(7)
	@Disabled
	void getCandidatesByJobIdAndHrId() {
		List<AppliedIdCandidateDTO> candidates = appliedjobService.getCandidatesByJobIdAndHrId(103, 2);
		assertThat(candidates).size().isGreaterThan(0);
	}
	
	@Test
	@Order(8)
	@Disabled
	void getCandidatesByHrId() {
		List<Candidate> listByHrId = appliedjobService.getCandidatesByHrId(2);
		assertThat(listByHrId).size().isGreaterThan(0);
	}
	
	@Test
	@Order(9)
	@Disabled
	void getAllCandidatesByHrId() {
		List<AppliedIdCandidateDTO> candidate = appliedjobService.getAllCandidatesByHrId(2);
		assertThat(candidate).size().isGreaterThan(0);
	}

}
