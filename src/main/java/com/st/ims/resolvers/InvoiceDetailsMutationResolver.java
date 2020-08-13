package com.st.ims.resolvers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.InvoiceDetails;
import com.st.ims.repo.InvoiceDetailsRepository;
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

	/*
	 * This method will take invoice details and productId and will persist invoice
	 * in database and will make relationship of product with invoicedetails.
	 */
	public InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetail, int productId) {
		try {
			InvoiceDetails filledInvoiceDetails = AppUtility.setDefaultValues(invoiceDetail);
			filledInvoiceDetails.setProduct(productRepo.findById(productId).orElse(null));
			return invoiceDetailsRepo.save(filledInvoiceDetails);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return invoiceDetail;
	}

	/* This method will delete invoice details by Id */
	public String deleteInvoiceDetailById(int ivoiceDetailsId) {
		try {
			invoiceDetailsRepo.deleteById(ivoiceDetailsId);
			return "Invoice Deleted successfully.";
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return "No InvoiceDetail entity with id " + ivoiceDetailsId + " exists!";
		}

	}

	/* This method will take InvoiceDetails Object and return new updated Object. */
	public InvoiceDetails updateInvoiceDetails(InvoiceDetails invoiceDetail) {
		try {
			entityManager.merge(invoiceDetail.getInvoice());
			return entityManager.merge(invoiceDetail);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return invoiceDetail;
	}

}
