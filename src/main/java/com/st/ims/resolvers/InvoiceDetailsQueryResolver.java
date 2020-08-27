package com.st.ims.resolvers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Invoice;
import com.st.ims.model.InvoiceDetails;
import com.st.ims.repo.InvoiceDetailsRepository;
import com.st.ims.repo.InvoiceRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Transactional
@Component
public class InvoiceDetailsQueryResolver implements GraphQLQueryResolver{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepo;
	
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	/* This method will return invoice and product data with invoiceDetailsId. */
     public List<InvoiceDetails> findAllInvoiceDetails(){
         List<InvoiceDetails> invoiceDetailsList =  new ArrayList<>();
		try {
			invoiceDetailsList= invoiceDetailsRepo.findAll();
			return invoiceDetailsList;
		} catch (Exception e) {
			logger.error("Exception while fetching all Invoice Details.",e);
		}
		return invoiceDetailsList;
	}
     
	/* This method will take invoiceId and will return list of all invoiceDetails object associated with this invoiceId.*/
     public List<InvoiceDetails> findInvoiceDetailsById(int invoiceId) {
    	 List<InvoiceDetails> invoiceDetailsList =  new ArrayList<>();
    	 try {
    	     Invoice invoice = invoiceRepo.findById(invoiceId).orElse(null);
    	     if(invoice != null) {
    	    	 invoiceDetailsList = invoice.getInvoiceDetails();
    	    	 return invoiceDetailsList;
    	     }else {
    	    	 logger.error("Error while while getting list for invoiceId, may be invoice with id "+invoiceId+" doesn,t exists.");
    	    	 return invoiceDetailsList;
    	     }
		}catch (Exception e) {
			logger.error("Exception while fetching Invoice Details by id, may be id you are looking for not available.",e);
		}
		return invoiceDetailsList;
     }

}
