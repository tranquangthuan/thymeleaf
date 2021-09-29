package com.example.java.thymeleaf.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.java.thymeleaf.model.PersonForm;
import com.example.java.thymeleaf.model.Product;
import com.example.java.thymeleaf.repository.CountryRepository;

@Controller
public class HomeController {

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping(value = { "/", "/home" })
	public String home(Model model) {
		model.addAttribute("variable1", "value of variable 1 aaaa using some character ;,.");
		model.addAttribute("variable2", "Variabe 2");
		model.addAttribute("script", "<script>\r\n" + "alert(\"Welcome\");\r\n" + "</script>");
		String[] flowers = { "A", "B", "C" };
		model.addAttribute("flowers", flowers);
		model.addAttribute("welcomeMsg", "Welcome to our <b>fantastic</b> grocery store!");

		List<Product> products = Arrays.asList(new Product("Nokia", 1, "Nokia mobile"),
				new Product("Iphone", 12, "Iphone"), new Product("Blackberry", 100, "Blackberry mobile"),
				new Product("Samsung", 1, "Samsung mobile"));

		model.addAttribute("products", products);
		model.addAttribute("name", "thuan");
		model.addAttribute("id", "11");
		model.addAttribute("type", "good");

		return "index";
	}

	@GetMapping(value = { "/select1" })
	public String select1(Model model) {

		model.addAttribute("personForm", new PersonForm());
		model.addAttribute("countries", countryRepository.findAll());
		model.addAttribute("test", "tesst value");
		model.addAttribute("allSkills", Arrays.asList("Java", ".Net", "GoLang", "C++"));
		model.addAttribute("allGenders", Arrays.asList("Male", "Female", "Other"));

		return "select1";
	}

	@PostMapping(value = { "/submit" })
	public String submit(Model model, @ModelAttribute(name = "personForm") PersonForm personForm) {

		System.out.println(personForm.getFullName());
		System.out.println(personForm.getCountryId());
		System.out.println(personForm.isMale());
		System.out.println(personForm.getSkills().toString());
		System.out.println(personForm.isMale());
		model.addAttribute("countries", countryRepository.findAll());
		model.addAttribute("allSkills", Arrays.asList("Java", ".Net", "GoLang", "C++"));
		model.addAttribute("allGenders", Arrays.asList("Male", "Female", "Other"));

		return "select1";
	}

	@GetMapping(value = { "/select2" })
	public String select2(Model model) {

		model.addAttribute("personForm", new PersonForm());
		model.addAttribute("countryMap", countryRepository.getMapsCoutries());

		return "select2";
	}

	@GetMapping(value = { "/custom" })
	public String custom(Model model) {
		return "custom";
	}

	@ModelAttribute("planets")
	public List<String> populatePlanets() {
		return Arrays
				.asList(new String[] { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" });
	}
}
