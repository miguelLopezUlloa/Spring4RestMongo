package com.mikauran.springRest.model;

/**
 * Bean with setters/getters for use with MongoDb
 */
public class Product {	
	
	private long id;
	private String name;
	private String title;
	private String description;
	private String imageUrl;
	private double price;
	
	public Product(){
		id=0;
	}
	
	public Product(long id, String name, String title, String description, String imageUrl, double price) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", title=" + title
				+ ", description=" + description + ", price=" + price + " ]";
	}
	
}
