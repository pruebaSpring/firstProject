package com.daily.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daily.sale.interfaceService.IPersonaService;
import com.daily.sale.modelo.Lots;
import com.daily.sale.modelo.Persona;
import com.daily.sale.modelo.User;
import com.daily.sale.service.LotService;
import com.daily.sale.service.UserService;

@Controller
@RequestMapping
public class Controlador {
	
	private static User user;

	@Autowired
	private IPersonaService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LotService lotService;

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
			user = new User(name, password);
			model.addAttribute("name", user.getName());
			List<List<Lots>> lots = lotService.getAll();
			model.addAttribute("reservedLots", lots.get(0));
			model.addAttribute("soldLots", lots.get(1));
			model.addAttribute("availableLots", lots.get(2));
			return "index";
		}
		model.addAttribute("invalidCredentials", true);
		return "login";
	}
	
	@GetMapping("/signOut")
	public String signOut() {
		user = null;
		return "login";
	}
	
	@GetMapping("/index")
	public String dashboard(@RequestParam(defaultValue = "", name = "name", required = false) String name, Model model) {
		List<List<Lots>> lots = lotService.getAll();
		model.addAttribute("reservedLots", lots.get(0));
		model.addAttribute("soldLots", lots.get(1));
		model.addAttribute("availableLots", lots.get(2));
		model.addAttribute("name", name);
		return "index";
	}
	
	@GetMapping("/reservados")
	public String reservados(@RequestParam(defaultValue = "", name = "name", required = false) String name, Model model) {
		List<Lots> reservedLots = lotService.getReserveds();
		model.addAttribute("reservedLots", reservedLots);
		model.addAttribute("name", name);
		return "reservados";
	}

	@GetMapping("/vendidos")
	public String vendidos(@RequestParam(defaultValue = "", name = "name", required = false) String name, Model model) {
		List<Lots> soldLots = lotService.getSolds();
		model.addAttribute("soldLots", soldLots);
		model.addAttribute("name", name);
		return "vendidos";
	}
	
	@GetMapping("/disponibles")
	public String disponibles(@RequestParam(defaultValue = "", name = "name", required = false) String name, Model model) {
		List<Lots> availableLots = lotService.getAvailables();
		model.addAttribute("availableLots", availableLots);
		model.addAttribute("name", name);
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
