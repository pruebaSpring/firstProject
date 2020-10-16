package com.daily.sale.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.daily.sale.constants.IDBCollections;
import com.daily.sale.constants.IDatabases;
import com.daily.sale.modelo.Lots;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service
public class LotService {

	public List<List<Lots>> getAll(MongoClient mongoClient, String type) {
		List<Lots> reservedList = new ArrayList<>();
		List<Lots> soldList = new ArrayList<>();
		List<Lots> availableList = new ArrayList<>();
		MongoDatabase mongoDatabase = mongoClient.getDatabase(IDatabases.DB_TEST);
		MongoCollection<Document> collection = mongoDatabase.getCollection(IDBCollections.LOTS);
		System.out.println("Connect to database successfully");
		
		MongoCursor<Document> result;
		
		if(!type.equalsIgnoreCase("00")) {
			Bson filter = Filters.and(
					Filters.eq("UserType", type)
					);
			result = collection.find(filter).iterator();
		}
		else {
			result = collection.find().iterator();
		}
		
		List<List<Lots>> lots = new ArrayList<>();
		while (result.hasNext()) {
			Lots lot = new Lots();
			Document document = result.next();
			String status = document.get("Status", new Document()).getString("Status");
			lot.setAmount(document.get("Information", new Document()).get("Price", new Document()).getDouble("Amount"));
			lot.setMeasure(document.get("Information", new Document()).getString("Measure"));
			lot.setLocation(document.get("Information", new Document()).getString("Location"));
			
			
			switch (status) {
			case "Reserved":
				reservedList.add(lot);
				break;
			case "Sold":
				soldList.add(lot);
				break;
			case "Available":
				availableList.add(lot);
				break;

			default:
				break;
			}
		}
		lots.add(reservedList);
		lots.add(soldList);
		lots.add(availableList);

		return lots;
	}

	public List<Lots> getAvailables(MongoClient mongoClient, String type) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(IDatabases.DB_TEST);
		MongoCollection<Document> collection = mongoDatabase.getCollection(IDBCollections.LOTS);
		System.out.println("Connect to database successfully");
		
		Bson filter;
		
		if(!type.equalsIgnoreCase("00")) {
			filter = Filters.and(
					Filters.eq("Status.Status", "Available"),
					Filters.eq("UserType", type)
					);
		}
		else {
			filter = Filters.and(
					Filters.eq("Status.Status", "Available")
					);
		}
		
		MongoCursor<Document> result = collection.find(filter).iterator();
		
		List<Lots> lots = new ArrayList<>();
		while (result.hasNext()) {
			Lots lot = new Lots();
			Document document = result.next();
			lot.setAmount(document.get("Information", new Document()).get("Price", new Document()).getDouble("Amount"));
			lot.setMeasure(document.get("Information", new Document()).getString("Measure"));
			lot.setLocation(document.get("Information", new Document()).getString("Location"));
			lots.add(lot);
		}

		return lots;
	}

	public List<Lots> getSolds(MongoClient mongoClient, String type) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(IDatabases.DB_TEST);
		MongoCollection<Document> collection = mongoDatabase.getCollection(IDBCollections.LOTS);
		System.out.println("Connect to database successfully");
		
		Bson filter;
		
		if(!type.equalsIgnoreCase("00")) {
			filter = Filters.and(
					Filters.eq("Status.Status", "Sold"),
					Filters.eq("UserType", type)
					);
		}
		else {
			filter = Filters.and(
					Filters.eq("Status.Status", "Sold")
					);
		}
		
		MongoCursor<Document> result = collection.find(filter).iterator();
		
		List<Lots> lots = new ArrayList<>();
		while (result.hasNext()) {
			Lots lot = new Lots();
			Document document = result.next();
			lot.setAmount(document.get("Information", new Document()).get("Price", new Document()).getDouble("Amount"));
			lot.setMeasure(document.get("Information", new Document()).getString("Measure"));
			lot.setLocation(document.get("Information", new Document()).getString("Location"));
			lots.add(lot);
		}

		return lots;
	}

	public List<Lots> getReserveds(MongoClient mongoClient, String type) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(IDatabases.DB_TEST);
		MongoCollection<Document> collection = mongoDatabase.getCollection(IDBCollections.LOTS);
		System.out.println("Connect to database successfully");
		
		Bson filter;
		
		if(!type.equalsIgnoreCase("00")) {
			filter = Filters.and(
					Filters.eq("Status.Status", "Reserved"),
					Filters.eq("UserType", type)
					);
		}
		else {
			filter = Filters.and(
					Filters.eq("Status.Status", "Reserved")
					);
		}
		
		MongoCursor<Document> result = collection.find(filter).iterator();
		
		List<Lots> lots = new ArrayList<>();
		while (result.hasNext()) {
			Lots lot = new Lots();
			Document document = result.next();
			lot.setAmount(document.get("Information", new Document()).get("Price", new Document()).getDouble("Amount"));
			lot.setMeasure(document.get("Information", new Document()).getString("Measure"));
			lot.setLocation(document.get("Information", new Document()).getString("Location"));
			lots.add(lot);
		}

		return lots;
	}
}
