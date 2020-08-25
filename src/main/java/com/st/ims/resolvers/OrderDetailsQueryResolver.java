package com.st.ims.resolvers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.OrderDetails;
import com.st.ims.repo.OrderDetailsRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class OrderDetailsQueryResolver implements GraphQLQueryResolver{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OrderDetailsRepository orderDetailRepo;
      
	/* This method will return list of all OrderDetails objects. */
	public List<OrderDetails> findAllOrderDetails() {
		List<OrderDetails> orderDetailsList =  new ArrayList<>();
		try {
			orderDetailsList = orderDetailRepo.findAll();
			return orderDetailsList;
		} catch (Exception e) {
			logger.error("Exception while fetching all Order Details.",e);
		}
		return orderDetailsList;
		
	}
	
	/*
	 * This method will take orderDetailId as integer and will return
	 * OrderDetials object for that if exist in database.
	 */
	public OrderDetails findOrderDetailById(int orderDetailId) {
		try {
			return orderDetailRepo.findById(orderDetailId).orElse(null); 	
		} catch (Exception e) {
			logger.error("Exception while fetching Order Details by id, may be id you are looking for is not available.",e);
		}
		return null;
	}
}
