package com.adp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adp.exception.AlreadyAppliedJobPException;
import com.adp.exception.AlreadySaveJobPException;
import com.adp.exception.AppliedJobNotFoundException;
import com.adp.exception.CandidateAlreadyExistException;
import com.adp.exception.CandidateException;
import com.adp.exception.CandidateNotFoundException;
import com.adp.exception.FieldNotNullException;
import com.adp.exception.JobPostNotFoundException;
import com.adp.exception.SaveJobNotFoundException;

/**
 * Global Exception handler to handle all exceptions in the application.
 */
//@RestControllerAdvice
@ControllerAdvice
public class ErrorHandler {
	
	/**
	 *  Handles EmployeeNotFoundException.
	 * @param The WebRequest object
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler({CandidateNotFoundException.class})
	public ResponseEntity<String> handleCandidateNotFound(CandidateNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	
	
	/**
	 *  Handles LeaveNotFoundException.
	 * @param ex
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler({JobPostNotFoundException.class})
	public ResponseEntity<String> handleJobPostNotFound(JobPostNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 *  Handles DepartmentWithDeptCodeAlreadyExists.
	 * @param ex The WebRequest object
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler({AppliedJobNotFoundException.class})
	public ResponseEntity<String> handleAppliedJobNotFound(AppliedJobNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({SaveJobNotFoundException.class})
	public ResponseEntity<String> handleSaveJobNotFound(SaveJobNotFoundException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({AlreadyAppliedJobPException.class})
	public ResponseEntity<String> handleAlreadyAppliedJobException(AlreadyAppliedJobPException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({CandidateAlreadyExistException.class})
	public ResponseEntity<String> handleCandidateAlreadyExistException(CandidateAlreadyExistException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({AlreadySaveJobPException.class})
	public ResponseEntity<String> handleAlreadySaveJobPException(AlreadySaveJobPException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handles FieldNotNullException
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FieldNotNullException.class)
    public ResponseEntity<String> handleFieldCantBeNullExceptions(FieldNotNullException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	/**
	 * Handles Employee Exception for sending HTTP status and error message
	 * @param ex
	 * @return
	 */
	@ExceptionHandler({CandidateException.class})
	public ResponseEntity<String> handleCandidateException(CandidateException ex){
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles MethodArgumentNotValidException
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<String> handleInvalidData(MethodArgumentNotValidException ex){
		List<FieldError> errors = ex.getFieldErrors();
		StringBuffer sb = new StringBuffer();
		for(FieldError fieldError : errors) {
			sb.append(fieldError.getDefaultMessage()+"\n");
		}
		return new ResponseEntity<String>(sb.toString(),HttpStatus.BAD_REQUEST);
	}

}
