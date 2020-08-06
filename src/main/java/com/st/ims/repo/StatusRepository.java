package com.st.ims.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.ims.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
