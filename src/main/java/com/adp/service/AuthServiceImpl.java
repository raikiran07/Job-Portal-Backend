package com.adp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.adp.dto.LoginDTO;
import com.adp.exception.CandidateNotFoundException;
import com.adp.exception.FieldNotNullException;
import com.adp.model.Candidate;
import com.adp.model.HRDetails;
import com.adp.repository.CandidateRepository;
import com.adp.repository.HrRepository;
import com.adp.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService{

	
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private CandidateRepository candidateRepository;
	private HrRepository hrRepository;

	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			CandidateRepository candidateRepository, HrRepository hrRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.candidateRepository = candidateRepository;
		this.hrRepository= hrRepository;
	}


	@Override
	public ResponseEntity<Map<String, String>>  login(LoginDTO loginDTO) {
		
		Map<String, String> map = new HashMap<>();
		
		Optional<Candidate> candidateOptional = candidateRepository.findByCandidateEmail(loginDTO.getEmail());
		Optional<HRDetails> hrOptional = hrRepository.findByHrEmail(loginDTO.getEmail());
		
		if(candidateOptional.isEmpty()&& hrOptional.isEmpty()) {
			throw new CandidateNotFoundException("No Candidate present with username "+ loginDTO.getEmail());
		}
		
		
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
		
		try {
			Authentication authentication = authenticationManager.authenticate(authToken);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String token = jwtTokenProvider.generateToken(authentication);
			
			if(candidateOptional.isPresent()) {
				String candidateId = String.valueOf(candidateOptional.get().getCandidateId());
				map.put("token", token);
				map.put("email", candidateOptional.get().getCandidateEmail());
				map.put("role", candidateOptional.get().getRole());
				map.put("candidateId", candidateId);
				return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
			}
			String hrId = String.valueOf(hrOptional.get().getHrId());
			map.put("token", token);
			map.put("email", hrOptional.get().getHrEmail());
			map.put("role", hrOptional.get().getRole());
			map.put("hrId", hrId);
			map.put("hrName", hrOptional.get().getHrName());
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
			
			
		}
		catch(AuthenticationException e) {
			map.put(e.getMessage(), "Password is incorrect");
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
		
	}

}
