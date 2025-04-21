package com.app.pm.patientService.dto;

import com.app.pm.patientService.dto.validators.CreatePatientValidationGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {	
	@NotBlank(message = "Name is required")
	@Size(max = 100 , message= "name cannot exceeed 100 character")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message ="Email should be valid")
	private String email;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "Date Of Birth is required")
	private String dateOfBirth;
	
	@NotBlank(groups = CreatePatientValidationGroup.class,
			message = "Registred date is required")
	private String registredDate;
}
