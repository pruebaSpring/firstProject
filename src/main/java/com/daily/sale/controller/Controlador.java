package com.daily.sale.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daily.sale.interfaceService.IPersonaService;
import com.daily.sale.modelo.Persona;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private IPersonaService service;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "index";
	}
	
	@GetMapping("/reservados")
	public String reservados(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "reservados";
	}

	@GetMapping("/vendidos")
	public String vendidos(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "vendidos";
	}
	
	@GetMapping("/disponibles")
	public String disponibles(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "disponibles";
	}
	
	@GetMapping("/colors")
	public String colors(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "colors";
	}
	
//	@GetMapping("/about")
//	public String about(Model model) {
//		List<Persona> personas = service.listar();
//		model.addAttribute("personas", personas);
//		return "about";
//	}
//
//	@GetMapping("/gallery")
//	public String gallery(Model model) {
//		List<Persona> personas = service.listar();
//		model.addAttribute("personas", personas);
//		return "gallery";
//	}
//
//	@GetMapping("/blog")
//	public String blog(Model model) {
//		List<Persona> personas = service.listar();
//		model.addAttribute("personas", personas);
//		return "blog";
//	}
//
//	@GetMapping("/contact")
//	public String contact(Model model) {
//		List<Persona> personas = service.listar();
//		model.addAttribute("personas", personas);
//		return "contact";
//	}
//	
//	@GetMapping("/insertar")
//	public String insert(Model model) {
//		MongoClientURI uri = new MongoClientURI("mongodb+srv://admin:aqafdhb@clusterspringexample.q1vcv.mongodb.net/dbTEST?retryWrites=true&w=majority");
//        MongoClient mongo = new MongoClient(uri);
//        MongoDatabase mongoDatabase = mongo.getDatabase("dbTEST");
//        MongoCollection<Document> collection = mongoDatabase.getCollection("TRANSACTION");
//        System.out.println(collection);
//        System.out.println("Connect to database successfully");  
//        
//        System.out.println("Inserción de documento");
//        
//        Document document = new Document("name", "Luis").
//          append("email", "lolaya@efact.pe").
//          append("twitter", "lolaya@twitt.pe").
//          append("location", new Document("city", "Villa Salvaje").append("zip", 12345));
//        
//        collection.insertOne(document);
//        
//        System.out.println("Documento agregado");
//        
//        return "index";
//	}
}
