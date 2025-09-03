package com.redisCustomer.rediscustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired 
    private CustomerRepo repo;
	@CachePut(value = "Customer", key = "#customer.id")
	public Customer addCustomer(Customer customer){
	    return repo.save(customer);
	}
  @Cacheable("posts")

  public List<Customer> getAllCustomers(){

      List<Customer> allCustomer = new ArrayList<>();
      repo.findAll().forEach(allCustomer::add);
      return allCustomer;
  }

  public Customer getCustomerById(int id){

      Optional<Customer> optionalCustomer
          = repo.findById(String.valueOf(id));
      return optionalCustomer.orElse(null);
  }

    
  public Customer updateCustomerById(int id,
                                     Customer newCustomer){
    
      Optional<Customer> existingCustomer
          = repo.findById(String.valueOf(id));

      if (existingCustomer.isPresent()) {
          Customer updatedCustomer
              = existingCustomer.get();

          updatedCustomer.setName(newCustomer.getName());
          updatedCustomer.setPhone(newCustomer.getPhone());
          updatedCustomer.setEmail(newCustomer.getEmail());

          repo.deleteById(String.valueOf(id));
          return repo.save(updatedCustomer);
      }

      return null;
  }
  @CacheEvict(value = "Customer", key = "#id")

  public void deleteCustomerById(int id){
      repo.deleteById(String.valueOf(id));
  }

}
