package com.daily.sale.service;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.daily.sale.constants.IDBCollections;
import com.daily.sale.constants.IDatabases;
import com.daily.sale.modelo.User;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service
public class UserService {

	public User identifier(String nameUser, String passwordUser, MongoClient mongoClient) {
//		String user = "admin";
//		String pass = "qFqW2Xpukwpzb5qg";
//		String host = "clusterspringexample.q1vcv.mongodb.net";
//		String dbName = "dbTEST";
//		String port = "27017";
//		String uriComplete= "mongodb+srv://"+user+":"+pass+"@"+host+"/"+dbName+"?retryWrites=true&w=majority";
//
//		MongoClientURI uri = new MongoClientURI(uriComplete);
//		MongoClient mongo = new MongoClient(uri);
		MongoDatabase mongoDatabase = mongoClient.getDatabase(IDatabases.DB_TEST);
		MongoCollection<Document> collection = mongoDatabase.getCollection(IDBCollections.USERS);
		System.out.println("Connect to database successfully");
		
		Bson filter = Filters.and(
				Filters.in("User", nameUser),
				Filters.in("Pass", passwordUser)
				);
		
		MongoCursor<Document> result = collection.find(filter).iterator();
		
		User user = null;
		if(result.hasNext()) {
			Document document = result.next();
			user = new User(document.getString("User"), document.getString("Pass"), document.get("UserType", new Document()).getString("TypeCode"));
			return user;
		}
		
		return user;
	}
}
