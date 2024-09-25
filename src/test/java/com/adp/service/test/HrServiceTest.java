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
import com.adp.service.HrService;

@SpringBootTest
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class HrServiceTest {
	
	@Autowired
	HrService hrService; 

	@Test
	@Order(3)
	@Disabled
	void testGetAllHrs() {
		List<HRDetails> listAllHrs= hrService.getAllHrs();
		assertThat(listAllHrs).size().isGreaterThan(0);	
	}

	@Test
	@Order(2)
	@Disabled
	void testGetByHrId() {
		assertNotNull(hrService.getByHrId(1));
	}

	@Test
	@Order(1)
	@Disabled
	void testAddHrDetail() {
		HRDetails hrDetails = new HRDetails();
		hrDetails.setHrName("Keerthana");
		hrDetails.setHrEmail("keerthana@gmail.com");
		hrDetails.setHrPassword("Keerthana123");
		hrDetails.setIndustry("MT");
		hrDetails.setHrMobileNo(987654321);
		hrDetails.setGender("Female");
		hrDetails.setAge(23);
		String str = "2001-12-27";
		Date date = Date.valueOf(str);
		hrDetails.setHrDOB(date);
		hrDetails.setRole("Role_Admin");
		hrService.addHrDetail(hrDetails);
		assertNotNull(hrService.getHrDetailsByEmail("keerthana@gmail.com"));
	}

	@Test
	@Order(5)
	@Disabled
	void testUpdateHrDetail() {
		HRDetails hr=hrService.getByHrId(1);
		hr.setHrMobileNo(978652780);
		hrService.updateHrDetail(1, hr);
		assertEquals(978652780,hrService.getByHrId(1).getHrMobileNo());
	}

	@Test
	@Order(6)
	@Disabled
	void testDeleteHrDetail() {
		String result = hrService.deleteHrDetail(1);
		assertEquals(result,"Hr with Id 1 deleted successfully");
	}

	@Test
	@Order(4)
	@Disabled
	void testGetHrDetailsByEmail() {
		HRDetails hrDetails = hrService.getHrDetailsByEmail("keerthana@gmail.com").get();
		assertNotNull(hrDetails);
	}

}
