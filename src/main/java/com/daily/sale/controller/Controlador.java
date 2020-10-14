package com.daily.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daily.sale.interfaceService.IPersonaService;
import com.daily.sale.modelo.Persona;
import com.daily.sale.modelo.User;
import com.daily.sale.service.UserService;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private IPersonaService service;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
//		List<Persona> personas = service.listar();
//		model.addAttribute("personas", personas);
		return "login";
	}
	
	@PostMapping("/login")
//	public String login(@ModelAttribute(value="user") User user) {
	public String login(Model model, String name, String password) {
		if(userService.identifier(name, password)) {
			User user = new User(name, password);
			model.addAttribute("name", user.getName());
			return "index";
		}
		model.addAttribute("invalidCredentials", true);
		return "login";
	}
	
	@GetMapping("/signOut")
	public String signOut() {
		return "login";
	}
	
	@GetMapping("/index")
	public String dashboard(Model model) {
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
