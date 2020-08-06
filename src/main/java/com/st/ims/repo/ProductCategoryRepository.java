package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.ims.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
