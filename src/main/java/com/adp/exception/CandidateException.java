package com.adp.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CandidateException extends RuntimeException {
	
	private HttpStatus status;
	private String message;

}
