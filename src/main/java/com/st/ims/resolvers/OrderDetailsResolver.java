package com.st.ims.resolvers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Order;
import com.st.ims.model.OrderDetails;
import com.st.ims.model.Product;
import com.st.ims.repo.OrderRepository;
import com.st.ims.repo.ProductRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class OrderDetailsResolver implements GraphQLResolver<OrderDetails>{
	
private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductRepository productRepo;

	public Order getOrder(OrderDetails orderDetails) {
		try {
			return orderRepo.save(orderDetails.getOrder());
		} catch (Exception e) {
			logger.error("Exception while saving Order for Order Details.",e);
		}
		return null;

	}

	public Product getProduct(OrderDetails orderDetails) {
		try {
			return productRepo.save(orderDetails.getProduct());
		} catch (Exception e) {
			logger.error("Exception while saving Product for Order Details.",e);
		}
		return null;

	}


}