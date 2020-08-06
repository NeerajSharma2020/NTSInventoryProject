package com.st.ims.resolvers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.errorhandler.ProductNotFoundException;
import com.st.ims.model.Product;
import com.st.ims.repo.ProductRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ProductQueryResolver implements GraphQLQueryResolver {

	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ProductRepository productRepo;
	
	
	/* This method will return the list of products. */

	public List<Product> findAllProducts() {
		log.info("finding all products product...");
		try {
			return productRepo.findAll();	
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
		
	   }
	
	/*
	 * This method will take a Integer parameter i.e ProductId and will return that
	 * product.
	 */
	public Product findProductById(int productID) {
		log.info("finding product with id "+productID);
		try {
			return productRepo.findById(productID)
					.orElseThrow(() -> new ProductNotFoundException("Product not found", productID));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
		
		
	}

}
