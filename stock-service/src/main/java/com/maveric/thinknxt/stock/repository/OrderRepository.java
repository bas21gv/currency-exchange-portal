package com.maveric.thinknxt.stock.repository;


import com.maveric.thinknxt.stock.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
