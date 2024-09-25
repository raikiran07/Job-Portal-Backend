package com.adp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adp.exception.CandidateAlreadyExistException;
import com.adp.exception.CandidateNotFoundException;
import com.adp.exception.FieldNotNullException;
import com.adp.model.Candidate;
import com.adp.repository.CandidateRepository;


@Service
public class CandidateServiceImpl implements CandidateService {
	private CandidateRepository candidateRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		super();
		this.candidateRepository = candidateRepository;
	}

	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}

	@Override
	public Candidate getByCandidateId(int candidateId) {
		return candidateRepository.findById(candidateId).orElseThrow(()-> new CandidateNotFoundException
				("Candidate with candidateId "+ candidateId+ " not found"));
	}

	@Override
	public String addCandidate(Candidate candidate)  {
		if(candidate.getCandidateEmail()==null || candidate.getFirstName()==null || candidate.getLastName()==null ||
				candidate.getAddress()==null ||candidate.getCandidateDOB()==null || candidate.getCandidatePassword() ==null ||
				candidate.getGender()==null 
				) {
			throw new FieldNotNullException("Field can't be empty");
		}
		
		Optional<Candidate> candOptional= candidateRepository.findByCandidateEmail(candidate.getCandidateEmail());
		if(candOptional.isPresent()) {
			throw new CandidateAlreadyExistException("Candidate with email "+ candidate.getCandidateEmail()+" already exits");
		}
		
		candidate.setRole("ROLE_USER");
		candidate.setCandidatePassword(passwordEncoder.encode(candidate.getCandidatePassword()));
		candidateRepository.save(candidate);
		return "Candidate with name "+candidate.getFirstName() +" saved successfully";
	}
	
	@Override
	public String updateCandidate(int candidateId, Candidate candidate) {
		Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);
		if(candidateOpt.isEmpty()) {
			throw new CandidateNotFoundException("Candidate with Candidate Id "+candidateId+ " not found");
		}
		Candidate tCandidate = candidateOpt.get();
		if(candidate.getAddress()!=null) {
			tCandidate.setAddress(candidate.getAddress());
		}
		if(candidate.getCandidateDOB()!=null) {
			tCandidate.setCandidateDOB(candidate.getCandidateDOB());
		}
		if(candidate.getCandidateEmail()!=null) {
			tCandidate.setCandidateEmail(candidate.getCandidateEmail());
		}
		if(candidate.getCandidateMobileNo()!=null) {
			tCandidate.setCandidateMobileNo(candidate.getCandidateMobileNo());
		}
//		if(candidate.getCandidatePassword()!=null) {
//			 tCandidate.setCandidatePassword(passwordEncoder.encode(candidate.getCandidatePassword()));
//		}
		
		if(candidate.getExperience()!=0) {
			tCandidate.setExperience(candidate.getExperience());
		}
		if(candidate.getFirstName()!=null) {
			tCandidate.setFirstName(candidate.getFirstName());
		}
		if(candidate.getGender()!=null) {
			tCandidate.setGender(candidate.getGender());
		}
		if(candidate.getLastName()!=null) {
			tCandidate.setLastName(candidate.getLastName());
		}
		if(candidate.getGithubURL()!=null) {
			tCandidate.setGithubURL(candidate.getGithubURL());
		}
		if(candidate.getLinkedinURL()!=null) {
			tCandidate.setLinkedinURL(candidate.getLinkedinURL());
		}
		if(candidate.getQualification()!=null) {
			tCandidate.setQualification(candidate.getQualification());
		}
		if(candidate.getSkills()!=null) {
			tCandidate.setSkills(candidate.getSkills());
		}
		if(candidate.getCandidateBio()!=null) {
			tCandidate.setCandidateBio(candidate.getCandidateBio());
		}
		if(candidate.getTwitterURL()!=null) {
			tCandidate.setTwitterURL(candidate.getTwitterURL());
		}
		if(candidate.getCandidateImage()!=null) {
			tCandidate.setCandidateImage(candidate.getCandidateImage());
		}
		if(candidate.getIndustry()!=null) {
			tCandidate.setIndustry(candidate.getIndustry());
		}
		if(candidate.getProfile()!=null) {
			tCandidate.setProfile(candidate.getProfile());
		}
		if(candidate.getProfession()!=null) {
			tCandidate.setProfession(candidate.getProfession());
		}
		candidateRepository.save(tCandidate);
		return "updated successfully";
	}

	@Override
	public String deleteCandidate(int candidateId) {
		Optional<Candidate> candidateOpt= candidateRepository.findById(candidateId);
		if(candidateOpt.isEmpty()) {
			throw new CandidateNotFoundException("Candidate with candidateId "+ candidateId+" not found");
		} 
		candidateRepository.deleteById(candidateId);
		return "Candidate with "+ candidateId + " deleted Successfully";
		
	}

	@Override
	public Optional<Candidate> getCandidateByEmail(String email) {
		return candidateRepository.findByCandidateEmail(email);
	}


}
