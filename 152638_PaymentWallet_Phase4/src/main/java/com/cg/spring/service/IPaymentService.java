package com.cg.spring.service;

import java.util.List;

import com.cg.spring.beans.Customer;
import com.cg.spring.exception.InsufficientBalanceException;

public interface IPaymentService {
	public boolean addCustomer(Customer newCustomer);

	public void depositMoney(String mobileNumber, Double depositableAmount);

	public void withdrawMoney(String mobileNumber, Double withdrawableAmount) throws InsufficientBalanceException;

	public String printTransaction(String mobileNumber);

	public void fundTransfer(String SmobileNumber, String RecmobileNumber, Double transferableAmount) throws InsufficientBalanceException;

	public List<Customer> getAllCustomer();

	public Customer getCustomerDetails(String mobileNumber);

}
