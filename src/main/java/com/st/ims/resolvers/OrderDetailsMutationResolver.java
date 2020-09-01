package com.st.ims.resolvers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Order;
import com.st.ims.model.OrderDetails;
import com.st.ims.model.OrderProducts;
import com.st.ims.repo.OrderRepository;
import com.st.ims.utility.AppUtility;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Transactional
@Component
public class OrderDetailsMutationResolver implements GraphQLMutationResolver{
	private final Logger logger = Logger.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private OrderRepository orderRepo;

	/*
	 * This method will take order details and productId and will persist order
	 * in database and will make relationship of product with OrderDetails.
	 */
	public Order saveOrder(Order order, List<OrderProducts> orderProductList) {
		
		List<OrderDetails> ordereDetailsList = null;
		try {
			ordereDetailsList=AppUtility.getOrderDetailsList(order, orderProductList);
            order.setOrderDetails(ordereDetailsList);
			    return orderRepo.save(order);
	} catch (Exception e) {
			logger.error("Exception while saving Invoide Details.",e);
		}
		return null;
	}

	/* This method will delete order and orderDetails associated with it by Id */
	public boolean deleteOrderById(int orderId) {
		try {
			orderRepo.deleteById(orderId);
			return true;
		} catch (Exception e) {
			logger.error("Exception while deleting Order Details.",e);
			return false;
		}

	}

	/* This method will take OrderDetails Object and return new updated Object. */
	public Order updateOrder(Order order,List<OrderProducts> orderProductList) {
		try {
			List<OrderDetails> orderDetailsList = null;
			 orderDetailsList = AppUtility.getUpdatedOrderList(order, orderProductList);
			 order.setOrderDetails(orderDetailsList);
			 return entityManager.merge(order);
		} catch (Exception e) {
			logger.error("Exception while updaing Order Details.",e);
		}
		return null;
		 
		 
	}
}
