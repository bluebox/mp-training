package com.redisCustomer.rediscustomer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface CustomerRepo extends CrudRepository<Customer,String>{
	

}
