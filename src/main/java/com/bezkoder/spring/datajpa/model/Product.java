package com.bezkoder.spring.datajpa.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "products")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;


@Column(name = "name")
private String name;

@Column(name = "model")
private String model;

@Column(name = "colour")
private String colour;

@Column(name = "price")
private float price;


public Product() {

}

public Product(String name, String model,String colour, float price) {
	this.name = name;
	this.model = model;
	this.colour = colour;
	this.price = price;
}

public String getColour() {
	return colour;
}

public void setColour(String colour) {
	this.colour = colour;
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

public String getModel() {
	return model;
}

public void setModel(String model) {
	this.model = model;
}

public float getPrice() {
	return price;
}

public void setPrice(float Price) {
	this.price = price;
}

@Override
public String toString() {
	return "Product [id=" + id + ", name=" + name + ", model=" + model + ", colour=" + colour + ", price=" + price + "]";
}


@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "categoryp_id", nullable = false)
@JsonIgnore
private Category categoryp;
}
