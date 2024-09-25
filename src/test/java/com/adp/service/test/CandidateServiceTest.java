package com.adp.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;

import com.adp.model.Candidate;
import com.adp.service.CandidateService;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class CandidateServiceTest {

	@Autowired
	CandidateService candidateService;
	
	@Order(3)
	@Test
	@Disabled
	void testGetAllCandidates() {
		List<Candidate> listAllCandidates = candidateService.getAllCandidates();
		assertNotNull(listAllCandidates);
	}

	@Order(2)
	@Test
	@Disabled
	void testGetByCandidateId() {
		assertNotNull(candidateService.getByCandidateId(19));
	}

	@Order(1)
	@Test
	@Disabled
	void testAddCandidate() {
		Candidate candidate = new Candidate();
		candidate.setFirstName("Preksha");
		candidate.setLastName("Sarwade");
		String str = "2001-10-15";
		Date date= Date.valueOf(str);
		candidate.setCandidateDOB(date);
		candidate.setGender("Female");
		candidate.setCandidateMobileNo(9876543210L);
		candidate.setCandidateEmail("preksha@gmail.com");
		candidate.setCandidatePassword("Preksha123");
		candidate.setAddress("Hyderabad");
		candidate.setSkills("Python");
		candidate.setExperience(1);
		candidate.setLinkedinURL("xyz.com");
		candidate.setGithubURL("xyz.profile");
		candidate.setQualification("B.Tech");
		candidate.setRole("Role_User");
		candidate.setTwitterURL("xyz@com");
		candidate.setCandidateBio("Hello!");
		candidate.setIndustry("IT");
		candidate.setProfile("xyzzzzzz");
		candidate.setProfession("HEDU");
		candidate.setCandidateImage("EU");
		candidateService.addCandidate(candidate);
		assertNotNull(candidateService.getCandidateByEmail("preksha@gmail.com"));
	}

	@Test
	@Order(4)
	@Disabled
	void testUpdateCandidate() {
		Candidate candidate = candidateService.getByCandidateId(19);
		candidate.setAddress("Secunderabad");
		candidateService.updateCandidate(19, candidate);
		assertEquals("Secunderabad",candidateService.getByCandidateId(19).getAddress());
	}

	@Test
	@Order(6)
	@Disabled
	void testDeleteCandidate() {
		String result=candidateService.deleteCandidate(19);
		assertEquals(result,"Candidate with 19 deleted Successfully");
	}

	@Test
	@Order(5)
	@Disabled
	void testGetCandidateByEmail() {
		Candidate candidate = candidateService.getCandidateByEmail("preksha@gmail.com").get();
		assertNotNull(candidate);
	}

}
