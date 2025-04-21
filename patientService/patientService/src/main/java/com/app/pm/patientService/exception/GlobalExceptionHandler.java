package com.app.pm.patientService.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(
			MethodArgumentNotValidException ex)
	{
		Map<String, String > errors = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors()
		.forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
		
		
		return ResponseEntity.badRequest().body(errors);
		
	}
	@ExceptionHandler(EmailAlreadyExitException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(
			EmailAlreadyExitException ex)
	{
		
		log.warn("Email address already exits {}",ex.getMessage());
		Map<String, String > errors = new HashMap<String, String>();
		errors.put("message", "Email address already  exits.");
		
		return ResponseEntity.badRequest().body(errors);
		
	}
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Map<String,String>>  handlePatientNotFoundException(PatientNotFoundException ex)
	{
		log.warn("patient not found {}",ex.getMessage());
		Map<String, String > errors = new HashMap<String, String>();
		errors.put("message", "patient not found.");
		
		return ResponseEntity.badRequest().body(errors);
		
	}

}
