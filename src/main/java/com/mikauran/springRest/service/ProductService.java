package com.mikauran.springRest.service;

import java.util.List;
import com.mikauran.springRest.model.Product;

public interface ProductService {
	Product findById(long id);
	Product findByName(String name);
	List<Product> findAllProducts();
	void insertProduct(Product product);
	//void updateProduct(Product product);
	void deleteProductById(long id);

	public boolean isProductExist(Product prods);
	
}
