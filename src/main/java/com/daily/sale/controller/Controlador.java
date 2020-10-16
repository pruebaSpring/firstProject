package com.daily.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daily.sale.modelo.Lots;
import com.daily.sale.modelo.User;
import com.daily.sale.service.DBService;
import com.daily.sale.service.LotService;
import com.daily.sale.service.UserService;

@Controller
@RequestMapping
public class Controlador {

	private static User user;
	private static com.mongodb.MongoClient mongoClient;

	@Autowired
	private DBService dbService;

	//	@Autowired
	//	private IPersonaService service;

	@Autowired
	private UserService userService;

	@Autowired
	private LotService lotService;

	@GetMapping("/")
	public String index(Model model) {
		mongoClient = dbService.getMongoClient();
		//		List<Persona> personas = service.listar();
		//		model.addAttribute("personas", personas);
		return "login";
	}

	@PostMapping("/login")
	//	public String login(@ModelAttribute(value="user") User user) {
	public String login(Model model, String name, String password) {
		mongoClient = dbService.getMongoClient();
		user = userService.identifier(name, password, mongoClient);
		if(user != null) {
			model.addAttribute("name", user.getName());
			model.addAttribute("type", user.getType());
			List<List<Lots>> lots = lotService.getAll(mongoClient, user.getType());
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
		mongoClient = null;
		return "login";
	}

	@GetMapping("/index/{name}/{type}")
	public String dashboard(@PathVariable("name") String name, @PathVariable("type") String type, Model model) {
		List<List<Lots>> lots = lotService.getAll(mongoClient, type);
		model.addAttribute("reservedLots", lots.get(0));
		model.addAttribute("soldLots", lots.get(1));
		model.addAttribute("availableLots", lots.get(2));
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "index";
	}

	@GetMapping("/reservados/{name}/{type}")
	public String reservados(@PathVariable("name") String name, @PathVariable("type") String type, Model model) {
		List<Lots> reservedLots = lotService.getReserveds(mongoClient, type);
		model.addAttribute("reservedLots", reservedLots);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "reservados";
	}

	@GetMapping("/vendidos/{name}/{type}")
	public String vendidos(@PathVariable("name") String name, @PathVariable("type") String type, Model model) {
		List<Lots> soldLots = lotService.getSolds(mongoClient, type);
		model.addAttribute("soldLots", soldLots);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "vendidos";
	}

	@GetMapping("/disponibles/{name}/{type}")
	public String disponibles(@PathVariable("name") String name, @PathVariable("type") String type, Model model) {
		List<Lots> availableLots = lotService.getAvailables(mongoClient, type);
		model.addAttribute("availableLots", availableLots);
		model.addAttribute("name", name);
		model.addAttribute("type", type);
		return "disponibles";
	}

	@GetMapping("/data/{amount}")
	public String getData(@PathVariable("amount") Double amount, Model model) {
//		List<Lots> availableLots = lotService.getAvailables(mongoClient);
//		model.addAttribute("availableLots", availableLots);
//		model.addAttribute("amount", amount);
		return "vendidos :: modalContents";
	}

	@GetMapping("/registro/{name}/{type}")
	public String registro(@PathVariable("name") String name, @PathVariable("type") String type, Model model) {
		//		List<Persona> personas = service.listar();
		//		model.addAttribute("personas", personas);
		return "registro";
	}



	@PostMapping("/reserveLot")
	//	public String login(@ModelAttribute(value="user") User user) {
	public String reserveLot(Model model, String nameClient, String mailClient, String date, String operation) {


		System.out.println("nameClient: "+nameClient+"\n"
				+
				"mailClient: "+mailClient+"\n"+
				"date: "+date+"\n"+
				"operation: "+operation+"\n"
				);


		//		if(userService.identifier(name, password)) {
		//			user = new User(name, password);
		//			model.addAttribute("name", user.getName());
		//			List<List<Lots>> lots = lotService.getAll();
		//			model.addAttribute("reservedLots", lots.get(0));
		//			model.addAttribute("soldLots", lots.get(1));
		//			model.addAttribute("availableLots", lots.get(2));
		//			return "index";
		//		}



		//		model.addAttribute("invalidCredentials", true);
		return "disponibles";
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
