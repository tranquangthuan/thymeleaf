package com.example.java.thymeleaf.model;

import java.util.List;

public class PersonForm {
	private String fullName;
	private Long countryId;
	private boolean isMale;
	private List<String> skills;
	private String gender;

	public PersonForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonForm(String fullName, Long countryId, boolean isMale, List<String> skills) {
		super();
		this.fullName = fullName;
		this.countryId = countryId;
		this.isMale = isMale;
		this.skills = skills;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
