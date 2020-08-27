package com.st.ims.resolvers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Invoice;
import com.st.ims.model.InvoiceDetails;
import com.st.ims.model.InvoiceProducts;
import com.st.ims.repo.InvoiceRepository;
import com.st.ims.utility.AppUtility;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Transactional
public class InvoiceDetailsMutationResolver implements GraphQLMutationResolver {

	private final Logger logger = Logger.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private InvoiceRepository invoiceRepo;

	/*
	 * This method will take invoice details and productId and will persist invoice
	 * in database and will make relationship of product with InvoiceDetails.
	 */
	public Invoice saveInvoice(Invoice invoice, List<InvoiceProducts> invoiceProductList) {
		try {
			List<InvoiceDetails> invoiceDetailsList = AppUtility.getInvoiceDetailsList(invoice, invoiceProductList);
            invoice.setInvoiceDetails(invoiceDetailsList);
			    return invoiceRepo.save(invoice);
	} catch (Exception e) {
			logger.error("Exception while saving Invoide Details.",e);
		}
		return null;
	}

	/* This method will delete invoice and invoiceDetails associated with it by Id */
	public boolean deleteInvoiceById(int invoiceId) {
		try {
			invoiceRepo.deleteById(invoiceId);
			return true;
		} catch (Exception e) {
			logger.error("Exception while deleting Invoice Details.",e);
			return false;
		}

	}

	/* This method will take InvoiceDetails Object and return new updated Object. */
	public Invoice updateInvoice(Invoice invoice,List<InvoiceProducts> invoiceProductList) {
		try {
			List<InvoiceDetails> invoiceDetailsList = AppUtility.getUpdatedInvoiceList(invoice, invoiceProductList);
			 invoice.setInvoiceDetails(invoiceDetailsList);
			 return entityManager.merge(invoice);
		} catch (Exception e) {
			logger.error("Exception while updaing Invoice Details.",e);
		}
		return null;
		 
		 
	}

}
