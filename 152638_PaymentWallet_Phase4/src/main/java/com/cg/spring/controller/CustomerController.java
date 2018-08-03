package com.cg.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.spring.beans.Customer;
import com.cg.spring.exception.InsufficientBalanceException;
import com.cg.spring.service.IPaymentService;

@RestController
public class CustomerController {
	@Autowired
	private IPaymentService service;

	@RequestMapping("/customer")
	public List<Customer> Products() {
		return service.getAllCustomer();
	}

	@RequestMapping("/customer/{mobileNumber}")
	public Customer getCustomerDetails(@PathVariable String mobileNumber) {
		return service.getCustomerDetails(mobileNumber);
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public void addCustomer(@RequestBody Customer newCustomer) {
		service.addCustomer(newCustomer);
	}

	@RequestMapping(value = "/customer/{mobileNumber}/deposit/{depositableAmount}", method = RequestMethod.PUT)
	public void depositMoney(@PathVariable String mobileNumber, @PathVariable Double depositableAmount) {
		service.depositMoney(mobileNumber, depositableAmount);
	}

	@RequestMapping(value = "/customer/{mobileNumber}/withdraw/{withdrawableAmount}", method = RequestMethod.PUT)
	public void withdrawMoney(@PathVariable String mobileNumber,@PathVariable Double withdrawableAmount) {
		try {
			service.withdrawMoney(mobileNumber, withdrawableAmount);
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/customer/{mobileNumber}/print")
	public String printTransaction(@PathVariable String mobileNumber) {
		return service.printTransaction(mobileNumber);

	}

	@RequestMapping(value = "/customer/{SmobileNumber}/{RecmobileNumber}/{transferableAmount}", method = RequestMethod.PUT)
	public void fundTransfer(@PathVariable String SmobileNumber, @PathVariable String RecmobileNumber,
			@PathVariable double transferableAmount)  {
		try {
			service.fundTransfer(SmobileNumber, RecmobileNumber, transferableAmount);
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
