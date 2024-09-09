package com.mj.nttdata.assignment.manikjain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	
	private String id;
	
	private int rank;
	
	private String firstName;
	
	private String lastName;
	
	private String country;
	
	private int rating;
	
	private String matchType;

}
