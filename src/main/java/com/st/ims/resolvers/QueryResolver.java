package com.st.ims.resolvers;

import org.springframework.stereotype.Component;

import com.st.ims.model.Customer;
import com.st.ims.repo.CustomerRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class QueryResolver implements GraphQLQueryResolver{
	
private final CustomerRepository customerRepo;
	
	public QueryResolver(CustomerRepository customerRepo) {
		this.customerRepo=customerRepo;
	}

	public Customer findById(int customerId) {
		return customerRepo.findById(customerId).orElse(null);
	}
}
