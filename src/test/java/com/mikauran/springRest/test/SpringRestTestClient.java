package com.mikauran.springRest.test;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mikauran.springRest.connectors.MongoDBConnector;
import com.mikauran.springRest.model.Product;

import org.springframework.web.client.RestTemplate;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/Spring4RestMongo";
	//public static final String REST_SERVICE_URI = "http://localhost:8080";
	
	private static void openConectMongo(){
    	MongoDBConnector cone = new MongoDBConnector();
	    System.out.println("MongoDB Connection..opened");
	    
		MongoClient mongo = cone.crearConexion();
	
	    if (mongo != null) {
	        System.out.println("MongoDatabases list: ");
	        cone.printDatabases(mongo);
	        
	     // Database ConnectionConexión
	     DB db = mongo.getDB("test");

	     //Get collection and use this to launch the actions and operations
	     DBCollection collection = db.getCollection("product");
	     
	     // "Count items" -> Launch an count method with all the documents detected
		 int numDocumentos = (int) collection.getCount();
		 System.out.println("Documents now are: " + numDocumentos + "\n");
	
	    } else {
	        System.out.println("Error: Unable to connect MongoDb.");
	    }
    }
	
	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllProducts(){
		System.out.println("Testing listAllProducts API-----------");
		String endPoint=REST_SERVICE_URI + "/products/";
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> productsMap = restTemplate.getForObject(endPoint, List.class);
		
		if(productsMap!=null){
			for(LinkedHashMap<String, Object> map : productsMap){
	            System.out.println("Products : id="+map.get("id")+", Name="+map.get("name")+", Title="+map.get("title")
	            +", Description="+map.get("description") + ", Price=" + map.get("price"));
	        }
		}else{
			System.out.println("Product not found ----------");
		}
	}
	
    
    /* POST */
    private static void insertProduct() {
		System.out.println("Testing insert using Product API----------");
    	RestTemplate restTemplate = new RestTemplate();
        Product product = new Product(0,"Mouse","Mouse Wireless","Human tool","http://mouse.com", 34.0);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/product/", product, Product.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    public static void main(String args[]){
       openConectMongo();
       
       listAllProducts();
       //insertProduct();
		
    }
}