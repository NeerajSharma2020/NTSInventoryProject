package com.st.ims.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.st.ims.model.Factory;
import com.st.ims.model.FormatType;
import com.st.ims.model.PriceBy;
import com.st.ims.model.Product;
import com.st.ims.model.ProductCategory;
import com.st.ims.model.ProductSubCategory;
import com.st.ims.model.Status;
import com.st.ims.repo.FactoryRepository;
import com.st.ims.repo.FormatTypeRepository;
import com.st.ims.repo.PriceByRepository;
import com.st.ims.repo.ProductCategoryRepository;
import com.st.ims.repo.ProductSubCategoryRepository;
import com.st.ims.repo.StatusRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class ProductResolver implements GraphQLResolver<Product>{
	
	@Autowired
	private FactoryRepository factoryRepo;
	
	@Autowired
	private FormatTypeRepository formattypeRepo;
	
	@Autowired
	private PriceByRepository priceRepo;
	
	@Autowired 
	private ProductCategoryRepository productCategoryRepo;
	
	@Autowired
	private ProductSubCategoryRepository productSubCategoryRepo;
	
	@Autowired
	private StatusRepository statusRepo;
	
	public Factory getFactoryID(Product product) {
		return factoryRepo.save(product.getFactoryID());
	}
	
	public ProductCategory getCategoryID(Product product) {
		return productCategoryRepo.save(product.getCategoryID());
	}
	
	public ProductSubCategory getSubCategoryID(Product product) {
		return productSubCategoryRepo.save(product.getSubCategoryID());
	}
	
	public FormatType getFormatTypeID(Product product) {
		return formattypeRepo.save(product.getFormatTypeID());
	}
	
	public Status getStatusID(Product product) {
		return statusRepo.save(product.getStatusID());
	}

	public PriceBy getPriceByID(Product product) {
		return priceRepo.save(product.getPriceById());
	}
	
}
