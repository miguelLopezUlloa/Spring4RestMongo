package com.mikauran.springRest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikauran.springRest.model.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	
	private static final AtomicLong counter = new AtomicLong();	
	private static List<Product> products;
	
	static{
		products= populateDummyProducts();
	}
	
	
	public Product findById(long id) {
		for(Product product : products){
			if(product.getId() == id){
				return product;
			}
		}
		return null;
	}

	public List<Product> findAllProducts() {
		return products;
	}
	
	public Product findByName(String name) {
		for(Product prod : products){
			if(prod.getName().equalsIgnoreCase(name)){
				return prod;
			}
		}
		return null;
	}
	
	public void insertProduct(Product prods) {
		prods.setId(counter.incrementAndGet());
		products.add(prods);
	}


	public void updateProduct(Product prods) {
		int index = products.indexOf(prods);
		products.set(index, prods);
	}
	
	
	public void deleteProductById(long id) {

		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext(); ) {
		    Product prods = iterator.next();
		    if (prods.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isProductExist(Product prods) {
		return findByName(prods.getName())!=null;
	}
	
	
	private static List<Product> populateDummyProducts(){
		List<Product> prods = new ArrayList<Product>();
		prods.add(new Product(counter.incrementAndGet(),"Glass","Beatiful Glass","glass","http://images.com", 4.0));
		prods.add(new Product(counter.incrementAndGet(),"Book","The Martian","Movie","http://theMartian.com", 15.87));
		prods.add(new Product(counter.incrementAndGet(),"iPhone","iPhone 6S","New device","http://apple.com", 4.0));
		
		return prods;
	}

	public void deleteAllUsers() {
		products.clear();
	}

}
