package testToMongoAtlas;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;




public class PruebaSpring {
	
		/**
	     * Main del proyecto.
	     * @param args
		 * @throws UnknownHostException 
	     */
	    public static void main(String[] args) throws UnknownHostException {
	        System.out.println("Prueba conexión MongoDB");
	        crearConexion();
	 
//	        if (mongo != null) {
//	            System.out.println("Lista de bases de datos: ");
//	            printDatabases(mongo);
//	 
//	        } else {
//	            System.out.println("Error: Conexión no establecida");
//	        }
	    }
	 
	    /**
	     * Clase para crear una conexión a MongoDB.
	     * @return MongoClient conexión
	     */
		private static void crearConexion() throws UnknownHostException {
//	        ServerAddress serverAddress = new ServerAddress("clusterspringexample-shard-00-02.q1vcv.mongodb.net",27017);  
//            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
//            addrs.add(serverAddress);  
//            MongoCredential credential = MongoCredential.createScramSha1Credential("admin","dbTEST","aqafdhb".toCharArray());  
//            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
//            credentials.add(credential); 
            MongoClientURI uri = new MongoClientURI("mongodb+srv://admin:aqafdhb@clusterspringexample.q1vcv.mongodb.net/dbTEST?retryWrites=true&w=majority");
            
            
//	        MongoCredential credential = MongoCredential.createScramSha1Credential("admin","dbTEST","aqafdhb".toCharArray());
//	        mongo = new MongoClient(new ServerAddress("clusterspringexample-shard-00-02.q1vcv.mongodb.net", 27017), Arrays.asList(credential));
            
            MongoClient mongo = new MongoClient(uri);
//            MongoClient mongo = new MongoClient(addrs, credentials);
            MongoDatabase mongoDatabase = mongo.getDatabase("dbTEST");
            MongoCollection<Document> collection = mongoDatabase.getCollection("TRANSACTION");
            System.out.println(collection);
            System.out.println("Connect to database successfully");  
            FindIterable<Document> findIterable = collection.find();  
            MongoCursor<Document> mongoCursor = findIterable.iterator();  
            List<Document> documents = new ArrayList<>();
			while (mongoCursor.hasNext()) {
				Document document = mongoCursor.next();
				documents.add(document);
			}
	        for (Document document : documents) {
	            System.out.println("Document: " + document);
	        }
	        
	        
	        System.out.println("Inserción de documento");
	        ArrayList<String> myHobbies = new ArrayList<String>();
	        
	        myHobbies.add("music");
	        myHobbies.add("movies");
	        
	        Document document = new Document("name", "John").
	          append("email", "john@doe.com").
	          append("twitter", "johndoe").
	          append("hobbies", myHobbies).
	          append("location", new Document("city", "nowhere").append("zip", 12345));
	        
	        collection.insertOne(document);
	        
	        System.out.println("Documento agregado");
	    	
	    }
	 
	    /**
	     * Clase que imprime por pantalla todas las bases de datos MongoDB.
	     * @param mongo conexión a MongoDB
	     */
	    private static void printDatabases(MongoClient mongo) {
//			List<String> dbs = mongo.getDatabaseNames();
	    	MongoDatabase s = mongo.getDatabase("dbTEST");
	    	MongoCollection<Document> c = s.getCollection("TRANSACTION");
	    	
	    	
	    	Document filter = new Document("Authorization.UserParty.PartyIdentification.ID", "20551093035");
			
	    	List<Document> documents = new ArrayList<>();
	    	MongoCursor<Document> result = c.find(filter).iterator();
			while (result.hasNext()) {
				Document document = result.next();
				documents.add(document);
			}
	        for (Document document : documents) {
	            System.out.println(" - " + document);
	        }
	    }
		
}
