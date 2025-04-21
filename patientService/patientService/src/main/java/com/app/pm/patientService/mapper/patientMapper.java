package com.app.pm.patientService.mapper;

import java.time.LocalDate;

import com.app.pm.patientService.dto.PatientRequestDTO;
import com.app.pm.patientService.dto.PatientResponseDTO;
import com.app.pm.patientService.model.Patient;

public class patientMapper {
	
	public static PatientResponseDTO  toDTO(Patient patient) {
		
		PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
		
		patientResponseDTO.setId(patient.getId().toString());
		patientResponseDTO.setName(patient.getName());
		patientResponseDTO.setEmail(patient.getEmail());
		patientResponseDTO.setAddress(patient.getAddress());
		patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
		
		return patientResponseDTO;
		
	}
   public static Patient  toModel(PatientRequestDTO patientreRequestDTO) {
		
		Patient patient = new Patient();
		patient.setName(patientreRequestDTO.getName());
		patient.setEmail(patientreRequestDTO.getEmail());
		patient.setAddress(patientreRequestDTO.getAddress());
		patient.setDateOfBirth(LocalDate.parse(patientreRequestDTO.getDateOfBirth()));
	    patient.setRegisteredDate(LocalDate.parse(patientreRequestDTO.getRegistredDate()));	
		return patient;
		
	}

}
