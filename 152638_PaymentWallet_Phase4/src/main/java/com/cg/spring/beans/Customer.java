package com.cg.spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Customer {
	@Id
	@Column(length = 10)
	private String mobileNumber;
	@Column(length = 50)
	private String name;
	@Lob
	private String transaction;
	private Double balance;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String mobileNumber, String name, String transaction, Double balance) {
		super();
		this.mobileNumber = mobileNumber;
		this.name = name;
		this.transaction = transaction;
		this.balance = balance;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
