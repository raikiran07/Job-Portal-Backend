package com.adp.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.adp.dto.LoginDTO;
import com.adp.model.Candidate;

/**
 * Auth interface for managing Login requests
  */
public interface AuthService {

	ResponseEntity<Map<String, String>> login(LoginDTO loginDTO);
	
	
}
