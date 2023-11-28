package com.example.schedule.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.schedule.cs.entity.Customer;

@Repository
public interface CustomRepository  extends JpaRepository<Customer, Long>{
	List<Customer> findAllByOrderByArriveTimeAsc();
	List<Customer> findAllByOrderByUseTimeAsc();
}
