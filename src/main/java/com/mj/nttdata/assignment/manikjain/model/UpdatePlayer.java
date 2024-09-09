package com.mj.nttdata.assignment.manikjain.model;

import java.util.Optional;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePlayer {
	
	@NotBlank(message = "Id cannot be empty")
	private String id;
	
	private Optional<@NotBlank(message = "First name cannot be empty") String> firstName = Optional.ofNullable("");
	
	private Optional<@NotBlank(message = "Last name cannot be empty") String> lastName = Optional.ofNullable("");
	
	private Optional<@NotBlank(message = "Player country cannot be empty") String> country =Optional.ofNullable("");
	
	private Optional<@NotBlank(message = "Player match type cannot be empty") String> matchType = Optional.ofNullable("");
	
	public Optional<
		@Min(value = 1, message = "Rank must be greater than or equal to 1") 
		@Max(value = Integer.MAX_VALUE - 8, message = "Rank must be lower than maximum number of players allowed by ICC")  
	Integer> rank = Optional.ofNullable(0);

	
	public Optional<
		@Min(value = 0, message = "Rating must be greater than or equal to 0")
		@Max(value = 1000, message = "Rating must be less than or equal to 1K") 
	Integer> rating = Optional.ofNullable(0);
}
