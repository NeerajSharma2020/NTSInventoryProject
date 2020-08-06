package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.ims.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
