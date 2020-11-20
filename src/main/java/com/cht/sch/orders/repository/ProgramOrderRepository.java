package com.cht.sch.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cht.sch.orders.model.Order;

@Repository
public interface ProgramOrderRepository extends JpaRepository<Order, Integer> {

	Order findByProgramAndClient(String program, String client);
}
