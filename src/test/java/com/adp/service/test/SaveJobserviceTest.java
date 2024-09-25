package com.adp.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adp.model.Candidate;
import com.adp.model.JobPost;
import com.adp.model.SaveJob;
import com.adp.service.CandidateService;
import com.adp.service.JobPostService;
import com.adp.service.SaveJobService;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class SaveJobserviceTest {

	@Autowired
	private SaveJobService savejobService;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private JobPostService jobpostService;

	@Test
	@Order(2)
	@Disabled
	void testGetallSaveJobs() {
		List<SaveJob> listAllSaveJobs = savejobService.getallSaveJobs();
		assertNotNull(listAllSaveJobs);
	}

	@Test
	@Order(1)
	@Disabled
	void testAddSaveJob() {
		SaveJob savejob = new SaveJob();
		savejob.setSaveJobId(1);
		Candidate candidate = candidateService.getByCandidateId(2);
		savejob.setCandidate(candidate);
		JobPost jobPost = jobpostService.getByJobId(2);
		savejob.setJobPost(jobPost);
		savejobService.addSaveJob(savejob);
		assertNotNull(savejob);
	}

	@Test
	@Order(4)
	@Disabled
	void testDeleteSaveJob() {
		String result =savejobService.deleteSaveJob(7);
		assertEquals(result,"deleted successfully");
	}

	@Test
	@Order(3)
	@Disabled
	void testGetSaveJobsByCandiateId() {
		List<SaveJob> listSaveJobsById =savejobService.getSaveJobsByCandiateId(2);
		assertNotNull(listSaveJobsById);
		
	}

}
