package com.amazon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productid;
	@JoinColumn(name = "userid", nullable = false)
	private Long userid;
	private String name;
	private String brand;
	private Double price;
	private String description;
	private String category;
	private Integer quantity;
	private String email;
	private Long contact;
	private String address;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] image;
	@Column(columnDefinition = "varchar(255) default 'Pending'")
	private String status;
	private String buyorcart;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getProductid() {
		return this.productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}


	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContact() {
		return this.contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyorcart() {
		return this.buyorcart;
	}

	public void setBuyorcart(String buyorcart) {
		this.buyorcart = buyorcart;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
