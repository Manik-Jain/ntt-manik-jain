package com.mj.nttdata.assignment.manikjain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePlayer {
	
	@NotBlank(message = "First name cannot be empty")
	private String firstName;
	
	@NotBlank(message = "Last name cannot be empty")
	private String lastName;
	
	@NotBlank(message = "Player country cannot be empty")
	private String country;
	
	@NotBlank(message = "Player match type cannot be empty")
	private String matchType;

}
