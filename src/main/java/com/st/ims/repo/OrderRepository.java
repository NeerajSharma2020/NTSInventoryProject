package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
