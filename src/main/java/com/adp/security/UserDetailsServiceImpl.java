package com.adp.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adp.exception.CandidateNotFoundException;
import com.adp.model.Candidate;
import com.adp.model.HRDetails;
import com.adp.repository.CandidateRepository;
import com.adp.repository.HrRepository;

import java.util.Arrays;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private CandidateRepository candidateRepository;
	private HrRepository hrRepository;
	
	public UserDetailsServiceImpl(CandidateRepository candidateRepository, HrRepository hrRepository) {
		super();
		this.candidateRepository = candidateRepository;
		this.hrRepository =  hrRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Candidate> candidateOptional = candidateRepository.findByCandidateEmail(email);
		Optional<HRDetails> hrOptional = hrRepository.findByHrEmail(email);
		
		if(candidateOptional.isEmpty()&& hrOptional.isEmpty()) {
			throw new CandidateNotFoundException("No Candidate present with username "+ email);
		}
		
//		Candidate candidate = candidateRepository.findByCandidateEmail(email).
//				orElseThrow(()->new CandidateNotFoundException("No candidate with this username"));
//		Optional<Candidate> candidate = candidateRepository.findByCandidateEmail(email);
//		Optional<HRDetails> hrDetails =hrRepository.findByHrEmail(email);
//		if(candidate.isEmpty()) {
//			if(hrDetails.isEmpty()) {
//				new CandidateNotFoundException("No candidate with this username");
//			}
//		}
		
		if(candidateOptional.isPresent()) {
			Candidate candidate = candidateOptional.get();
			List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(candidate.getRole()));
			
			return new User(candidate.getCandidateEmail(), candidate.getCandidatePassword(), authorities);
		}
		HRDetails hr = hrOptional.get();
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(hr.getRole()));
		
		return new User(hr.getHrEmail(), hr.getHrPassword(), authorities);
		
		
	}
	

}
