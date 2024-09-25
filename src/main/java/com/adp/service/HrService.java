package com.adp.service;

import java.util.List;
import java.util.Optional;

import com.adp.model.Candidate;
import com.adp.model.HRDetails;

public interface HrService {
	List<HRDetails> getAllHrs();
	HRDetails getByHrId(int hrId);
	String addHrDetail(HRDetails hr);
	String updateHrDetail(int hrId, HRDetails hr);
	String deleteHrDetail(int hrId);
	Optional<HRDetails> getHrDetailsByEmail(String email);

}
