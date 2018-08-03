package com.cg.spring.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.spring.beans.Customer;
@Repository("customerRepo")
public interface IPaymentRepo extends CrudRepository<Customer, String>{


}
