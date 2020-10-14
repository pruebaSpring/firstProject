package com.daily.sale.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service
public class UserService {

	public boolean identifier(String nameUser, String passwordUser) {
		String user = "admin";
		String pass = "qFqW2Xpukwpzb5qg";
		String host = "clusterspringexample.q1vcv.mongodb.net";
		String dbName = "dbTEST";
		String port = "27017";
		String uriComplete= "mongodb+srv://"+user+":"+pass+"@"+host+"/"+dbName+"?retryWrites=true&w=majority";

		MongoClientURI uri = new MongoClientURI(uriComplete);
		MongoClient mongo = new MongoClient(uri);
		MongoDatabase mongoDatabase = mongo.getDatabase("dbTEST");
		MongoCollection<Document> collection = mongoDatabase.getCollection("USERS");
		System.out.println("Connect to database successfully");
		
		Bson filter = Filters.and(
				Filters.in("User", nameUser),
				Filters.in("Pass", passwordUser)
				);
		
		MongoCursor<Document> result = collection.find(filter).iterator();
		
		if(result.hasNext())
			return true;
		
		return false;
	}
}
