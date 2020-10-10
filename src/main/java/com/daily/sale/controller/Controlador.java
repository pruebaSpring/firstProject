package com.daily.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daily.sale.interfaceService.IPersonaService;
import com.daily.sale.modelo.Persona;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private IPersonaService service;
	
	@GetMapping("/index")
	public String listar(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "index";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "about";
	}

	@GetMapping("/gallery")
	public String gallery(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "gallery";
	}

	@GetMapping("/blog")
	public String blog(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "blog";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "contact";
	}
}
