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

@Component
@Transactional
public class OrderDetailsMutationResolver implements GraphQLMutationResolver {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderRepository orderRepo;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * This method will take orderDetails object and productId, it will persist
	 * orderDetails object into database and make relationship with product with
	 * passed productId.
	 */
	public Order saveOrderDetails(Order order, List<OrderProducts> orderProductList) {
	   try {
		   List<OrderDetails> orderDetailsList = AppUtility.getOrderDetailsList(order, orderProductList);
		   order.setOrderDetails(orderDetailsList);
		   return orderRepo.save(order);
		} catch (Exception e) {
			logger.error("Exception while saving Order Details.", e);
		}
		return null;
	}

	/*
	 * This method will take orderId as integer and will delete OrderDetails
	 * with it.
	 */
	public boolean deleteOrderById(int orderId) {
		try {
			orderRepo.deleteById(orderId);
			return true;
		} catch (Exception e) {
			logger.error("Exception while deleting Order Details.", e);
			return false;
		}

	}

	/* This method will take OrderDetails Object and return new updated Object. */
	public Order updateOrder(Order order,List<OrderProducts> orderProductList) {
		try {
			List<OrderDetails> orderDetailsList = AppUtility.getUpdatedOrderList(order, orderProductList);
			 order.setOrderDetails(orderDetailsList);
			 return entityManager.merge(order);
		} catch (Exception e) {
			logger.error("Exception while updaing Order Details.",e);
		}
		return null;
	}

}
