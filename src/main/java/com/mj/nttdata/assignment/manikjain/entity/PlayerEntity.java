package com.mj.nttdata.assignment.manikjain.entity;

import java.io.Serializable;

import org.hibernate.annotations.Immutable;

import com.mj.nttdata.assignment.manikjain.enums.Country;
import com.mj.nttdata.assignment.manikjain.enums.MatchType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Players")
@Data
public class PlayerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Immutable
	private String id;
	
	@Column(name = "rank", nullable = true)
	private int rank;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Country country;
	
	@Column(name = "rating", nullable = true)
	private int rating;
	
	@Enumerated(EnumType.STRING)
	private MatchType matchType;

}