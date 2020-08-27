package com.st.ims.resolvers;

import java.util.ArrayList;
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
		List<Product> productsList = new ArrayList<>();
		try {
			productsList=productRepo.findAll();
			log.info("Size of all products List.."+productsList.size());
			return productsList;
		} catch (Exception e) {
			log.error("Exception while fetching all Products."+e);
		}
		return productsList;
		
	   }
	
	/*
	 * This method will take a Integer parameter i.e ProductId and will return that
	 * product.
	 */
	public Product findProductById(int productId) {
		log.info("finding product with id "+productId);
		try {
			return productRepo.findById(productId)
					.orElseThrow(() -> new ProductNotFoundException("Product not found", productId));
		} catch (Exception e) {
			log.error("Exception while fetching Prodcut by Id."+e);
		}
		return null;
		
		
	}

}
