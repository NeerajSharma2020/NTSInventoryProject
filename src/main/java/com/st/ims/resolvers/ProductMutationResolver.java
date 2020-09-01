package com.st.ims.resolvers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Product;
import com.st.ims.repo.ProductRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Transactional
public class ProductMutationResolver implements GraphQLMutationResolver{
	
	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	ProductRepository productRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * This method will accept an Product Object and will save that object into
	 * database.
	 */
	public Product saveProduct(Product productInput){
		try {
			if(productInput != null) {
				return productRepo.save(productInput);
			}
		} catch (Exception e) {
			log.error("Exception while saving Product.",e);
		}
		
		return null;
	}
	
	/*
	 * This method will take productID as integer and will delete that product from
	 * database.
	 */
	public boolean deleteProductById(int productId) {
		try {
			productRepo.deleteById(productId);
			return true;
		} catch (Exception e) {
			log.error("Exception while deleting Product.",e);
			return false;
		}
		
		
		 
	}

	/*
	 * This method will take two parameters, first productId as integer and other
	 * Product object.
	 * This will find product with passed id and update that according to passed object.
	 */
	public Product updateProductById(Product productData) {
		
		try {
			entityManager.merge(productData.getFactory());
            entityManager.merge(productData.getCategory());
            entityManager.merge(productData.getSubCategory());
            entityManager.merge(productData.getFormatType());
            entityManager.merge(productData.getPriceBy());
            entityManager.merge(productData.getStatus());
            log.info("Product to be updated.."+productData);
			return entityManager.merge(productData);
		} catch (Exception e) {
			log.error("Exception while updating Product.",e);
		}
		return null;
	
		
		
	}
    
}
