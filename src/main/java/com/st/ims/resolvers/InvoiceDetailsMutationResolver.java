package com.st.ims.resolvers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.InvoiceDetails;
import com.st.ims.repo.InvoiceDetailsRepository;
import com.st.ims.repo.InvoiceRepository;
import com.st.ims.repo.ProductRepository;
import com.st.ims.utility.AppUtility;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Transactional
public class InvoiceDetailsMutationResolver implements GraphQLMutationResolver {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepo;

	@Autowired
	private ProductRepository productRepo;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private InvoiceRepository invoiceRepo;

	/*
	 * This method will take invoice details and productId and will persist invoice
	 * in database and will make relationship of product with invoicedetails.
	 */
	public InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetail, int productId) {
		try {
			InvoiceDetails filledInvoiceDetails = AppUtility.setDefaultValues(invoiceDetail);
			filledInvoiceDetails.setProduct(productRepo.findById(productId).orElse(null));
			logger.info("initial invoice Id....."+invoiceDetail.getInvoice().getInvoiceId());
			if(invoiceDetail.getInvoice().getInvoiceId() == 0) {
				invoiceRepo.save(invoiceDetail.getInvoice());
			}
			return invoiceDetailsRepo.save(filledInvoiceDetails);
		} catch (Exception e) {
			logger.error("Exception while saving Invoide Details.",e);
		}
		return invoiceDetail;
	}

	/* This method will delete invoice details by Id */
	public boolean deleteInvoiceDetailById(int invoiceDetailsId) {
		try {
			invoiceDetailsRepo.deleteById(invoiceDetailsId);
			return true;
		} catch (Exception e) {
			logger.error("Exception while deleting Invoice Details.",e);
			return false;
		}

	}

	/* This method will take InvoiceDetails Object and return new updated Object. */
	public InvoiceDetails updateInvoiceDetails(InvoiceDetails invoiceDetail) {
		try {
			if(invoiceDetailsRepo.existsById(invoiceDetail.getInvoiceDetailId())) {
				entityManager.merge(invoiceDetail.getInvoice());
				return entityManager.merge(invoiceDetail);	
			}else {
				logger.error("Exception while updaing Invoice Details, May be InvocieDetailsId you are looking for doesn't exists.");
			}
			} catch (Exception e) {
			logger.error("Exception while updaing Invoice Details.",e);
		}
		return invoiceDetail;
	}

}
