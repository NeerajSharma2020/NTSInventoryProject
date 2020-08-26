package com.st.ims.resolvers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.InvoiceDetails;
import com.st.ims.model.InvoiceProducts;
import com.st.ims.repo.InvoiceDetailsRepository;
import com.st.ims.utility.AppUtility;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Transactional
public class InvoiceDetailsMutationResolver implements GraphQLMutationResolver {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepo;

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * This method will take invoice details and productId and will persist invoice
	 * in database and will make relationship of product with InvoiceDetails.
	 */
	public List<InvoiceDetails> saveInvoiceDetails(InvoiceDetails invoiceDetail, List<InvoiceProducts> invoiceProductList) {
		
		List<InvoiceDetails> invoiceDetailsList = null;
		try {
			invoiceDetailsList=AppUtility.getInvoiceDetailsList(invoiceDetail, invoiceProductList);
             if(invoiceDetail.getInvoice() != null) {
            	 entityManager.persist(invoiceDetail.getInvoice());
             }
			    return invoiceDetailsRepo.saveAll(invoiceDetailsList);
	} catch (Exception e) {
			logger.error("Exception while saving Invoide Details.",e);
		}
		return invoiceDetailsList;
	}

	/* This method will delete invoice details by Id */
	public boolean deleteInvoiceDetailById(int ivoiceDetailsId) {
		try {
			invoiceDetailsRepo.deleteById(ivoiceDetailsId);
			return true;
		} catch (Exception e) {
			logger.error("Exception while deleting Invoice Details.",e);
			return false;
		}

	}

	/* This method will take InvoiceDetails Object and return new updated Object. */
	public InvoiceDetails updateInvoiceDetails(InvoiceDetails invoiceDetail) {
		try {
			entityManager.merge(invoiceDetail.getInvoice());
			return entityManager.merge(invoiceDetail);
		} catch (Exception e) {
			logger.error("Exception while updaing Invoice Details.",e);
		}
		return invoiceDetail;
	}

}
