package com.st.ims.resolvers;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Order;
import com.st.ims.model.OrderDetails;
import com.st.ims.repo.OrderDetailsRepository;
import com.st.ims.repo.OrderRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class OrderDetailsQueryResolver implements GraphQLQueryResolver{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OrderDetailsRepository orderDetailRepo;
	
	@Autowired
	private OrderRepository orderRepo;
      
	/* This method will return list of all OrderDetails objects. */
	public List<OrderDetails> findAllOrderDetails() {
		try {
			return orderDetailRepo.findAll();
		
		} catch (Exception e) {
			logger.error("Exception while fetching all Order Details.",e);
		}
		return Collections.emptyList();
	
		
	}
	
	/*
	 * This method will take orderId as integer and will return
	 * list of all OrderDetials object for that if exist in database.
	 */
	public List<OrderDetails> findOrderDetailById(int orderId) {
		try {
		Order order = orderRepo.findById(orderId).orElse(null); 
		if(order != null) {
	    	 return order.getOrderDetails();
	   
	     }else {
	    	 logger.info("Error while while getting list for orderId, may be order with id "+orderId+" doesn,t exists.");
	    	 return Collections.emptyList();
	     }

		} catch (Exception e) {
			logger.error("Exception while fetching Order Details by id, may be id you are looking for is not available.",e);
		}
		return Collections.emptyList();
	}
}
