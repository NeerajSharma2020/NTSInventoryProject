package com.st.ims.resolvers;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.errorhandler.ProductNotFoundException;
import com.st.ims.model.Product;
import com.st.ims.model.ProductInput;
import com.st.ims.repo.ProductRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Transactional
public class ProductMutationResolver implements GraphQLMutationResolver{
	
	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	ProductRepository productRepo;
	
	/*
	 * This method will accept an Product Object and will save that object into
	 * database.
	 */
	public Product saveProduct(Product productInput){
		log.info("saving product....");
		try {
			if(productInput != null) {
				return productRepo.save(productInput);
			}
		} catch (Exception e) {
			log.error(e);
		}
		
		return null;
	}
	
	/*
	 * This method will take productID as integer and will delete that product from
	 * database.
	 */
	public String deleteProductById(int productId) {
		try {
			log.info("Deleting product based on id.");
			productRepo.deleteById(productId);
			return "Product deleted successfully.";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return "No Product entity with id "+productId+" exists!";
		}
		
		
		 
	}

	/*
	 * This method will take two parameters, first productID as integer and other
	 * Product object.
	 * This will find product with passed id and update that according to passed object.
	 */
	public Product updateProductById(int productID,Product productData) {
		Product productToUpdate = productRepo.findById(productID)
				 .orElseThrow(() -> new ProductNotFoundException("No product found for this ProductID.", productID));
		log.info("Product to be updated "+productToUpdate);
		try {
			productToUpdate.getCategoryID().setTitle(productData.getCategoryID().getTitle());
			productToUpdate.getSubCategoryID().setTitle(productData.getSubCategoryID().getTitle());
			productToUpdate.setPartNumber(productData.getPartNumber());
			productToUpdate.setDescription(productData.getDescription());
			productToUpdate.getFormatTypeID().setTitle(productData.getFormatTypeID().getTitle());
			productToUpdate.setIndUPC(productData.getIndUPC());
			productToUpdate.setCaseUPC(productData.getCaseUPC());
			productToUpdate.setEan(productData.getEan());
			productToUpdate.setAlternateEAN(productData.getAlternateEAN());
			productToUpdate.setUom(productData.getUom());
			productToUpdate.getStatusID().setTitle(productData.getStatusID().getTitle());
			productToUpdate.setBasePrice(productData.getBasePrice());
			productToUpdate.setCost(productData.getCost());
			productToUpdate.getPriceById().setTitle(productData.getPriceById().getTitle());;
			productToUpdate.setListPrice(productData.getListPrice());
			productToUpdate.setMinOrderQty(productData.getMinOrderQty());
			productToUpdate.setCommisionRate(productData.getCommisionRate());
			productToUpdate.setImageURL(productData.getImageURL());
			return productRepo.save(productToUpdate);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return productToUpdate;
		
		
	}
    
}
