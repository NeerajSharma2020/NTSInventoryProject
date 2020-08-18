package com.st.ims.resolvers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Invoice;
import com.st.ims.model.InvoiceDetails;
import com.st.ims.model.Product;
import com.st.ims.repo.InvoiceRepository;
import com.st.ims.repo.ProductRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
@Transactional
public class InvoiceDetailsResolver implements GraphQLResolver<InvoiceDetails> {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private ProductRepository productRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

	public Invoice getInvoice(InvoiceDetails invoiceDetails) {
		try {
			return invoiceRepo.save(invoiceDetails.getInvoice());
		} catch (Exception e) {
			logger.error("Exception while saving Invoice for Invoice Details.",e);
		}
		return null;

	}

	public Product getProduct(InvoiceDetails invoiceDetails) {
		try {
			return productRepo.save(invoiceDetails.getProduct());
		} catch (Exception e) {
			logger.error("Exception while saving Product for Invoice Details.",e);
		}
		return null;

	}

}
