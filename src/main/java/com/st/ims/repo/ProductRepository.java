package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}

