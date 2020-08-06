package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.FormatType;

@Repository
public interface FormatTypeRepository extends JpaRepository<FormatType, Integer> {

}
