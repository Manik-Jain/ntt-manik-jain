package com.mj.nttdata.assignment.manikjain.enums;

public enum Country {
	
	IND("INDIA"),
	AUS("AUSTRALIA"),
	ENG("ENGLAND"),
	NZL("NEW ZEALAND"),
	PAK("PAKISTAN");
	
	private final String country;
	
	Country(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}	
}
