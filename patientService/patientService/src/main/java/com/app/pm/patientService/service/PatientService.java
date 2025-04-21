package com.app.pm.patientService.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.pm.patientService.dto.PatientRequestDTO;
import com.app.pm.patientService.dto.PatientResponseDTO;
import com.app.pm.patientService.exception.EmailAlreadyExitException;
import com.app.pm.patientService.exception.PatientNotFoundException;
import com.app.pm.patientService.grpc.BillingServiceGrpcClient;
import com.app.pm.patientService.mapper.patientMapper;
import com.app.pm.patientService.model.Patient;
import com.app.pm.patientService.repository.PatientRepository;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientService {
	
	private PatientRepository patientRepository;
	private  BillingServiceGrpcClient billingServiceGrpcClient;
	
	private Gson gson;
	
	private ModelMapper modelMapper;
	
	PatientService(PatientRepository patientRepository,Gson gson,ModelMapper modelMapper,
			BillingServiceGrpcClient billingServiceGrpcClient)
	{
		this.patientRepository=patientRepository;
		this.gson = gson;
		this.modelMapper=modelMapper;
		this.billingServiceGrpcClient=billingServiceGrpcClient;
	}
	
	public List<PatientResponseDTO> getPatient()
	{
		log.info("Fetching all patients from the repository");
		List<Patient> patients = patientRepository.findAll();
		log.debug("Patients fetched: {}", gson.toJson(patients));
	    return   patients.stream()
//				.map(patient->patientMapper.toDTO(patient)).toList();
				.map(patientMapper::toDTO).toList();
	}
	
	public PatientResponseDTO createPatient(
			PatientRequestDTO patientRequestDTO)
	{
		log.info("Creating patient: {}", gson.toJson(patientRequestDTO));
		
		if(patientRepository.existsByEmail(patientRequestDTO.getEmail()))
		{	
		    log.warn("Attempt to create patient with existing email: {}", patientRequestDTO.getEmail());
			throw new EmailAlreadyExitException("A patient with this email "
					+ "already exit."+patientRequestDTO.getEmail());
		}
		
		Patient newPatient = patientRepository
				.save(patientMapper.toModel(patientRequestDTO));
		
		log.debug("Patient created: {}", gson.toJson(newPatient));
		
		billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(), 
				newPatient.getName(), newPatient.getEmail());
		
		return patientMapper.toDTO(newPatient);
		
	}
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
		
		 log.info("Updating patient with ID: {}", id);
	     log.debug("Update data: {}", gson.toJson(patientRequestDTO));
	    try {
	        Patient patient = patientRepository.findById(id)
	            .orElseThrow(() -> new PatientNotFoundException("patient not found with id: " + id));
            
	        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
	            throw new EmailAlreadyExitException("A patient with this email already exists: " 
	        + patientRequestDTO.getEmail());
	        }
	        
	        modelMapper.map(patientRequestDTO, patient); 
	        patientRepository.save(patient);
	        log.debug("Patient updated: {}", gson.toJson(patient));
	        return patientMapper.toDTO(patient);
	    } catch (Exception e) {
	    	log.error("Error updating patient: {}", e.getMessage(), e);
	        e.printStackTrace();
	        throw e;
	    }
	}
	
	public void deletePatient(UUID id)
	{
	patientRepository.deleteById(id);	
	}

}
