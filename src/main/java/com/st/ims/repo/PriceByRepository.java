package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.PriceBy;

@Repository
public interface PriceByRepository extends JpaRepository<PriceBy, Integer> {

}
