package com.example.java.thymeleaf.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.java.thymeleaf.model.Country;

@Repository
public class CountryRepository {

	public List<Country> findAll() {
		List<Country> countries = Arrays.asList(new Country(1L, "VN", "Viet Nam"), new Country(2L, "US", "USA"),
				new Country(3L, "UK", "England"));
		return countries;
	}

	public Map<Long, Country> getMapsCoutries() {
		List<Country> countries = findAll();

		return countries.stream().collect(Collectors.toMap(Country::getId, c -> c));
	}
}
