package com.adp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adp.exception.FieldNotNullException;
import com.adp.exception.HrNotFoundException;
import com.adp.model.HRDetails;
import com.adp.repository.HrRepository;

@Service
public class HrServiceImpl implements HrService{
	private HrRepository hrRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public HrServiceImpl(HrRepository hrRepository) {
		super();
		this.hrRepository = hrRepository;
	}

	@Override
	public List<HRDetails> getAllHrs() {
		return hrRepository.findAll();
	}

	@Override
	public HRDetails getByHrId(int hrId) {
		return hrRepository.findById(hrId).orElseThrow(()-> new HrNotFoundException("Hr with hrId "+ hrId+" not found" ));
	}

	@Override
	public String addHrDetail(HRDetails hr) {
		if(hr.getHrName()==null || hr.getGender()==null || hr.getHrDOB()==null || hr.getHrEmail()==null ||
				hr.getAge()==0 || hr.getHrMobileNo()==0 || hr.getHrPassword()== null ||hr.getIndustry()==null ) {
			throw new FieldNotNullException();
		}
		hr.setHrPassword(passwordEncoder.encode(hr.getHrPassword()));
		hr.setRole("ROLE_ADMIN");
		hrRepository.save(hr);
		return "Hr added successfully";
	}

	@Override
	public String updateHrDetail(int hrId, HRDetails hrDetails) {
		Optional<HRDetails> hrOptional = hrRepository.findById(hrId);
		if(hrOptional.isEmpty()) {
			throw new HrNotFoundException("Hr with hrId "+ hrId+" not found" );
		}
		HRDetails hr= hrOptional.get();
		if(hrDetails.getHrName()!=null) {
			hr.setHrName(hrDetails.getHrName());
		}
		if(hrDetails.getAge()!=0) {
			hr.setAge(hrDetails.getAge());
		}
		if(hrDetails.getGender()!=null) {
			hr.setGender(hrDetails.getGender());
		}
		if(hrDetails.getHrDOB()!=null) {
			hr.setHrDOB(hrDetails.getHrDOB());
		}
		if(hrDetails.getHrEmail()!=null) {
			hr.setHrEmail(hrDetails.getHrEmail());
		}
		if(hrDetails.getHrMobileNo()!=0) {
			hr.setHrMobileNo(hrDetails.getHrMobileNo());
		}
		if(hrDetails.getIndustry()!=null) {
			hr.setIndustry(hrDetails.getIndustry());
		}
		if(hrDetails.getHrPassword()!=null) {
			hr.setHrPassword(passwordEncoder.encode(hrDetails.getHrPassword()));
		}
//		if(hrDetails.getRole()!=null) {
//			hr.setRole(hrDetails.getRole());
//		}
		hrRepository.save(hr);
		return "updated successfully";
	}

	@Override
	public String deleteHrDetail(int hrId) {
		Optional<HRDetails> hrOptional = hrRepository.findById(hrId);
		if(hrOptional.isEmpty()) {
			throw new HrNotFoundException("Hr with hrId "+ hrId+" not found" );
		}
		hrRepository.deleteById(hrId);
		return "Hr with Id "+hrId+" deleted successfully";
		
	}

	@Override
	public Optional<HRDetails> getHrDetailsByEmail(String email) {
		return hrRepository.findByHrEmail(email);
	}

	

}
