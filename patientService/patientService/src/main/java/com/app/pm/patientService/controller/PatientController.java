package com.app.pm.patientService.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pm.patientService.dto.PatientRequestDTO;
import com.app.pm.patientService.dto.PatientResponseDTO;
import com.app.pm.patientService.dto.validators.CreatePatientValidationGroup;
import com.app.pm.patientService.grpc.BillingServiceGrpcClient;
import com.app.pm.patientService.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patients")
@Tag(name="patient",description="Api for managing Patients")
@Slf4j
public class PatientController {
	
	private final PatientService patientService;
	
	
	
	PatientController(PatientService patientService, BillingServiceGrpcClient billingServiceGrpcClient)
	{
		this.patientService = patientService;
		
		
	}
	
	@GetMapping
	@Operation(summary="get patients")
	public ResponseEntity<List<PatientResponseDTO>> getPatients()
	{
		log.info("Fetching list of patients");
		
		List<PatientResponseDTO> patients = patientService.getPatient();
		
		log.debug("Fetched patients: {}", patients);
		return ResponseEntity.ok().body(patients);
		
	}
	
	@PostMapping
	@Operation(summary="create new patient")
	public ResponseEntity<PatientResponseDTO> createPatient(
			@Validated({Default.class,CreatePatientValidationGroup.class})
			@RequestBody PatientRequestDTO patieRequestDTO)
	{
		log.info("Creating a new patient with data: {}", patieRequestDTO);
		
		PatientResponseDTO patient = patientService.createPatient(patieRequestDTO);
		
		log.debug("Created patient: {}", patient);
		return ResponseEntity.ok().body(patient);
		
	}
	
	@PutMapping("/{id}")
	@Operation(summary="update a patient")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id , 
			@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO)
	{
		log.info("Updating patient with ID: {}", id);
        log.debug("Update data: {}", patientRequestDTO);
        
		PatientResponseDTO updatedPatient = patientService
				.updatePatient(id, patientRequestDTO);
		
		log.debug("Updated patient: {}", updatedPatient);
		return ResponseEntity.ok().body(updatedPatient);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary="Delete a patient")
	public ResponseEntity<Void> deletePatient(@PathVariable UUID id)
	{
		log.info("Deleting patient with ID: {}", id);
		
		patientService.deletePatient(id);
		
		log.debug("Deleted patient with ID: {}", id);
		return  ResponseEntity.noContent().build();
	}
}
