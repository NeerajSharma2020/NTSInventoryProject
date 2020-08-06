package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.ProductSubCategory;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Integer> {

}
