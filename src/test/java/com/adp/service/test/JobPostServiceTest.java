package com.adp.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;

import com.adp.model.HRDetails;
import com.adp.model.JobPost;
import com.adp.service.HrService;
import com.adp.service.JobPostService;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class JobPostServiceTest {
	 
	 @Autowired
	 private JobPostService jobpostService;
	 
	 @Autowired
	 private HrService hrService;

	@Test
	@Order(3)
	@Disabled
	void testGetAllJobPosts() {
		List<JobPost> listAllJobPost = jobpostService.getAllJobPosts();
		assertThat(listAllJobPost).size().isGreaterThan(0);
	}

	@Test
	@Order(2)
	@Disabled
	void testGetByJobId() {
		assertNotNull(jobpostService.getByJobId(106));
		
	}

	@Test
	@Order(1)
	@Disabled
	void testAddJobPost() {
		JobPost jobPost = new JobPost();
		jobPost.setJobTitle("Sr.Java Developer");
		jobPost.setDescription("It is certainly the most famous placeholder text even if there are different");
		String str1 = "2024-03-06";
		Date date1 = Date.valueOf(str1);
		jobPost.setPostedDate(date1);
		jobPost.setNoOfOpening(2);
		jobPost.setLocation("Hyderabad");
		jobPost.setSalary("55000");
		jobPost.setIndustry("IT");
		jobPost.setEmployementType("Full Time");
		jobPost.setRequiredSkills("Java");
		jobPost.setPreferredSkill("React");
		jobPost.setJobQualification("B.Tech");
		jobPost.setJobExperience(2);
		jobPost.setRolesResponsibility("Developer roles will vary greatly depending on companies and job positions");
		jobPost.setStatus("Open");
		jobPost.setCompanyLogo("Logo");
		HRDetails hrDetails = hrService.getByHrId(2);
		jobPost.setHrDetails(hrDetails);
		String updatedResult=jobpostService.addJobPost(jobPost);
		assertEquals(updatedResult,"Job has been posted successfully");
	}

	@Test
	@Order(4)
	@Disabled
	void testUpdateJobPost() {
		JobPost jobPost = jobpostService.getByJobId(106);
		jobPost.setStatus("Closed");
		jobpostService.updateJobPost(106, jobPost);
		assertEquals("Closed",jobpostService.getByJobId(106).getStatus());
	}

	@Test
	@Order(6)
	@Disabled
	void testDeleteJobPost() {
		String result=jobpostService.deleteJobPost(106);
		assertEquals(result,"Job Post with 106 deleted successfully");
	}

	@Test
	@Order(5)
	@Disabled
	void testGetAllJobPostByHrId() {
		List<JobPost> listjobPostByHr = jobpostService.getAllJobPostByHrId(2);
		assertThat(listjobPostByHr).size().isGreaterThan(0);
	}

}
