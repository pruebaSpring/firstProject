package com.daily.sale.controller;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class test {


	public static void main(String[] args) {

		String user = "admin";
		String pass = "qFqW2Xpukwpzb5qg";
		String host = "clusterspringexample.q1vcv.mongodb.net";
		String dbName = "dbTEST";
		String port = "27017";
		String uriComplete= "mongodb+srv://"+user+":"+pass+"@"+host+"/"+dbName+"?retryWrites=true&w=majority";

		//Conecction
		MongoClientURI uri = new MongoClientURI(uriComplete);
		MongoClient mongo = new MongoClient(uri);
		MongoDatabase mongoDatabase = mongo.getDatabase("dbTEST");
		MongoCollection<Document> collection = mongoDatabase.getCollection("LOTS");
		System.out.println("Connect to database successfully");


		System.out.println("Process init: Inserción de documento");
		Document document = new Document("name", "Luis").
				append("email", "lolaya@efact.pe").
				append("twitter", "lolaya@twitt.pe").
				append("location", new Document("city", "Villa Salvaje").append("zip", 12345));
		collection.insertOne(document);
		System.out.println("Documento agregado");
	}



}
