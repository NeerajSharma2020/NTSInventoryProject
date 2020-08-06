package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.Factory;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Integer> {

}
