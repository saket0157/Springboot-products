package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "categoryp")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "type")
	private String type;

	@Column(name = "name")
	private String name;

	@Column(name = "stock")
	private boolean stock;

	public Category() {

	}

	public Category (String type, String name, boolean stock) {
		this.type = type;
		this.name = name;
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", name=" + name + ", stock=" + stock + "]";
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categoryp")
	private Product products ;
}
