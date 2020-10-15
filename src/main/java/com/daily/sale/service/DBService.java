package com.daily.sale.service;

import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Service
public class DBService {

	public MongoClient getMongoClient() {
		String user = "admin";
		String pass = "qFqW2Xpukwpzb5qg";
		String host = "clusterspringexample.q1vcv.mongodb.net";
		String dbName = "dbTEST";
		String port = "27017";
		String uriComplete= "mongodb+srv://"+user+":"+pass+"@"+host+"/"+dbName+"?retryWrites=true&w=majority";

		MongoClientURI uri = new MongoClientURI(uriComplete);
		MongoClient mongo = new MongoClient(uri);
		
		return mongo;
	}
}
