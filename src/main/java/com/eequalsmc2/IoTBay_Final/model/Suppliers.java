package com.eequalsmc2.IoTBay_Final.model;

public class Suppliers {
	private Integer id ;
	private String name;
	private String phone;
	private String email;
	private String company;
	private String status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Suppliers [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", company="
				+ company + ", status=" + status + "]";
	}
	public Suppliers(Integer id, String name, String phone, String email, String company, String status) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.status = status;
	}
	public Suppliers( String name, String phone, String email, String company, String status) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.status = status;
	}
	public Suppliers() {
		
	}
}
