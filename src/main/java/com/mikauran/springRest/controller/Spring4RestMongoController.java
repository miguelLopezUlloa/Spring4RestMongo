package com.mikauran.springRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mikauran.springRest.connectors.MongoDBConnector;
import com.mikauran.springRest.model.Product;
import com.mikauran.springRest.service.ProductService;

import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@RestController
public class Spring4RestMongoController {
	
	@Autowired
	ProductService productService; //Service to retrieval and work using data
	
	@RequestMapping(value = "/tables/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listTables(){
		List<String> lstTables=null;
    	MongoDBConnector cone = new MongoDBConnector();
		MongoClient mongo = cone.crearConexion();
	
	    if (mongo != null) {
	        lstTables=cone.returnDatabases(mongo);
	        
	        if(lstTables.isEmpty()){
	        	return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
	        }
	    } else {
	        System.out.println("Error: Unable to connect into MongoDb.");
	    }
	    
	    
	    return new ResponseEntity<List<String>>(lstTables, HttpStatus.OK);
	}

	
	//-------------------Retrieve All Products--------------------------------------------------------
	
		@RequestMapping(value = "/products/", method = RequestMethod.GET)
		public ResponseEntity<List<Product>> listAllProducts() {
			List<Product> products = productService.findAllProducts();
			if(products.isEmpty()){
				return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		
		
		//-------------------Insert Product--------------------------------------------------------
		
		@RequestMapping(value = "/product/", method = RequestMethod.POST)
		public ResponseEntity<Void> insertProduct(@RequestBody Product product, 	UriComponentsBuilder ucBuilder) {
			System.out.println("Inserting Product " + product.getName());

			if (productService.isProductExist(product)) {
				System.out.println("The product with name " + product.getName() + " already exist");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			productService.insertProduct(product);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		
		
		//------------------- Delete a Product --------------------------------------------------------
		
		@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
			System.out.println("Fetching & Deleting Product with id " + id);

			Product product = productService.findById(id);
			if (product == null) {
				System.out.println("Unable to delete. Product with id " + id + " not found");
				return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			}

			productService.deleteProductById(id);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}


}
