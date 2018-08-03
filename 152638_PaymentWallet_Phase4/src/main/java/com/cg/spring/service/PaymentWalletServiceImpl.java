package com.cg.spring.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.spring.beans.Customer;
import com.cg.spring.exception.IPaymentWalletException;
import com.cg.spring.exception.InsufficientBalanceException;
import com.cg.spring.repo.IPaymentRepo;

@Service
public class PaymentWalletServiceImpl implements IPaymentService {

	@Autowired
	private IPaymentRepo repo = null;

	@Override
	public boolean addCustomer(Customer newCustomer) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
		String strDate = sdf.format(date);
		String trans = "Account Created on \t" + strDate + "\nAmount\tType\t\t\tDate\t\tRemaining Balance"
				+ "\n---------------------------------------------------------------------------------------\n";
		newCustomer.setTransaction(trans);
		repo.save(newCustomer);
		return true;
	}

	@Override
	public void depositMoney(String mobileNumber, Double depositableAmount) {
		Optional<Customer> cust = repo.findById(mobileNumber);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
			String strDate = sdf.format(date);
			String statement = customer.getTransaction().toString() + "\n\u20B9" + depositableAmount + "\tDeposited\t"
					+ strDate + "\t\u20B9" + customer.getBalance();
			customer.setTransaction(statement);
			customer.setBalance(customer.getBalance() + depositableAmount);
			repo.save(customer);
		}

	}

	@Override
	public void withdrawMoney(String mobileNumber, Double withdrawableAmount) throws InsufficientBalanceException {
		/*
		 * Checking Balance if sufficient for Withdrawal or not
		 */

		Optional<Customer> cust = repo.findById(mobileNumber);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			double newBalance = customer.getBalance() - withdrawableAmount;
			if (newBalance >= 1000) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
				String strDate = sdf.format(date);
				String statement = customer.getTransaction().toString() + "\n\u20B9" + withdrawableAmount
						+ "\tWithdrawn\t" + strDate + "\t\u20B9" + customer.getBalance();
				customer.setTransaction(statement);
				customer.setBalance(customer.getBalance() - withdrawableAmount);
				repo.save(customer);
			} else {
				throw new InsufficientBalanceException(IPaymentWalletException.MESSAGE5);
			}
		}
	}

	@Override
	public String printTransaction(String mobileNumber) {
		Optional<Customer> cust = repo.findById(mobileNumber);
		Customer customer = null;
		if (cust.isPresent()) {
			customer = cust.get();
		}
		return customer.getTransaction();
	}

	@Override
	public void fundTransfer(String SmobileNumber, String RecmobileNumber, Double transferableAmount)
			throws InsufficientBalanceException {
		/*
		 * Checking Balance if sufficient for Withdrawal or not PROJECT BY- VIKASH
		 * KUMAR(EMPID: 152638)
		 */
		Optional<Customer> cust = repo.findById(SmobileNumber);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			double newBalance = customer.getBalance() - transferableAmount;
			if (newBalance >= 1000) {
				customer.setBalance(customer.getBalance() - transferableAmount);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
				String strDate = sdf.format(date);
				String statement = customer.getTransaction().toString() + "\n\u20B9" + transferableAmount
						+ "\tTransfered To\t" + strDate + "\t\u20B9" + customer.getBalance();
				customer.setTransaction(statement);
				repo.save(customer);

				Customer receiverCustomer = repo.findById(RecmobileNumber).get();
				String statement2 = receiverCustomer.getTransaction().toString() + "\n\u20B9" + transferableAmount
						+ "\tTransfered from\t" + strDate + "\t\u20B9" + receiverCustomer.getBalance();
				receiverCustomer.setTransaction(statement2);
				receiverCustomer.setBalance(receiverCustomer.getBalance() + transferableAmount);
				repo.save(receiverCustomer);
			} else {
				throw new InsufficientBalanceException(IPaymentWalletException.MESSAGE5);
			}
		}

	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Customer getCustomerDetails(String mobileNumber) {
		return repo.findById(mobileNumber).get();
	}

}
