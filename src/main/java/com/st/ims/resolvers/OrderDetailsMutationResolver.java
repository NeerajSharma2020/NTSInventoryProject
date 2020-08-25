package com.st.ims.resolvers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.OrderDetails;
import com.st.ims.repo.OrderDetailsRepository;
import com.st.ims.repo.OrderRepository;
import com.st.ims.repo.ProductRepository;
import com.st.ims.utility.AppUtility;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Transactional
public class OrderDetailsMutationResolver implements GraphQLMutationResolver{
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * This method will take orderDetails object and productId, it will persist
	 * orderDetails object into database and make relationship with product with
	 * passed productId.
	 */
	public OrderDetails saveOrderDetails(OrderDetails orderDetail, int productId) {
		try {
			OrderDetails filledOrderDetails = AppUtility.setDefaultValues(orderDetail);
			filledOrderDetails.setProduct(productRepo.findById(productId).orElse(null));
			logger.info("initial order Id....."+orderDetail.getOrder().getOrderId());
			if(orderDetail.getOrder().getOrderId() == 0) {
				orderRepo.save(orderDetail.getOrder());
			}
			return orderDetailsRepo.save(filledOrderDetails);
		} catch (Exception e) {
			logger.error("Exception while saving Order Details.",e);
		}
		return orderDetail;
	}

	/* This method will take orderDetailsId as integer and will delete OrderDetails with it.*/
	public boolean deleteOrderDetailById(int orderDetailsId) {
		try {
			orderDetailsRepo.deleteById(orderDetailsId);
			return true;
		} catch (Exception e) {
			logger.error("Exception while deleting Order Details.",e);
			return false;
		}

	}

	/* This method will take OrderDetails Object and return new updated Object. */
	public OrderDetails updateOrderDetails(OrderDetails orderDetail) {
		try {
			if(orderDetailsRepo.existsById(orderDetail.getOrderDetailId())) {
				entityManager.merge(orderDetail.getOrder());
				return entityManager.merge(orderDetail);
			}else {
				logger.error("OrderDetails Id you are looking for doesn't exist.");
			}
		} catch (Exception e) {
			logger.error("Exception while updaing Invoice Details.",e);
		}
		return null;
	}

	
}
