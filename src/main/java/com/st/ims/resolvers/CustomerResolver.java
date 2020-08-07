package com.st.ims.resolvers;

import org.springframework.stereotype.Component;

import com.st.ims.model.Customer;
import com.st.ims.repo.CustomerRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class CustomerResolver implements GraphQLMutationResolver{
	
	private final CustomerRepository customerRepo;
	
	public CustomerResolver(CustomerRepository customerRepo) {
		this.customerRepo=customerRepo;
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

}
