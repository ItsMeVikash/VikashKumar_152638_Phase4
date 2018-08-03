package com.cg.spring.service;

import com.cg.spring.exception.InvalidInputException;

public interface IPaymentServiceValidation {
	public boolean menuChoiceValidation(String choice) throws InvalidInputException;
	public boolean mobileNumberValidation(String mobileNumber) throws InvalidInputException;
	public boolean nameValidation(String name) throws InvalidInputException;
	public boolean amountValidation(String amount) throws InvalidInputException;
}
